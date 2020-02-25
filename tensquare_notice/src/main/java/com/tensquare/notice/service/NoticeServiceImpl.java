package com.tensquare.notice.service;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.entity.Result;
import com.tensquare.notice.client.ArticleClient;
import com.tensquare.notice.client.UserClient;
import com.tensquare.notice.dao.NoticeFreshMapper;
import com.tensquare.notice.dao.NoticeMaapper;
import com.tensquare.notice.pojo.Article;
import com.tensquare.notice.pojo.Notice;
import com.tensquare.notice.pojo.NoticeFresh;
import com.tensquare.notice.pojo.User;
import com.tensquare.utils.IdWorker;
import org.bouncycastle.crypto.engines.RC2Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.tensquare.notice.service
 * @ClassName: NoticeServiceImpl
 * @Author: Nicolas.Chang
 * @Date: 2020/2/25 18:39
 * @Description:
 */

@Service
public class NoticeServiceImpl{

    @Autowired
    private NoticeMaapper noticeMaapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private NoticeFreshMapper noticeFreshMapper;


    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private UserClient userClient;


    //查

    public Notice findById(String id){
        Notice notice = noticeMaapper.selectById(id);

        return notice;

    }

    //增
    public void save(Notice notice){
        String id = idWorker.nextId()+ "";
        notice.setId(id);
        notice.setCreatetime(new Date());
        notice.setState("0");   //未读
        //保存至推送消息
        noticeMaapper.insert(notice);

        //保存中间关系
        NoticeFresh noticeFresh = new NoticeFresh();
        noticeFresh.setNoticeId(id);
        noticeFresh.setUserId(notice.getReceiverId());   //通知用户的id

        noticeFreshMapper.insert(noticeFresh);
    }

    //通知推送  条件分页
    public Page<Notice> findByPage(Notice notice,Integer page, Integer size){

        Page<Notice> page1= new Page<>(page,size);
        List<Notice> noticeList = noticeMaapper.selectPage(page1, new EntityWrapper<>(notice));
        page1.setRecords(noticeList);
        return page1;
    }

    //根据userId查询待推送业务

    public Page<NoticeFresh> findByNoticeFresh(String userId,Integer page,Integer size){

        Page<NoticeFresh> pageData = new Page<>(page,size);

        NoticeFresh noticeFresh = new NoticeFresh();
        noticeFresh.setUserId(userId);

        List<NoticeFresh> noticeFreshList = noticeFreshMapper.selectPage(pageData, new EntityWrapper<>(noticeFresh));
        pageData.setRecords(noticeFreshList);
        return pageData;
    }


    //修改
    public void updateNoticelById(Notice notice){

        noticeMaapper.updateById(notice);
    }


    public void getNoticelInfo(Notice notice){
        Map map = (Map) userClient.findById(notice.getOperatorId()).getData();
        String nickname = (String) map.get("nickname");
        notice.setOperatorName(nickname);
        if ("article".equals(notice.getTargetType())) {
            Result<Article> result = articleClient.function2(notice.getTargetId());
            Map map1 = (Map) result.getData();
            String title = (String) map1.get("title");
            notice.setTargetName(title);
        }
    }

    public Notice findByInfo(String id){
        Notice notice = noticeMaapper.selectById(id);
        getNoticelInfo(notice);
        return notice;
    }

}

