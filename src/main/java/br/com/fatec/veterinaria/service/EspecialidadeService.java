package br.com.fatec.veterinaria.service;

import br.com.fatec.veterinaria.model.Especialidade;
import br.com.fatec.veterinaria.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<Especialidade> listarTodos() {
        return especialidadeRepository.findAll();
    }

    public Especialidade buscarPorId(Long id) {
        return especialidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada com o ID: " + id));
    }

    @Transactional
    public Especialidade salvar(Especialidade especialidade) {
        return especialidadeRepository.save(especialidade);
    }

    @Transactional
    public Especialidade atualizar(Long id, Especialidade espAtualizada) {
        Especialidade espExistente = buscarPorId(id);
        espExistente.setNome(espAtualizada.getNome());
        espExistente.setDescricao(espAtualizada.getDescricao());
        return especialidadeRepository.save(espExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Especialidade esp = buscarPorId(id);
        especialidadeRepository.delete(esp);
    }
}
