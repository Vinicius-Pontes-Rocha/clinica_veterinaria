package br.com.fatec.veterinaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tb_cachorro")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Cachorro extends Animal {

    @Column(length = 50)
    private String raca;

    @Column(length = 30)
    private String porte; // Pequeno, Medio, Grande
}
