package jwtapp.auth;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service //Database'e gider SpringAuthManager için User'i çeker.
//https://www.youtube.com/watch?v=NO4HR66oVg4&list=PLd0jsEi3hUAdJk49VWxFDRGFBjnrUMK59&index=6
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final Map<String, String> users = new HashMap<>(); //veritabani yerine manuel kullanicilar olusturdum ornek icin
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        users.put("salih", passwordEncoder.encode("123"));
    }

    //Spring Security(authManager), kullanıcı kimlik doğrulaması sırasında bu metodu çağırarak kullanıcı bilgilerini alır ve doğrulama işlemlerini gerçekleştirir.
    //orneğin JwtTokenFilter sınıfında aşağıdaki methodda username ile bu loadUserByUsername methodu tetiklenip UserDetails'i döndürüyor.
    //UsernamePasswordAuthenticationToken upassAuthToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (users.containsKey(username)) {
            return new User(username, users.get(username), new ArrayList<>()); //springin kendi User sinifi. yaratirken, name,passw,yetki veriyorsun
        }
        throw new UsernameNotFoundException("bulunamayan kullanici: " + username);
    }
}
