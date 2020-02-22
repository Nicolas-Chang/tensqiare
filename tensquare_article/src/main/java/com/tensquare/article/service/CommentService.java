package com.tensquare.article.service;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.respority.MongonRespority;
import com.tensquare.utils.IdWorker;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @PackageName: com.tensquare.article.service
 * @ClassName: CommentService
 * @Author: Nicolas.Chang
 * @Date: 2020/2/22 17:13
 * @Description:
 */


@Service
public class CommentService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongonRespority mongonRespority;

    @Autowired
    private MongoTemplate mongoTemplate;



    //id查询

    public Comment findById(String _id){
        Comment comment = mongonRespority.findById(_id).get();
        return comment;
    }

    //查询所有
    public List<Comment> find(){
        return mongonRespority.findAll();
    }

    //增加
    public void save(Comment comment){
        comment.set_id(idWorker.nextId()+"");
        comment.setThumbup(0);
        comment.setPublishdate(new Date());
        mongonRespority.save(comment);
    }

    //修改

    public void update(Comment comment){
        mongonRespority.save(comment);  //  当id不为空则为修改
    }

    //删除
    public void deleted(String _id){
        mongonRespority.deleteById(_id);
    }

    //根据文章id
    public List<Comment> findByArticleid(String articleid){
        List<Comment> byArticleid = mongonRespority.findByArticleid(articleid);
        return byArticleid;
    }

    public void updateAddThumpub(String _id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(_id));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"comment");
    }

    public void updateRemoveThumpub(String _id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(_id));
        Update update = new Update();
        update.inc("thumbup",-1);
        mongoTemplate.updateFirst(query,update,"comment");
    }

}
