package com.example.springbootpostgresqlwebapp.mapper;

import com.example.springbootpostgresqlwebapp.dto.KisiDto;
import com.example.springbootpostgresqlwebapp.entity.Adres;
import com.example.springbootpostgresqlwebapp.entity.Kisi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface KisiMapper {

    @Mapping(source = "adresler", target = "adresleri")
    Kisi toEntity(KisiDto kisiDto);
    @Mapping(source = "adresleri", target = "adresler")
    KisiDto toDto(Kisi kisi);

    List<KisiDto> toDtoList(List<Kisi> kisiler);

    default List<Adres> mapAdresList(List<String> adresler) {
        return adresler.stream()
                .map(adres -> {
                    Adres adresObj = new Adres();
                    adresObj.setAdres(adres);
                    // Diğer gerekli alanları da ayarlayabilirsiniz
                    return adresObj;
                })
                .collect(Collectors.toList());
    }
    default List<String> mapAdresler(List<Adres> adresler) {
        return adresler.stream()
                .map(Adres::getAdres)
                .collect(Collectors.toList());
    }

}
