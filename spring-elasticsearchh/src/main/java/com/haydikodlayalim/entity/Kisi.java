package com.haydikodlayalim.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "kisiler") // http://localhost:9200/kisiler ile elasticsearch'ın nasıl bu entitiy'i depoladigini görebilirsin.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Kisi {
    @Id
    private String id;
    @Field(name = "ad",type = FieldType.Text)
    private String ad;
    @Field(name = "soyad",type = FieldType.Text)
    private String soyad;
    @Field(name = "adres",type = FieldType.Text)
    private String adres;
    @Field(name = "dogum_tarihi",type = FieldType.Date)
    private Date dogumTarih;
}
