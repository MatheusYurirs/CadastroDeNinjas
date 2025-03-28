package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Component;

@Component
public class MissoesMapper {

    public MissoesModel map(MissoesDTO missoesDTO){
        MissoesModel missoesModel = new MissoesModel();
        missoesModel.setId(missoesDTO.getId());
        missoesModel.setNinja(missoesDTO.getNinja());
        missoesModel.setDificuldade(missoesDTO.getDificuldade());
        missoesModel.setTitulo(missoesDTO.getTitulo());

        return missoesModel;
    }

    public MissoesDTO map(MissoesModel missoesModel){
        MissoesDTO missoesDTO = new MissoesDTO();
        missoesDTO.setId(missoesModel.getId());
        missoesDTO.setNinja(missoesModel.getNinja());
        missoesDTO.setDificuldade(missoesModel.getDificuldade());
        missoesDTO.setTitulo(missoesModel.getTitulo());

        return missoesDTO;
    }
}
