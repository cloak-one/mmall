package org.example.mmall.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.mmall.exception.Asserts;
import org.example.mmall.model.User;
import org.example.mmall.response.UserVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 */
@Configuration
public class JwtTokenUtil {
//    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_ID = "userid";
    private static final String CLAIM_KEY_PHONE = "phone";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据负责生成JWT的token
     */
    private String getToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS512")
                .signWith(SignatureAlgorithm.HS512, secret)
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
//            LOGGER.info("JWT格式验证失败:{}", token);
            Asserts.fail("JWT格式验证失败");
        }
        return claims;
    }

    /**
     * 从token中能否解析负载
     */
    public boolean verify(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从token中获取登录用户名
     */
    public UserVO decodeUser(String token) {
        UserVO userVO = null;
        try {
            Claims claims = getClaimsFromToken(token);
            String phone = claims.get(CLAIM_KEY_PHONE).toString();
            Long id = Long.valueOf(claims.get(CLAIM_KEY_ID).toString());
            userVO = new UserVO(id,phone);
        } catch (Exception e) {
//            LOGGER.info("JWT解析失败:{}",token);
            Asserts.fail("JWT解析失败");

        }
        return userVO;
    }
    /**
     * 根据用户id和phone生成token
     */
    public String getToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_ID,user.getId());
        claims.put(CLAIM_KEY_PHONE, user.getPhone());
        return getToken(claims);
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param user 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, User user) {
        String phone = decodeUser(token).getPhone();
        return phone.equals(user.getPhone())&&!isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

}
