package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

//LOCALHOST:8080/
@RestController
@RequestMapping("missoes")
public class MissoesController {

    //GET -- Mandar uma requisição para mostrar as missoes
    @GetMapping("/listar")
    public String listarMissao(){
        return "Missões listadas com sucesso";
    }
    //POST -- Mandar uma requisição para criar as missoes
    @PostMapping("/criar")
    public String criarMissao(){
        return "Missao Criada com Sucesso";
    }
    //PUT -- Mandar uma requisição para alterar as missoes
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao Alterada com sucesso";
    }
    //Delete -- Mandar uma requisição para deletar as missoes
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missão Deletada com sucesso";
    }


}
