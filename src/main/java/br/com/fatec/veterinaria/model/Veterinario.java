package br.com.fatec.veterinaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_veterinario")
@Data
@EqualsAndHashCode(callSuper = true, exclude = "especialidades")
@ToString(callSuper = true, exclude = "especialidades")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Veterinario extends Pessoa {

    @Column(nullable = false, unique = true, length = 20)
    private String crmv;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "tb_veterinario_especialidade",
        joinColumns = @JoinColumn(name = "veterinario_id"),
        inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    @JsonIgnoreProperties("veterinarios")
    @Builder.Default
    private Set<Especialidade> especialidades = new HashSet<>();
}
