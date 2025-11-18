package com.back.infocapitos_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.infocapitos_back.model.Noticia;
import com.back.infocapitos_back.repository.NoticiaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public List<Noticia> findAll() {
        return noticiaRepository.findAll();
    }

    public Noticia findById(Integer id) {
        return noticiaRepository.findById(id).orElse(null);
    }

    public Noticia save(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public Noticia partialUpdate(Noticia noticia) {
        Noticia existing = noticiaRepository.findById(noticia.getId()).orElse(null);
        if (existing != null) {
            if (noticia.getTitulo() != null) {
                existing.setTitulo(noticia.getTitulo());
            }
            if (noticia.getDescripcion() != null) {
                existing.setDescripcion(noticia.getDescripcion());
            }
            return noticiaRepository.save(existing);
        }
        return null;
    }

    public void deleteById(Integer id) {
        noticiaRepository.deleteById(id);
    }
}
