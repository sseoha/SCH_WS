package kr.co.hit.lvlab.com.support;

import java.util.Iterator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ParamCollectorArgumentResolver implements HandlerMethodArgumentResolver{
	
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // TODO Auto-generated method stub
        return ParamCollector.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        // TODO Auto-generated method stub
        ParamCollector collector = new ParamCollector();
        System.out.println(webRequest.getNativeRequest().toString());
        for(Iterator<String> iterator = webRequest.getParameterNames(); iterator.hasNext();){
        	
            String key = iterator.next();
            System.out.println("key="+key);
            collector.put(key, webRequest.getParameter(key));
        }
        return collector;
    }

}