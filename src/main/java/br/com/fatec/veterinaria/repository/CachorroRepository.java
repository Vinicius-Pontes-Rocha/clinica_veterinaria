package br.com.fatec.veterinaria.repository;

import br.com.fatec.veterinaria.model.Cachorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Long> {
    List<Cachorro> findByTutorId(Long tutorId);
}
