package com.scorpio.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

@Component
public class ZuulPostLoggingFilter extends ZuulFilter {

    public static final String POST = "post";
    private static Logger logger = LoggerFactory.getLogger(ZuulPostLoggingFilter.class);

    @Override
    public String filterType() {
        return POST_TYPE;
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
        final HttpServletRequest request = ctx.getRequest();
        final HttpServletResponse response = ctx.getResponse();
        Long startTime = (Long)ctx.get("startTime");

        if (logger.isInfoEnabled()) {
            logger.info("<--- {} request to: {}, response code:{}, execution time: {}", request.getMethod(),
                    request.getRequestURL().toString(),
                    response.getStatus(),
                    System.currentTimeMillis() - startTime);
            logger.info("Request was serviced by: {}", ctx.getRouteHost());
        }
        return null;
    }
}
