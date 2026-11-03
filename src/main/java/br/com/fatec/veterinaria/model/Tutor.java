package br.com.fatec.veterinaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tutor")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "animais", callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Tutor extends Pessoa {

    @Column(length = 200)
    private String endereco;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("tutor")
    @Builder.Default
    private List<Animal> animais = new ArrayList<>();
}
