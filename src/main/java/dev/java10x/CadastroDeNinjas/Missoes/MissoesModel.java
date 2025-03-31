package dev.java10x.CadastroDeNinjas.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tb_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @JsonIgnore
    private List<NinjaModel> ninja;
}
