package com.mongondb.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public class Test {

    private MongoClient mongoClient;
    private MongoDatabase commentdb ;
    private MongoCollection<Document> comment ;

    @Before
    public void Before(){
        mongoClient = new MongoClient("192.168.200.128");
        commentdb = mongoClient.getDatabase("commentdb");
        comment= this.commentdb.getCollection("comment");//获取集合
    }


    @org.junit.Test
    public void Test(){

        FindIterable<Document> documents = comment.find();//获取文档集合
        for (Document document : documents) {
            System.out.println("------------------------");
            System.out.println("_id:"+document.get("_id"));
            System.out.println("_count:"+document.get("count"));
            System.out.println("userId:"+document.get("userId"));
            System.out.println("点赞数:"+document.get("thumbup"));

        }
    }

    @org.junit.Test
    public void Find(){
        FindIterable<Document> documents = comment.find(new BasicDBObject("_id", "002"));
        for (Document document : documents) {
            System.out.println("_id:"+document.get("_id"));
            System.out.println("count:"+document.get("count"));
            System.out.println("userId:"+document.get("userId"));
            System.out.println("点赞数:"+document.get("thumbup"));
        }
    }

    @org.junit.Test
    public void Update(){
        //修改条件
        Bson basicDBObject = new BasicDBObject("_id", "002");
        //修改的数据
        Bson bson = new BasicDBObject("$set",new Document("count","当时应该是真的2"));
        System.out.println(bson);
        UpdateResult updateResult = comment.updateOne(basicDBObject, bson);
        System.out.println(updateResult);
    }

    @org.junit.Test
    public void insert(){
        Map<String,Object> map = new HashMap<>();
        map.put("_id","10086");
        map.put("count","测试用例");
        map.put("userId","100861");
        map.put("thumbup","1008611");
        Document document = new Document(map);
        comment.insertOne(document);
    }

    @org.junit.Test
    public void remove(){
        Bson basicDBObject = new BasicDBObject("_id", "10086");//条件
        System.out.println(basicDBObject);
        DeleteResult deleteResult = comment.deleteOne(basicDBObject);
        System.out.println(deleteResult);
    }

    @After
    public void After(){
        mongoClient.close();
    }


}
