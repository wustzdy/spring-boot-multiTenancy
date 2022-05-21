package com.wust.spring.boot.multi.tenant.bean.others;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

import static com.wust.spring.boot.multi.tenant.bean.others.BasicCloudConstants.X_CLIENT_TOKEN_USER;

@Data
@Component
public class JwtUtils {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String secret = "123456";
    private long expire = 604800;
    private String header = "x-token";
    private String subject = "api-test";

    /**
     * 生成jwt token
     */
    public String generateToken(String username) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成jwt token
     */
    public String generateToken() {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * 鉴权服务token
     */
    public String generateAuthToken(Map<String, Object> content) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(content)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public Claims getAuthClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    /**
     * 校验是否是subject
     */
    public boolean isTokenSubject(String subject) {
        if (StringUtils.isBlank(subject)) {
            return false;
        }
        return getSubject().equals(subject);
    }

    public boolean isNotTokenSubject(String subject) {
        return !isTokenSubject(subject);
    }

    public static void main(String[] args) {
        String secret = "123456";
        long expire = 604800;
        String header = "x-token";
        String subject = "api-test";

        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject("zhangsan").claim(X_CLIENT_TOKEN_USER, "zhangsan")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        System.out.println("token:" + token);
    }
}
