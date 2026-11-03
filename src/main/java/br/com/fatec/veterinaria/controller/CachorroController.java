package br.com.fatec.veterinaria.controller;

import br.com.fatec.veterinaria.model.Cachorro;
import br.com.fatec.veterinaria.service.CachorroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cachorros")
public class CachorroController {

    @Autowired
    private CachorroService cachorroService;

    @GetMapping
    public ResponseEntity<List<Cachorro>> listarTodos() {
        return ResponseEntity.ok(cachorroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cachorro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cachorroService.buscarPorId(id));
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<Cachorro>> listarPorTutor(@PathVariable Long tutorId) {
        return ResponseEntity.ok(cachorroService.listarPorTutor(tutorId));
    }

    @PostMapping
    public ResponseEntity<Cachorro> salvar(@RequestBody Cachorro cachorro, @RequestParam Long tutorId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cachorroService.salvar(cachorro, tutorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cachorro> atualizar(@PathVariable Long id, @RequestBody Cachorro cachorro) {
        return ResponseEntity.ok(cachorroService.atualizar(id, cachorro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cachorroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
