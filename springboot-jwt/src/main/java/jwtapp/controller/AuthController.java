package jwtapp.controller;

import jwtapp.auth.TokenManager;
import jwtapp.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    private TokenManager tokenManager;
    //Tüm Olay'ın kontrolcüsü AuthenticationManager. Biz tüm security olaylarını ona tanıtırız ve ondan alırız. Hepsini ayarladık(Conf,TokenFilter,UserDetailsService,TokenManager)
    private AuthenticationManager authenticationManager; //WebSecurityConfiguration sınıfında bir instance'ını yaratmadan kullanamazsın.

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
       try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
           return ResponseEntity.ok(tokenManager.generateToken(loginRequest.getUsername()));

       }catch (Exception e){
           throw e;
       }
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }
}
