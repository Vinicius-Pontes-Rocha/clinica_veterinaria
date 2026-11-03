package br.com.fatec.veterinaria.config;

import br.com.fatec.veterinaria.model.*;
import br.com.fatec.veterinaria.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============================================================");
        System.out.println("   INICIALIZANDO BANCO DE DADOS - CLÍNICA VETERINÁRIA FATEC   ");
        System.out.println("==============================================================");

        // 1. Criando Especialidades
        Especialidade cirurgia = Especialidade.builder()
                .nome("Cirurgia Geral")
                .descricao("Procedimentos cirúrgicos de pequenos e médios animais")
                .build();
        Especialidade dermatologia = Especialidade.builder()
                .nome("Dermatologia")
                .descricao("Tratamento de afecções na pele e pelos")
                .build();
        Especialidade cardiologia = Especialidade.builder()
                .nome("Cardiologia")
                .descricao("Diagnóstico e acompanhamento cardiovascular")
                .build();

        especialidadeRepository.save(cirurgia);
        especialidadeRepository.save(dermatologia);
        especialidadeRepository.save(cardiologia);

        // 2. Criando Veterinários (Herança de Pessoa) e relação N:N com Especialidades
        Set<Especialidade> espDraCamila = new HashSet<>();
        espDraCamila.add(cirurgia);
        espDraCamila.add(dermatologia);

        Veterinario vet1 = Veterinario.builder()
                .nome("Dra. Camila Santos")
                .cpf("111.222.333-44")
                .telefone("(11) 98888-1111")
                .email("camila.vet@clinica.com")
                .crmv("SP-12345")
                .especialidades(espDraCamila)
                .build();

        Set<Especialidade> espDrRoberto = new HashSet<>();
        espDrRoberto.add(dermatologia);
        espDrRoberto.add(cardiologia);

        Veterinario vet2 = Veterinario.builder()
                .nome("Dr. Roberto Silva")
                .cpf("222.333.444-55")
                .telefone("(11) 97777-2222")
                .email("roberto.vet@clinica.com")
                .crmv("SP-54321")
                .especialidades(espDrRoberto)
                .build();

        veterinarioRepository.save(vet1);
        veterinarioRepository.save(vet2);

        // 3. Criando Tutor (Herança de Pessoa)
        Tutor tutor1 = Tutor.builder()
                .nome("Carlos Eduardo")
                .cpf("333.444.555-66")
                .telefone("(11) 99999-3333")
                .email("carlos.tutor@gmail.com")
                .endereco("Rua das Flores, 120 - São Paulo/SP")
                .build();

        tutor1 = tutorRepository.save(tutor1);

        // 4. Criando Animais (Herança de Animal) e associação 1:N com Tutor
        Cachorro cachorro = Cachorro.builder()
                .nome("Thor")
                .idade(4)
                .peso(18.5)
                .raca("Golden Retriever")
                .porte("Grande")
                .tutor(tutor1)
                .build();

        Gato gato = Gato.builder()
                .nome("Mimi")
                .idade(2)
                .peso(4.2)
                .pelagem("Siamês")
                .castrado(true)
                .tutor(tutor1)
                .build();

        cachorroRepository.save(cachorro);
        gatoRepository.save(gato);

        System.out.println(">> Dados de teste inseridos com sucesso!");
        System.out.println(">> [Herança 1]: Pessoa -> Tutor, Veterinario");
        System.out.println(">> [Herança 2]: Animal -> Cachorro, Gato");
        System.out.println(">> [Relacionamento 1:N]: Tutor (1) -> Animais (N) [Thor e Mimi vinculados a Carlos]");
        System.out.println(">> [Relacionamento N:N]: Veterinário (N) <-> Especialidade (N) [Dra. Camila & Dr. Roberto]");
        System.out.println("--------------------------------------------------------------");
        System.out.println("API REST rodando em: http://localhost:8085");
        System.out.println("Console H2 disponível em: http://localhost:8085/h2-console");
        System.out.println("Endpoints disponíveis:");
        System.out.println(" - /api/tutores");
        System.out.println(" - /api/veterinarios");
        System.out.println(" - /api/especialidades");
        System.out.println(" - /api/cachorros");
        System.out.println(" - /api/gatos");
        System.out.println("==============================================================");
    }
}
