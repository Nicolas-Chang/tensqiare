package com.tensquare.notice.client;

import com.tensquare.entity.Result;
import com.tensquare.notice.pojo.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "tensquare-article")
public interface ArticleClient {

    @RequestMapping(value = "article/{articleId}",method = RequestMethod.GET)
    public Result<Article> function2(@PathVariable("articleId") String articleId );
}
