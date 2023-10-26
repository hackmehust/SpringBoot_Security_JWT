package ra.springboot_security_jwt.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ra.springboot_security_jwt.security.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        // Kiem tra xem header Authorization co chua thong tin jwt khong
        // import org.springframework.util.StringUtils;
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Lay JWT tu request
            String jwt = getJwtFromRequest(request);
            if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
                // Lay userName tu chuoi JWT
                String userName = jwtTokenProvider.getUserNameFromJwt(jwt);

                // Security se lam viec voi UserDetails
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

                if (userDetails != null) { // That ra cho~ nay` thua`. Vi ham loadUserByUserName o tren da~ Exception neu truong hop khong tim thay user nao.
                    // Neu nguoi dung hop le set thong tin cho security context
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // userDetails.getAuthorities(): lay cac quyen cua user
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Dia chi IP, phien lam viec, ....
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception ex) {
            logger.error("fail on set user authentication", ex);
        }
        filterChain.doFilter(request, response);
    }
}
