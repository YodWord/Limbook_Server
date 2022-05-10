package com.daelim.Limbook.web.argumentResolver;

import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResovler implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        log.info("supportsParameter 실행");// 캐시에 저장되어 supportsParameter는 한번만 실행

        // @Login 커스텀 어노테이션 여부 확인
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);

        // sessionId는 문자열이므로 String 타입인지 확인
        boolean hasStringType = User.class.isAssignableFrom(parameter.getParameterType());

        // 두 개 다 만족해야지 해당 ArgumentResolver가 지원하는 타입
        return hasLoginAnnotation && hasStringType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        log.info("resolveArgument 실행");
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        return session.getAttribute(SessionConst.LOGIN_USER);
    }
}
