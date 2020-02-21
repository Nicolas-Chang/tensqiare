package com.tensquare.article.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tensquare.article.pojo.Article;
import org.springframework.stereotype.Component;

@Component
public interface ArticleMapper extends BaseMapper<Article> {
}
