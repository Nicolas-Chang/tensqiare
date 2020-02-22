package com.tensquare.article.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @PackageName: com.tensquare.article.pojo
 * @ClassName: Moment
 * @Author: Nicolas.Chang
 * @Date: 2020/2/22 17:08
 * @Description:
 */
public class Comment implements Serializable {

    @Id
    private String _id;          // 点赞id
    private String articleid;    // 文章id
    private String content;      // 内容
    private String userId;       // 用户id
    private String parentid;     // 父评论id
    private Date publishdate;    // 修改时间
    private Integer thumbup;     // 点赞数

    public Comment() {
    }

    public Comment(String _id, String articleid, String content, String userid, String parentid, Date publishdate, Integer thumbup) {
        this._id = _id;
        this.articleid = articleid;
        this.content = content;
        this.userId = userid;
        this.parentid = parentid;
        this.publishdate = publishdate;
        this.thumbup = thumbup;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userId;
    }

    public void setUserid(String userid) {
        this.userId = userid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Integer getThumbup() {
        return thumbup;
    }

    public void setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "_id='" + _id + '\'' +
                ", articleid='" + articleid + '\'' +
                ", content='" + content + '\'' +
                ", userid='" + userId + '\'' +
                ", parentid='" + parentid + '\'' +
                ", publishdate=" + publishdate +
                ", thumbup=" + thumbup +
                '}';
    }
}
