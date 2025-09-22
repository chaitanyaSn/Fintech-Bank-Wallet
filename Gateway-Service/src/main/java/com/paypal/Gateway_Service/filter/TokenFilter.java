package com.paypal.Gateway_Service.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class TokenFilter extends AbstractGatewayFilterFactory<TokenFilter.Config> {

    private static final String SECRET = "ubLgVHPbtgzD8qKXqBWRjgrGxJp27VXNxvuKR3dvNPvMyUxDXo0LC5cYFGQBpyQw56y6Xe8xHkxU2lD4N1TM7w==";

    public TokenFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange,chain)->{
            String path=exchange.getRequest().getPath().toString();
            if(path.equals("/user/login") || path.equals("user/register")){
                return chain.filter(exchange.mutate().request(r->r.header("X-SECRET-KEY","SECRET")).build());
            }
            HttpHeaders header = exchange.getRequest().getHeaders();
            if(!header.containsKey(HttpHeaders.AUTHORIZATION)){
                throw new RuntimeException("Missing authorization header");
            }
            String authHeader = header.getFirst(HttpHeaders.AUTHORIZATION);
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                throw new RuntimeException("Missing or invalid authorization header");
            }
            String token = authHeader.substring(7);
            try{
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET).parseClaimsJws(token).getBody();
                exchange=exchange.mutate().request(r->r.header("X-SECRET-KEY","SECRET")).build();

            }catch (Exception e){
                throw new RuntimeException("Invalid token");
            }
            return chain.filter(exchange);
        };
    }

    public static class Config{

    }

}
