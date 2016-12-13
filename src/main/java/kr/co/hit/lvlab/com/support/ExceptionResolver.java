package kr.co.hit.lvlab.com.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class ExceptionResolver extends SimpleMappingExceptionResolver{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		logger.error("#resolveException#ERROR::resolveException::", ex);
		System.out.println("#resolveException#ERROR::resolveException::"+ ex+"::####::"+request.getRequestURI());
		return super.resolveException(request, response, handler, ex);
	}
}