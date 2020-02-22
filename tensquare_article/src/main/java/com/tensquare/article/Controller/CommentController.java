package com.tensquare.article.Controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

/**
 * @PackageName: com.tensquare.article.Controller
 * @ClassName: CommentController
 * @Author: Nicolas.Chang
 * @Date: 2020/2/22 18:20
 * @Description:
 */


@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 查询全部
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Comment> commentList = commentService.find();
        return new Result(true, StatusCode.OK,"查询全部成功",commentList);
    }

    /**
     * 根据id查询
     * @param _id
     * @return
     */
    @RequestMapping(value = "{_id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("_id") String _id){
        Comment comment = commentService.findById(_id);
        return new Result(true,StatusCode.OK,"查询一条成功",comment);
    }


    /**
     * 新增
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment){
        commentService.save(comment);
        return new Result(true,StatusCode.OK,"新增成功");
    }


    /**
     * 修改
     * @param _id
     * @param comment
     * @return
     */
    @RequestMapping(value = "{_id}",method = RequestMethod.PUT)
    public  Result update(@PathVariable("_id") String _id, @RequestBody Comment comment){
        comment.set_id(_id);
        commentService.update(comment);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "{_id}",method = RequestMethod.DELETE)
    public Result deleted(@PathVariable("_id") String _id){
            commentService.deleted(_id);
            return new Result(true,StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/articleid/{articleid}",method = RequestMethod.GET)
    public Result findByArticleId(@PathVariable("articleid") String articleid){
        List<Comment> byArticleid = commentService.findByArticleid(articleid);
        return new Result(true,StatusCode.OK,"根据文章查询成功",byArticleid);
    }

    @RequestMapping(value = "/thumpub/{_id}",method = RequestMethod.PUT)
    public Result updateThumpub(@PathVariable("_id") String _id){
        String userId = "001";
        Object key = redisTemplate.opsForValue().get("ThumPub_" + userId);
        if(key != null){
            commentService.updateRemoveThumpub(_id);
            redisTemplate.delete("ThumPub_"+userId);
            return new Result(true,StatusCode.OK,"remove1Start");
        }
        commentService.updateAddThumpub(_id);
        redisTemplate.boundValueOps("ThumPub_"+userId).set("1");
        return new Result(true,StatusCode.OK,"Add1Start");
    }

}
