package com.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.util.RequestContentDataExtractor;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean shouldFilter() {		//tells whether this filter must be executed. 
		return true;
	}

	@Override
	public Object run() throws ZuulException {		//here we can mention what we want to log
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {} request uri -> {}", request, request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {	//indicates when filtering should happen, post, pre request execution or only error requests.
		return "pre";
	}

	@Override
	public int filterOrder() {		//If we have multiple filters, we can set a priority order between them.
		return 1;
	}

}
