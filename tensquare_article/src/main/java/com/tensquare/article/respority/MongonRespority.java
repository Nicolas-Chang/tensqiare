package com.tensquare.article.respority;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface MongonRespority extends MongoRepository<Comment,String> {

    //根据文章id查询
     List<Comment> findByArticleid(String articleid);
}
