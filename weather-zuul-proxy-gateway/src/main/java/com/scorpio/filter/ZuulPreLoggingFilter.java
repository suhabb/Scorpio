package com.scorpio.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class ZuulPreLoggingFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(ZuulPreLoggingFilter.class);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        ctx.set("startTime", System.currentTimeMillis());

        if (logger.isInfoEnabled()) {
            logger.info("---> {} request to {}", request.getMethod(), request.getRequestURL().toString());
        }
        return null;
    }
}
