package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissoesService {
    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    //Listar todas as missões
    public List<MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    //Criar missao

    public MissoesModel criarMissoes(MissoesModel missoes){
        return missoesRepository.save(missoes);
    }

    //Deletar Missoes

    public void deletarMissoesPorId(Long id){
        missoesRepository.deleteById(id);
    }

    //Alterar missoes
    public MissoesModel atualizarMissao(Long id, MissoesModel missoesAtualizada){
        if(missoesRepository.existsById(id)){
            missoesAtualizada.setId(id);
            return missoesRepository.save(missoesAtualizada);
        }
        return null;
    }


}
