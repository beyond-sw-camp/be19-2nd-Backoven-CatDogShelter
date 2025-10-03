package com.backoven.catdogshelter.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class AuthorizationHeaderFilter
        extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    private final Environment env;

    @Autowired
    public AuthorizationHeaderFilter(Environment env) {
        super(Config.class);
        this.env = env;
    }

    public static class Config { }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // Authorization 헤더 확인
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            // Bearer 파싱
            String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
                return onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
            }

            String jwt = bearerToken.substring(7);

            // JWT 유효성 검사
            if (!isJwtValid(jwt)) {
                return onError(exchange, "JWT Token is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private boolean isJwtValid(String jwt) {
        try {
            // === UserService 발급 방식과 동일하게 문자열 secret 사용 ===
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(env.getProperty("token.secret")) // 문자열 그대로
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            String subject = claims.getSubject();
            if (subject == null || subject.isEmpty()) {
                return false;
            }

            // 권한 확인
            Object rolesObj = claims.get("auth");
            if (rolesObj == null) {
                log.warn("토큰에 권한(auth)이 없음");
                return false;
            }

            String rolesStr = rolesObj.toString();
            log.info("토큰 권한(auth): {}", rolesStr);

            // 허용된 권한 목록
            List<String> allowedRoles = List.of("ROLE_USER", "ROLE_SHELTER");
            return allowedRoles.stream().anyMatch(rolesStr::contains);

        } catch (Exception e) {
            log.error("JWT 검증 실패: {}", e.getMessage());
            return false;
        }
    }

    private Mono<Void> onError(ServerWebExchange exchange,
                               String errorMessage,
                               HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        log.info("에러 메세지: {}", errorMessage);

        return response.setComplete();
    }
}
