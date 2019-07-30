package com.beiming.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class TokenFilter extends ZuulFilter{

	//是否执行该过滤器，自己自定义自己的逻辑，此处表示全部执行
	@Override
	public boolean shouldFilter() {
		return true;
	}

	//过滤器具体的逻辑
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("token");
        String header = request.getHeader("token");
        if(token == null && header == null) {      
			if (ctx.getResponseBody() == null) {
			    ctx.setSendZuulResponse(false);  //关闭Zuul的返回
			    ctx.setResponseStatusCode(401);
			    ctx.setResponseBody("token is null"); //返回的消息体
			}
        }
		return null;  //return null 表示通过过滤，继续执行其它逻辑
	}

	/**
	 * pre：路由之前
	 * routing：路由之时
	 * post： 路由之后
	 * error：发送错误调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}
	//过滤器级别 数字越大，级别越低
	@Override
	public int filterOrder() {
		return 0;
	}

}
