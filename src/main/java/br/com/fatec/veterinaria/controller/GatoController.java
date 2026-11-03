package br.com.fatec.veterinaria.controller;

import br.com.fatec.veterinaria.model.Gato;
import br.com.fatec.veterinaria.service.GatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gatos")
public class GatoController {

    @Autowired
    private GatoService gatoService;

    @GetMapping
    public ResponseEntity<List<Gato>> listarTodos() {
        return ResponseEntity.ok(gatoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gato> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(gatoService.buscarPorId(id));
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<Gato>> listarPorTutor(@PathVariable Long tutorId) {
        return ResponseEntity.ok(gatoService.listarPorTutor(tutorId));
    }

    @PostMapping
    public ResponseEntity<Gato> salvar(@RequestBody Gato gato, @RequestParam Long tutorId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gatoService.salvar(gato, tutorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gato> atualizar(@PathVariable Long id, @RequestBody Gato gato) {
        return ResponseEntity.ok(gatoService.atualizar(id, gato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        gatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
