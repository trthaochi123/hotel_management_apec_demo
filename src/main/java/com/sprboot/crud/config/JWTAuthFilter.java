package com.sprboot.crud.config;

import com.sprboot.crud.service.JWTUtils;
import com.sprboot.crud.service.OurUserDetailsService;
import com.sprboot.crud.service.impl.RoomTypeImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
// tạo filter để kiểm tra xem JWT token có hợp lệ không
// OncePerRequestFilter: đảm bảo rằng filter này chỉ được thực thi một lần cho mỗi yêu cầu (request).
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private OurUserDetailsService ourUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // lấy header từ yêu cầu HTTP
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        // kiểm tra header có hợp lệ không
        if( authHeader == null || authHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        // trích xuất token từ header, lấy từ vị trí số 7, bỏ phần Bearer đi
        jwtToken = authHeader.substring(7);

        // trích xuất username(email) user từ token
        userEmail = jwtUtils.extractUsername(jwtToken);

        // nếu email hợp lệ và chưa có xác thực trong SecurityContextHolder thì gọi phuong thuc loadUserByUsername của
        // ourUserDetailsService để tải thông tin người dùng từ CSDL
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = ourUserDetailsService.loadUserByUsername(userEmail);

            // token được kiểm tra xem hợp lệ ko, có thuộc user này ko và đã hết hạn chưa
            if(jwtUtils.isTokenValid(jwtToken, userDetails)) {

                // xác thực trong SecurityContextHolder nếu email hợp lệ --> cấp quyền tương ứng
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        // tiếp tục chuỗi filter vs các yêu cầu và phản hồi khác
        filterChain.doFilter(request, response);
    }
}
