package br.com.fatec.veterinaria;

import br.com.fatec.veterinaria.model.*;
import br.com.fatec.veterinaria.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClinicaVeterinariaApplicationTests {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private CachorroRepository cachorroRepository;

    @Autowired
    private GatoRepository gatoRepository;

    @Test
    @DisplayName("Deve carregar o contexto da aplicação")
    void contextLoads() {
        assertNotNull(tutorRepository);
        assertNotNull(veterinarioRepository);
        assertNotNull(especialidadeRepository);
    }

    @Test
    @DisplayName("Requisito 1 & 2: Validação de Herança e Cardinalidade 1:N (Tutor -> Animais)")
    void testHerancaEAssociacaoUmParaMuitos() {
        List<Tutor> tutores = tutorRepository.findAll();
        assertFalse(tutores.isEmpty(), "Deve haver ao menos um tutor inserido");

        Tutor tutor = tutores.get(0);
        assertTrue(tutor instanceof Pessoa, "Tutor deve herdar de Pessoa");

        List<Cachorro> cachorros = cachorroRepository.findByTutorId(tutor.getId());
        List<Gato> gatos = gatoRepository.findByTutorId(tutor.getId());

        assertTrue(!cachorros.isEmpty() || !gatos.isEmpty(), "Tutor deve possuir animais associados (1:N)");
        if (!cachorros.isEmpty()) {
            assertTrue(cachorros.get(0) instanceof Animal, "Cachorro deve herdar de Animal");
        }
    }

    @Test
    @DisplayName("Requisito 3: Validação de Cardinalidade N:N (Veterinario <-> Especialidade)")
    void testAssociacaoMuitosParaMuitos() {
        List<Veterinario> veterinarios = veterinarioRepository.findAll();
        assertFalse(veterinarios.isEmpty(), "Deve haver ao menos um veterinário");

        Veterinario vet = veterinarios.get(0);
        assertTrue(vet instanceof Pessoa, "Veterinario deve herdar de Pessoa");
        assertFalse(vet.getEspecialidades().isEmpty(), "Veterinário deve possuir especialidades associadas (N:N)");
    }
}
