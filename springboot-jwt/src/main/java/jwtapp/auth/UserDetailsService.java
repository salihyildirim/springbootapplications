package jwtapp.auth;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final Map<String,String> users= new HashMap<>(); //veritabani yerine manuel kullanicilar olusturdum ornek icin

    BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        users.put("salihname", passwordEncoder.encode("123"));
    }

    //Spring Security(authManager), kullanıcı kimlik doğrulaması sırasında bu metodu çağırarak kullanıcı bilgilerini alır ve doğrulama işlemlerini gerçekleştirir.
    // orneğin JwtTokenFilter sınıfında aşağıdaki methodda username ile bu loadUserByUsername methodu tetiklenip UserDetails'i döndürüyor.
    //UsernamePasswordAuthenticationToken upassAuthToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(users.containsKey(username)){
            return new User(username,users.get(username),new ArrayList<>()); //springin kendi User sinifi. yaratirken, name,passw,yetki veriyorsun
        }
        throw new UsernameNotFoundException("bulunamayan kullanici: "+username);
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
