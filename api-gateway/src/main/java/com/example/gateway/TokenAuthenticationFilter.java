package com.example.gateway;

import com.example.common.context.CommonException;
import com.example.common.context.ServiceContext;
import com.example.common.context.UserToken;
import com.example.common.util.JWTUtils;
import com.example.common.util.SerializeUtil;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class TokenAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    CircuitBreakerConfig circuitBreakerConfig;

    static List<String> domains;

    static {
        String authCookieDomain = System.getenv("AUTH_COOKIE_DOMAIN");
        if (StringUtils.isEmpty(authCookieDomain)) {
            authCookieDomain = "localhost";
        }
        domains = Arrays.asList(authCookieDomain.split(","));
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        if (FilterCommon.skipRequest(request)) {
            return chain.filter(exchange);
        }

        //all other cases are accessing protected resources
        HttpCookie authSessionCookie = request.getCookies().getFirst(ServiceContext.TOKEN_HEADER);
        String jwtStr = null;
        if (authSessionCookie != null) {
            jwtStr = authSessionCookie.getValue();
        }

        if (StringUtils.isEmpty(jwtStr)) {
            HttpHeaders headers = request.getHeaders();
            jwtStr = headers.getFirst(ServiceContext.TOKEN_HEADER);

            if (StringUtils.isEmpty(jwtStr)) {
                return Mono.error(new CommonException(401, "Please login", 401));
            }
        }

        //validate the token
        UserToken user = JWTUtils.extractToken(jwtStr);

        // the Token is gonna expire in 4hours, help user to refresh token
        if (LocalDateTime.now().plusHours(16).isAfter(user.getExpireAt())) {
            log.info("refreshUserToken +++++++++++++++++++++++++ refreshUserToken");

            String newToken = JWTUtils.createToken(user.getId(), user.getName());
            domains.forEach(domain ->
                    exchange.getResponse()
                            .addCookie(ResponseCookie.from(ServiceContext.TOKEN_HEADER, newToken)
                                    .domain(domain)
                                    .path("/")
                                    .httpOnly(true)
                                    .build())
            );
            UserToken newUser = JWTUtils.extractToken(newToken);
            return continueFilter(newUser, exchange, chain);
        }
        return continueFilter(user, exchange, chain);
    }


    Mono<Void> continueFilter(UserToken user, ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest newRequest = exchange.getRequest().mutate()
                .header(ServiceContext.AUTHENTICATED_HEADER, SerializeUtil.serialize(user))
                .build();

        ServerWebExchange newExchange = exchange.mutate()
                .request(newRequest).principal(Mono.just(user)).build();

        return chain.filter(newExchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 500;
    }
}
