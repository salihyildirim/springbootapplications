package com.example.springbootpostgresqlwebapp.repo;

import com.example.springbootpostgresqlwebapp.entity.Kisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KisiRepository extends JpaRepository<Kisi,Long> {
}
