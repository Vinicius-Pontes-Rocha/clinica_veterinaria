package br.com.fatec.veterinaria.service;

import br.com.fatec.veterinaria.model.Gato;
import br.com.fatec.veterinaria.model.Tutor;
import br.com.fatec.veterinaria.repository.GatoRepository;
import br.com.fatec.veterinaria.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GatoService {

    @Autowired
    private GatoRepository gatoRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public List<Gato> listarTodos() {
        return gatoRepository.findAll();
    }

    public Gato buscarPorId(Long id) {
        return gatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gato não encontrado com o ID: " + id));
    }

    public List<Gato> listarPorTutor(Long tutorId) {
        return gatoRepository.findByTutorId(tutorId);
    }

    @Transactional
    public Gato salvar(Gato gato, Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + tutorId));
        gato.setTutor(tutor);
        return gatoRepository.save(gato);
    }

    @Transactional
    public Gato atualizar(Long id, Gato gatoAtualizado) {
        Gato gatoExistente = buscarPorId(id);
        gatoExistente.setNome(gatoAtualizado.getNome());
        gatoExistente.setIdade(gatoAtualizado.getIdade());
        gatoExistente.setPeso(gatoAtualizado.getPeso());
        gatoExistente.setPelagem(gatoAtualizado.getPelagem());
        gatoExistente.setCastrado(gatoAtualizado.getCastrado());
        return gatoRepository.save(gatoExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Gato gato = buscarPorId(id);
        gatoRepository.delete(gato);
    }
}
