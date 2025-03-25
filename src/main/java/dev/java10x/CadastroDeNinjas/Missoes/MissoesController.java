package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//LOCALHOST:8080/
@RestController
@RequestMapping("missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    //GET -- Mandar uma requisição para mostrar as missoes
    @GetMapping("/listar")
    public List<MissoesModel> listarMissao(){
        return missoesService.listarMissoes();
    }
    //POST -- Mandar uma requisição para criar as missoes
    @PostMapping("/criar")
    public MissoesModel criarMissao(@RequestBody MissoesModel missoes){
        return missoesService.criarMissoes(missoes);
    }
    //PUT -- Mandar uma requisição para alterar as missoes
    @PutMapping("/alterar/{id}")
    public MissoesModel alterarMissao(@PathVariable Long id,@RequestBody MissoesModel missoesAtualizada){
        return missoesService.atualizarMissao(id, missoesAtualizada);
    }

    //Delete -- Mandar uma requisição para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id)
    {
        missoesService.deletarMissoesPorId(id);
    }


}
