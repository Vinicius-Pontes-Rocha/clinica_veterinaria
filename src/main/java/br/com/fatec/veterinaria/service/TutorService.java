package br.com.fatec.veterinaria.service;

import br.com.fatec.veterinaria.model.Tutor;
import br.com.fatec.veterinaria.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> listarTodos() {
        return tutorRepository.findAll();
    }

    public Tutor buscarPorId(Long id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + id));
    }

    @Transactional
    public Tutor salvar(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Transactional
    public Tutor atualizar(Long id, Tutor tutorAtualizado) {
        Tutor tutorExistente = buscarPorId(id);
        tutorExistente.setNome(tutorAtualizado.getNome());
        tutorExistente.setCpf(tutorAtualizado.getCpf());
        tutorExistente.setTelefone(tutorAtualizado.getTelefone());
        tutorExistente.setEmail(tutorAtualizado.getEmail());
        tutorExistente.setEndereco(tutorAtualizado.getEndereco());
        return tutorRepository.save(tutorExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Tutor tutor = buscarPorId(id);
        tutorRepository.delete(tutor);
    }
}
