package com.tensquare.notice.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.notice.pojo.Notice;
import com.tensquare.notice.pojo.NoticeFresh;
import com.tensquare.notice.service.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

/**
 * @PackageName: com.tensquare.notice.controller
 * @ClassName: NoticelController
 * @Author: Nicolas.Chang
 * @Date: 2020/2/25 19:45
 * @Description:
 */


@RestController
@RequestMapping("/noticel")
public class NoticelController {


    @Autowired
    private NoticeServiceImpl noticeService;


    /**
     * id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){

        Notice notice = noticeService.findById(id);
        return new Result(true, StatusCode.OK,"查询成功",notice);

    }


    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public PageResult findNoticeByPage(@RequestBody Notice notice,@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<Notice> noticeServiceByPage = noticeService.findByPage(notice, page, size);
        long total = noticeServiceByPage.getTotal();
        List<Notice> rows = noticeServiceByPage.getRecords();
        return new PageResult(total,rows);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result updateById(@RequestBody Notice notice,@PathVariable String id){
            notice.setId(id);
        noticeService.updateNoticelById(notice);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "/noticelFresh/{page}/{size}")
    public PageResult<NoticeFresh> findNoticelFreshBySearch(@RequestBody NoticeFresh noticeFresh,
                                           @PathVariable("page") Integer page,
                                           @PathVariable("size") Integer size){
        String userId = noticeFresh.getUserId();
        Page<NoticeFresh> noticeServiceByNoticeFresh = noticeService.findByNoticeFresh(userId, page, size);
        long total = noticeServiceByNoticeFresh.getTotal();
        List<NoticeFresh> rows = noticeServiceByNoticeFresh.getRecords();
        return new PageResult(total,rows);
    }


    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Notice notice){
        noticeService.save(notice);
        return new Result(true,StatusCode.OK,"新增成功");
    }

    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public Result findInfoById(@PathVariable("id") String id){

        Notice notice = noticeService.findByInfo(id);
        return new Result(true,StatusCode.OK,"查询成功",notice);
    }


}
