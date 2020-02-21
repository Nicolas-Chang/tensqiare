package com.tensquare.article.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.dao.ArticleMapper;
import com.tensquare.article.pojo.Article;
import com.tensquare.entity.PageResult;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private IdWorker idWorker;


    public List<Article> findAll(){
        return articleMapper.selectList(null);
    }

    public Article findById(String id){
        Article article = articleMapper.selectById(id);
        return article;
    }

    public void updateOneById(Article article){
        articleMapper.updateById(article);
    }

    public void deletedById(String id){
        articleMapper.deleteById(id);
    }


    public void save(Article article){
        article.setId(idWorker.nextId()+"");
        article.setVisits(0);
        article.setThumbup(0);
        article.setComment(0);
        articleMapper.insert(article);
    }
    //条件分页
    public Page<Article> articlePage(Map map,int page , int size){

        //public Page search(Map map, int page, int size) {
        EntityWrapper<Article> wrapper = new EntityWrapper<>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            wrapper.eq(null != key,key,map.get(key));
        }
        Page page1 = new Page(page,size);
        List<Article> articles = articleMapper.selectPage(page1, wrapper);
          page1.setRecords(articles);
            return page1;
    }


}
