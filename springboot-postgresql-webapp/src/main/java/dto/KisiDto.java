package dto;

import entity.Adres;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class KisiDto {

    private Long id;
    private String adi;
    private String soyadi;
    private List<String> adresler;
}
