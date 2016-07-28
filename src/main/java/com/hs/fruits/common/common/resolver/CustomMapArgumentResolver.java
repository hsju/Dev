package com.hs.fruits.common.common.resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.hs.fruits.common.common.CommandMap;

/**
 * 스프링 사용 시, 컨트롤러(Controller)에 들어오는 파라미터(Parameter)를 수정하거나 공통적으로 추가를 해주어야 하는 경우가
 * 있다. 예를 들어, 로그인을 한 사용자의 사용자 아이디나 닉네임등을 추가하는것을 생각해보자. 보통 그런 정보는 세션(Session)에
 * 담아놓고 사용하는데, DB에 그러한 정보를 입력할 때에는 결국 세션에서 값을 꺼내와서 파라미터로 추가를 해야한다. 그런 경우가 뭐 하나나
 * 두번 정도 있다면 몰라도, 여러번 사용되는 값을 그렇게 일일히 세션에서 가져오는건 상당히 번거로운 일이다.
 * HandlerMethodArgumentResolver 는 사용자 요청이 Controller에 도달하기 전에 그 요청의 파라미터들을 수정할
 * 수 있도록 해준다.
 * 
 * @author Ju
 *
 */
public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return CommandMap.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		CommandMap commandMap = new CommandMap();

		HttpServletRequest request = (HttpServletRequest) webRequest
				.getNativeRequest();
		Enumeration<?> enumeration = request.getParameterNames();

		String key = null;
		String[] values = null;
		while (enumeration.hasMoreElements()) {
			key = (String) enumeration.nextElement();
			values = request.getParameterValues(key);
			if (values != null) {
				commandMap.put(key, (values.length > 1) ? values : values[0]);
			}
		}
		return commandMap;
	}
}