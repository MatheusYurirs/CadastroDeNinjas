package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissoesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo_missao")
    private String titulo;

    @Column(name = "dificuldade")
    private String dificuldade;

    // @OneToMany Uma missão pode ter vários ninjas(Como uma lista de Ninjas)
    @OneToMany(mappedBy = "missoes")
    @Column(name = "ninja_id")
    private List<NinjaModel> ninja;
}
