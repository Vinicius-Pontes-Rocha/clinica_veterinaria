package br.com.fatec.veterinaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_especialidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "veterinarios")
@EqualsAndHashCode(exclude = "veterinarios")
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @ManyToMany(mappedBy = "especialidades", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("especialidades")
    @Builder.Default
    private Set<Veterinario> veterinarios = new HashSet<>();
}
