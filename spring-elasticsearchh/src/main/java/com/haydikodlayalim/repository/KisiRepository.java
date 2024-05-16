package com.haydikodlayalim.repository;

import com.haydikodlayalim.entity.Kisi;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KisiRepository extends ElasticsearchRepository<Kisi,String> {
    @Query("{\"bool\": {\"must\": [{\"match\": {\"ad\": \"?0\"}}]}}")
    List<Kisi> getByCustomerQuery(String search);

    //spring repository'lerinde method adı asagidaki gibi bir data formatiyla kullanilabilir.
    //elastic search icin de kullanabiliyoruz. control + space basarak alternaifleri görebilirsin.
    List<Kisi> findByAdLikeOrSoyadLike(String ad,String soyad);


}
