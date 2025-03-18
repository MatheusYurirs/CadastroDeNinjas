package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// Entity ele transforma uma classe em uma entidade do BD
// JPA = Java Persistent API
@Entity
@Table(name = "tb_cadastro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NinjaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "img_URL")
    private String imgURL;

    @Column(name = "idade")
    private int idade;

    //@ManyToOne Um ninja tem uma única missão
    @ManyToOne
    @JoinColumn(name = "missoes_id") //Foreing Key ou Chave Estrangeira
    private MissoesModel missoes;

}
