package br.com.fatec.veterinaria.service;

import br.com.fatec.veterinaria.model.Especialidade;
import br.com.fatec.veterinaria.model.Veterinario;
import br.com.fatec.veterinaria.repository.EspecialidadeRepository;
import br.com.fatec.veterinaria.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<Veterinario> listarTodos() {
        return veterinarioRepository.findAll();
    }

    public Veterinario buscarPorId(Long id) {
        return veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado com o ID: " + id));
    }

    @Transactional
    public Veterinario salvar(Veterinario veterinario) {
        if (veterinario.getEspecialidades() != null && !veterinario.getEspecialidades().isEmpty()) {
            Set<Especialidade> vinculadas = new HashSet<>();
            for (Especialidade esp : veterinario.getEspecialidades()) {
                if (esp.getId() != null) {
                    especialidadeRepository.findById(esp.getId()).ifPresent(vinculadas::add);
                }
            }
            veterinario.setEspecialidades(vinculadas);
        }
        return veterinarioRepository.save(veterinario);
    }

    @Transactional
    public Veterinario atualizar(Long id, Veterinario vetAtualizado) {
        Veterinario vetExistente = buscarPorId(id);
        vetExistente.setNome(vetAtualizado.getNome());
        vetExistente.setCpf(vetAtualizado.getCpf());
        vetExistente.setTelefone(vetAtualizado.getTelefone());
        vetExistente.setEmail(vetAtualizado.getEmail());
        vetExistente.setCrmv(vetAtualizado.getCrmv());
        return veterinarioRepository.save(vetExistente);
    }

    @Transactional
    public Veterinario associarEspecialidade(Long veterinarioId, Long especialidadeId) {
        Veterinario vet = buscarPorId(veterinarioId);
        Especialidade esp = especialidadeRepository.findById(especialidadeId)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada com o ID: " + especialidadeId));
        vet.getEspecialidades().add(esp);
        return veterinarioRepository.save(vet);
    }

    @Transactional
    public Veterinario desassociarEspecialidade(Long veterinarioId, Long especialidadeId) {
        Veterinario vet = buscarPorId(veterinarioId);
        vet.getEspecialidades().removeIf(e -> e.getId().equals(especialidadeId));
        return veterinarioRepository.save(vet);
    }

    @Transactional
    public void deletar(Long id) {
        Veterinario vet = buscarPorId(id);
        veterinarioRepository.delete(vet);
    }
}
