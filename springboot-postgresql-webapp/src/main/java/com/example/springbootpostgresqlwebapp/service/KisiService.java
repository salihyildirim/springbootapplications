package com.example.springbootpostgresqlwebapp.service;

import com.example.springbootpostgresqlwebapp.dto.KisiDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KisiService {
    KisiDto save(KisiDto kisiDto);
    void delete(Long id);
    List<KisiDto> getAll();
    Page<KisiDto> getAll(Pageable pageable);
}
