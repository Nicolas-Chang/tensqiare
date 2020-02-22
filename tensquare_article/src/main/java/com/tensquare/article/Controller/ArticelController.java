package com.tensquare.article.Controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.config.ControllerMonitor;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.dc.pr.PRError;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticelController {


    @Autowired
    private ArticleService articleService;

//    GET/article 文章全部列表
    @ControllerMonitor
    @RequestMapping(method = RequestMethod.GET)
    public Result function1(){
        List<Article> all = articleService.findAll();
        return new Result(true, StatusCode.OK,"查询全部成功",all);
    }
    ///article/{articleId}根据ID查询文章
    @ControllerMonitor
    @RequestMapping(value = "{articleId}",method = RequestMethod.GET)
    public Result function2(@PathVariable("articleId") String articleId ){
        Article article = articleService.findById(articleId);
        return new Result(true,StatusCode.OK,"查询成功",article);
    }

    //POST /article 增加文章

    @RequestMapping(method = RequestMethod.POST)
    public Result function3(@RequestBody Article article){
        articleService.save(article);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    ///article/search/{page}/{size}
    //文章分页
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findPage(@RequestBody Map map,@PathVariable Integer page, @PathVariable Integer size){
        Page<Article> page1 = articleService.articlePage(map, page, size);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(page1.getTotal());
        pageResult.setRows(page1.getRecords());
        return new Result(true,StatusCode.OK,"分页查询成功",pageResult);

    }

// /article/{articleId}  修改文章
    @RequestMapping(value = "{articleId}",method = RequestMethod.PUT)
    public Result function4(@PathVariable("articleId") String articleId,@RequestBody Article article){
                article.setId(articleId);
                articleService.updateOneById(article);
                return new Result(true,StatusCode.OK,"修改成功");
    }





}
