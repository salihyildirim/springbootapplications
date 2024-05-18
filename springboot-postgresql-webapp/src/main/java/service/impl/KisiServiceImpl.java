package service.impl;

import dto.KisiDto;
import entity.Adres;
import entity.Kisi;
import lombok.RequiredArgsConstructor;
import mapper.KisiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repo.AdresRepository;
import repo.KisiRepository;
import service.KisiService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KisiServiceImpl implements KisiService {

    private final KisiRepository kisiRepository;
    private final AdresRepository adresRepository;
    private final KisiMapper kisiMapper;
    @Override
    @Transactional
    public KisiDto save(KisiDto kisiDto) {
        Assert.isNull(kisiDto.getAdi(), "Ad alani zorunldur");

        Kisi kisi = kisiMapper.toEntity(kisiDto);
        final Kisi kisiDb= kisiRepository.save(kisi);

        List<Adres> liste = new ArrayList<>();
        kisiDto.getAdresler().forEach(item -> {
            Adres adres = new Adres();
            adres.setAdres(item);
            adres.setAdresTip(Adres.AdresTip.DIGER);
            adres.setAktif(Boolean.TRUE);
            adres.setKisi(kisiDb);
            liste.add(adres);
        });

        adresRepository.saveAll(liste);
        kisiDto.setId(kisiDb.getId());
        return kisiDto;

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<KisiDto> getAll() {
        List<Kisi> kisiList= kisiRepository.findAll();
        return kisiMapper.toDtoList(kisiList);

    }


    @Override
    public Page<KisiDto> getAll(Pageable pageable) {
        return null;
    }
}
