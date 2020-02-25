package com.tensquare.encrypt.filters;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.tensquare.encrypt.rsa.RsaKeys;
import com.tensquare.encrypt.service.RsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @PackageName: com.tensquare.encrypt.filters
 * @ClassName: RSARequestFilter
 * @Author: Nicolas.Chang
 * @Date: 2020/2/23 15:42
 * @Description:
 */
@Component
public class RSARequestFilter extends ZuulFilter {

    @Autowired
    private RsaService rsaService;

    @Override
    public String filterType() {
        //过滤时机,在转发之前过滤
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;  //执行顺序
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {



        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        try {
            String dacryptData = null;
            String url = request.getRequestURL().toString();
            ServletInputStream inputStream = request.getInputStream();
            String message = StreamUtils.copyToString(inputStream, Charsets.UTF_8);
            if(!Strings.isNullOrEmpty(message)){   //如果不为空  进行解密
                System.out.println(String.format("请求中的密文: %s",message));
                dacryptData = rsaService.RSADecryptDataPEM(message, RsaKeys.getServerPrvKeyPkcs8());
                System.out.println(String.format("解密后的messag: %s",dacryptData));
            }
            System.out.println(String.format("request: %s >>> %s, data=%s", request.getMethod(), url, dacryptData));
            //查询是否解密  如果解密 则 dacryptDate != null  携带参数
            if(!Strings.isNullOrEmpty(dacryptData)){
                byte[] dacryptDataBytes = dacryptData.getBytes();
                currentContext.setRequest(new HttpServletRequestWrapper(request){
                    @Override
                    public ServletInputStream getInputStream() throws IOException {
                        return new ServletInputStreamWrapper(dacryptDataBytes);
                    }

                    @Override
                    public int getContentLength() {
                        return dacryptDataBytes.length;
                    }

                    @Override
                    public long getContentLengthLong() {
                        return dacryptDataBytes.length;
                    }
                });
            }
            System.out.println("转发请求");
            currentContext.addZuulRequestHeader("Content-Type",String.valueOf(MediaType.APPLICATION_JSON)+";chartset=UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
