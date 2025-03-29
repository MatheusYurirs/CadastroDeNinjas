package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissao(){
        List<MissoesDTO> missoes =  missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoes){
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Nova missão criada com Sucesso");
    }


    @PatchMapping ("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id,@RequestBody MissoesDTO missoesAtualizada){
        MissoesDTO missao = missoesService.atualizarMissao(id, missoesAtualizada);
        if(missao != null){
            return ResponseEntity.ok(missao);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao de Id: " + id + " não existe nos nossos registros.");
        }
    }


    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id)
    {
        if(missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissoesPorId(id);
            return ResponseEntity.ok("Ninja com id " + id + " deletado com sucesso.");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja de Id: " + id + " não existe nos nossos registros.");
        }
    }


}
