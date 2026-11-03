package br.com.fatec.veterinaria.repository;

import br.com.fatec.veterinaria.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Optional<Veterinario> findByCrmv(String crmv);
    Optional<Veterinario> findByCpf(String cpf);
}
