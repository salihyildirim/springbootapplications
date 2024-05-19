package com.example.springbootpostgresqlwebapp.controller;

import com.example.springbootpostgresqlwebapp.dto.KisiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springbootpostgresqlwebapp.service.KisiService;

import java.util.List;

@RestController
@RequestMapping("/kisi")
@RequiredArgsConstructor
public class KisiController {

    private final KisiService kisiService;

    @PostMapping
    public ResponseEntity<KisiDto> kaydet (@RequestBody KisiDto kisiDto){
        return ResponseEntity.ok(kisiService.save(kisiDto));
    }

    @GetMapping
    public ResponseEntity<List<KisiDto>> tumunuListele(){
        System.out.println("get tumunuListele calisti");
        return ResponseEntity.ok(kisiService.getAll());
    }
}
