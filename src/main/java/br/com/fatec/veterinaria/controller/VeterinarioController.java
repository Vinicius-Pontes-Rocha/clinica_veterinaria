package br.com.fatec.veterinaria.controller;

import br.com.fatec.veterinaria.model.Veterinario;
import br.com.fatec.veterinaria.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping
    public ResponseEntity<List<Veterinario>> listarTodos() {
        return ResponseEntity.ok(veterinarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(veterinarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Veterinario> salvar(@RequestBody Veterinario veterinario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinarioService.salvar(veterinario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinario> atualizar(@PathVariable Long id, @RequestBody Veterinario veterinario) {
        return ResponseEntity.ok(veterinarioService.atualizar(id, veterinario));
    }

    @PostMapping("/{id}/especialidades/{especialidadeId}")
    public ResponseEntity<Veterinario> associarEspecialidade(@PathVariable Long id, @PathVariable Long especialidadeId) {
        return ResponseEntity.ok(veterinarioService.associarEspecialidade(id, especialidadeId));
    }

    @DeleteMapping("/{id}/especialidades/{especialidadeId}")
    public ResponseEntity<Veterinario> desassociarEspecialidade(@PathVariable Long id, @PathVariable Long especialidadeId) {
        return ResponseEntity.ok(veterinarioService.desassociarEspecialidade(id, especialidadeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        veterinarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
