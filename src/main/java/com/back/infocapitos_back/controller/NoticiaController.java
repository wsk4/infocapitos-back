package com.back.infocapitos_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.infocapitos_back.model.Noticia;
import com.back.infocapitos_back.service.NoticiaService;

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping
    public ResponseEntity<List<Noticia>> getAllNoticias() {
        List<Noticia> noticias = noticiaService.findAll();
        if (noticias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(noticias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Noticia> getNoticiaById(@PathVariable Integer id) {
        Noticia noticia = noticiaService.findById(id);
        if (noticia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(noticia);
    }

    @PostMapping
    public ResponseEntity<Noticia> createNoticia(@RequestBody Noticia noticia) {
        Noticia createdNoticia = noticiaService.save(noticia);
        return ResponseEntity.status(201).body(createdNoticia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Noticia> updateNoticia(@PathVariable Integer id, @RequestBody Noticia noticia) {
        noticia.setId(id);
        Noticia updatedNoticia = noticiaService.save(noticia);
        if (updatedNoticia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedNoticia);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Noticia> partialUpdateNoticia(@PathVariable Integer id, @RequestBody Noticia noticia) {
        Noticia existingNoticia = noticiaService.findById(id);
        if (existingNoticia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(noticiaService.partialUpdate(noticia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoticia(@PathVariable Integer id) {
        noticiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
