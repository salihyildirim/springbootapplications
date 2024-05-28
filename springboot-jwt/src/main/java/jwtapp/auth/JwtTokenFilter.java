package jwtapp.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    /* 1- İstek üzerinde Authorization başlığı kontrol edilir ve JWT token alınır.
2 -Token geçerliyse ve kimlik doğrulaması yapılmamışsa, kullanıcı doğrulanır ve SecurityContextHolder'a kimlik bilgileri yerleştirilir.
3 - filterChain.doFilter(request, response);: Bu, filtre zincirinde bir sonraki filtreye geçiş yapar.*/

    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /**
         * "Bearer 123abc321
         */
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (authHeader != null && authHeader.contains("Bearer")) {
            token = authHeader.substring(7);
            try {
                username = tokenManager.getUsernameToken(token);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (username != null && token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (tokenManager.tokenValidate(token)) {
                UsernamePasswordAuthenticationToken upassAuthToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>()); //userDetailsService username parametre olarak gönderilir ve user çekilir. authManager yapıyor bu işi.
                upassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // istemcinin IP adresi, oturum kimliği gibi durumlarını detay olarak ekle.

                SecurityContextHolder.getContext().setAuthentication(upassAuthToken); //kimlik dogrulamasi tamamlandı, spring contex'e bildir ve ata.

            }
        }
        filterChain.doFilter(request,response);
    }

    @Autowired
    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }
}
