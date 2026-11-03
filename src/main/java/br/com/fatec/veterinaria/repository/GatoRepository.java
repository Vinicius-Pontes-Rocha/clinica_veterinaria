package br.com.fatec.veterinaria.repository;

import br.com.fatec.veterinaria.model.Gato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatoRepository extends JpaRepository<Gato, Long> {
    List<Gato> findByTutorId(Long tutorId);
}
