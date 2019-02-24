package com.scorpio.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class AddAuthenticationHeader extends ZuulFilter {

    public static final String ANONYMOUS = "Anonymous";
    private static Logger logger = LoggerFactory.getLogger(AddAuthenticationHeader.class);
    private static final String X_AUTH_NAME = "X-AUTH-NAME";

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
        final String userName = ctx.getRequest().getHeader(X_AUTH_NAME);
        if (Objects.isNull(userName)) {
            //add multiple headers.. usually a service is called connected to db to authenticate the username
            ctx.addZuulRequestHeader(X_AUTH_NAME, ANONYMOUS);
            logger.info("Added header {}: in the request",ANONYMOUS);
        }
        return null;
    }

}
