package br.com.fatec.veterinaria.repository;

import br.com.fatec.veterinaria.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    Optional<Especialidade> findByNomeIgnoreCase(String nome);
}
