package com.bosch.gateway.filter;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UserGlobalFilter implements GlobalFilter, Ordered {
    public static final String ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER = "@ignoreUserGlobalFilter";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getAttribute(ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER) != null) {
            return chain.filter(exchange);}
        else {
            String tokenFromCookie = null;
            if (exchange.getRequest().getCookies().containsKey("token")) {
                tokenFromCookie = exchange.getRequest().getCookies().getFirst("token").getValue();
            }

            String tokenFromParam = exchange.getRequest().getQueryParams().getFirst("token");

            // 鉴权规则
            // 没有Token则返回
            if (StringUtils.isEmpty(tokenFromCookie) & StringUtils.isEmpty(tokenFromParam)) {
                String url = "http://bcsc.gateway.com:8888/user/login";
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.SEE_OTHER);
                response.getHeaders().set(HttpHeaders.LOCATION, url);
                return response.setComplete();
            }

            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
