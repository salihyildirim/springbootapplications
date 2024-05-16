package com.haydikodlayalim.controller;

import com.haydikodlayalim.entity.Kisi;
import com.haydikodlayalim.repository.KisiRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor //kisirepository inject'ini de sağlar
@RestController
@RequestMapping("/kisi")
public class KisiController {

   private final KisiRepository kisiRepository;

   @PostConstruct
   public void init(){
       Kisi kisi = new Kisi();
       kisi.setAd("salih");
       kisi.setSoyad("yildirim");
       kisi.setAdres("adres123");
       kisi.setDogumTarih(Calendar.getInstance().getTime());
       kisi.setId("K001");
       kisiRepository.save(kisi);
   }


   @GetMapping("/{search}")
   ResponseEntity<List<Kisi>> getKisi(@PathVariable String search){
       List<Kisi> kisiler= kisiRepository.findByAdLikeOrSoyadLike(search,search);
       return ResponseEntity.ok(kisiler);
   }


}
