package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }


    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas",description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas(){
        return "Essa é a minha primeira mensagem nessa rota";
    }


    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e inseri no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja Criado com Sucesso " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }


    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas",description = "Rota lista todos os ninjas do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninjas Listados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninjas não encontrados")
    })
    public ResponseEntity<List<NinjaDTO>> mostrarTodosOsNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }


    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por Id", description = "Rota Lista um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "ninja não encontrado")
    })
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "Usuário manda o Id no caminho da requisiçao")
            @PathVariable Long id){
        NinjaDTO ninjaPorId = ninjaService.listarNinjasPorId(id);

        if(ninjaPorId != null) {
            return ResponseEntity.ok(ninjaPorId);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja de Id: " + id + " não existe nos nossos registros.");
        }
    }

    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por Id", description = "Rota Altera um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "ninja não encontrado, não foi possível alterar")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "Usuário manda o Id no caminho da requisiçao")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisiçao")
            @RequestBody NinjaDTO ninjaAtualizado){

        NinjaDTO ninjaAtualiza = ninjaService.atualizarNinja(id,ninjaAtualizado);

        if(ninjaAtualiza != null) {
            return ResponseEntity.ok(ninjaAtualiza);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja de Id: " + id + " não existe nos nossos registros.");
        }
    }




    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta ninja por Id", description = "Rota deleta ninja do banco de dados pelo Id")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){

        if(ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com id " + id + " deletado com sucesso.");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja de Id: " + id + " não existe nos nossos registros.");
        }
    }

}
