package com.example.springbootpostgresqlwebapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "kisi")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}) //iki aynı id'ye sahip Adres nesnesi varsa bunlar aynıdır.
@ToString
public class Kisi implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_kisi",allocationSize = 1)
    @GeneratedValue(generator = "seq_kisi", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "adi")
    private String adi;

    @Column(length = 100,name = "soyadi")
    private String soyadi;

    @OneToMany(mappedBy = "kisi", cascade = CascadeType.ALL, orphanRemoval = true)
    //mappedBy kullanildi.@JoinColumn gerek yok
    private List<Adres> adresleri;
}
