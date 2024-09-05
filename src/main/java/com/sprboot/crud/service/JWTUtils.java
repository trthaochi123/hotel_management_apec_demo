package com.sprboot.crud.service;

import com.auth0.jwt.interfaces.Claim;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

@Component
//  tạo, mã hóa, giải mã, và xác thực JWT
public class JWTUtils {
    private SecretKey Key;
    private static final long EXPIRATION_TIME = 86400000;  //24hours

    public JWTUtils() {
        //chuỗi bí mật được sử dụng để tạo khóa mã hóa cho JWT.
        String secreteString = "99839458983949692352523592548369389384968948639HJHJHJVDKN989892352352523589";

        //giải mã chuỗi base64 (dưới dạng mảng byte) đã được mã hóa trước đó.
        byte[] keyBytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));

        //tạo ra một đối tượng SecretKeySpec sử dụng mảng byte đã được giải mã (keyBytes)
        // và chỉ định thuật toán mã hóa là "HmacSHA256".
        this.Key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                // thiết lập các claims trong payload
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))

                // Ký khóa bí mật để tạo Signature
                .signWith(Key)

                // Kết thúc và trả về chuỗi JWT hoàn chỉnh dưới dạng string
                .compact();
    }

    public String generateRefreshToken(HashMap<String, Objects> claims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }


    // Trích xuất username (subject) từ token
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimTFunction) {
        return claimTFunction.apply(Jwts.parser().verifyWith(Key).build().parseSignedClaims(token).getPayload());
    }

    // check Token hợp lệ khi username trong token trùng với username trong UserDetails và token chưa hết hạn.
    public boolean isTokenValid (String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

    // check so sánh thời gian hết hạn của token với thời gian hiện tại
    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
