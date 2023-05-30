package sopt.org.fouthSeminar.config.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sopt.org.fouthSeminar.config.jwt.JwtService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class UserIdResolver implements HandlerMethodArgumentResolver {
    // Method Argument 스코프에서의 Resolver
    private final JwtService jwtService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        /**
         * @UserId 가 부착되어 있는 파라미터인지
         * && Long 타입 데이터가 맞는지
         */
        return parameter.hasParameterAnnotation(UserId.class)
                && Long.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        /**
         * Request 내 Header 에서 Token 가져오기
         */
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final String token = request.getHeader("Authorization").split(" ")[1];

        // 토큰 검증
        if (!jwtService.verifyToken(token)) {
            throw new RuntimeException(
                    String.format(
                            "USER_ID를 가져오지 못했습니다. (%s - %s)"
                            , parameter.getClass(), parameter.getMethod()
                    )
            );
        }

        // 유저 아이디 반환
        final String tokenContents = jwtService.getJwtContents(token);
        try {
            return Long.parseLong(tokenContents); // 올바른 값 객체는 반환
        } catch (NumberFormatException e) {
            // 예외 발생 시 Exception 객체 반환
            throw new RuntimeException(
                    String.format(
                            "USER_ID를 가져오지 못했습니다. (%s - %s)"
                            , parameter.getClass(), parameter.getMethod()
                    )
            );
        }
    }
}
