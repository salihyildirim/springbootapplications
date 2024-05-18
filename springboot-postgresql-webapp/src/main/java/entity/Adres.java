package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "kisi_adres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}) //iki aynı id'ye sahip Adres nesnesi varsa bunlar aynıdır.
@ToString
public class Adres implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_kisi_adres",allocationSize = 1)
    @GeneratedValue(generator = "seq_kisi_adres", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 500, name = "adres")
    private String adres;
    @Enumerated
    private AdresTip adresTip;
    @Column(name = "adres")
    private Boolean aktif;

    public enum AdresTip {
        EV_ADRESI,
        IS_ADRESI,
        DIGER
    }

    @ManyToOne
    @JoinColumn(name = "kisi_id")
    private Kisi kisi;
}
