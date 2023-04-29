package indi.gd.pcmw.controller.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import indi.gd.pcmw.domain.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

public class JwtUtil {
    private static final String privateKey = "a1804351@!A*i^c02G%$$!89)op";

    public static String getToken(User user){
        Instant datetime = LocalDateTime.now().plusDays(1).toInstant(OffsetDateTime.now().getOffset());
        Date expireTime = Date.from(datetime);
        String token;
        token = JWT.create()
                .withClaim("role","admin")
                .withClaim("loginName",user.getLoginName())
                .withIssuedAt(new Date())
                .withExpiresAt(expireTime)
                .sign(Algorithm.HMAC256(privateKey));

        return token;
    }

    public static int verifyToken(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(privateKey)).build();
        try {
            jwtVerifier.verify(token);
        }catch (TokenExpiredException exception){
            return 1;
        }catch (JWTVerificationException exception){
            return 2;
        }
        return 0;
    }
}
