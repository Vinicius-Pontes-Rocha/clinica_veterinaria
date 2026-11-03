package br.com.fatec.veterinaria.service;

import br.com.fatec.veterinaria.model.Cachorro;
import br.com.fatec.veterinaria.model.Tutor;
import br.com.fatec.veterinaria.repository.CachorroRepository;
import br.com.fatec.veterinaria.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CachorroService {

    @Autowired
    private CachorroRepository cachorroRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public List<Cachorro> listarTodos() {
        return cachorroRepository.findAll();
    }

    public Cachorro buscarPorId(Long id) {
        return cachorroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cachorro não encontrado com o ID: " + id));
    }

    public List<Cachorro> listarPorTutor(Long tutorId) {
        return cachorroRepository.findByTutorId(tutorId);
    }

    @Transactional
    public Cachorro salvar(Cachorro cachorro, Long tutorId) {
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID: " + tutorId));
        cachorro.setTutor(tutor);
        return cachorroRepository.save(cachorro);
    }

    @Transactional
    public Cachorro atualizar(Long id, Cachorro cachorroAtualizado) {
        Cachorro cachorroExistente = buscarPorId(id);
        cachorroExistente.setNome(cachorroAtualizado.getNome());
        cachorroExistente.setIdade(cachorroAtualizado.getIdade());
        cachorroExistente.setPeso(cachorroAtualizado.getPeso());
        cachorroExistente.setRaca(cachorroAtualizado.getRaca());
        cachorroExistente.setPorte(cachorroAtualizado.getPorte());
        return cachorroRepository.save(cachorroExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Cachorro cachorro = buscarPorId(id);
        cachorroRepository.delete(cachorro);
    }
}
