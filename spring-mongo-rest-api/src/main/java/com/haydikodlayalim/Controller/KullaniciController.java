package com.haydikodlayalim.Controller;

import com.haydikodlayalim.entity.Kullanici;
import com.haydikodlayalim.repository.KullaniciRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kullanici")
public class KullaniciController {
    private KullaniciRepository kullaniciRepository;

@PostConstruct
void init(){
        Kullanici kullanici= new Kullanici();
        kullanici.setAdi("Selam");
        kullanici.setSoyadi("Naber");
        kullaniciRepository.save(kullanici);
    }

    @Autowired
    KullaniciController(KullaniciRepository kullaniciRepository){
        this.kullaniciRepository=kullaniciRepository;
    }
    @PostMapping
    public ResponseEntity<Kullanici> ekle(@RequestBody Kullanici kullanici){
        return ResponseEntity.ok(kullaniciRepository.save(kullanici));
    }

    @GetMapping
    public ResponseEntity<List<Kullanici>> tumunuListele (){
        return ResponseEntity.ok(kullaniciRepository.findAll());
    }
}
