package com.back.infocapitos_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.infocapitos_back.model.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

}
