package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {
    private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    //Listar todas as missões
    public List<MissoesDTO> listarMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    public MissoesDTO listarMissoesPorId(Long id) {
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missoesMapper::map).orElse(null);
    }

    public MissoesDTO criarMissoes(MissoesDTO missoesDTO) {
        MissoesModel missoes = missoesMapper.map(missoesDTO);
        missoes = missoesRepository.save(missoes);
        return missoesMapper.map(missoes);
    }

    //Deletar Missoes

    public void deletarMissoesPorId(Long id) {
        missoesRepository.deleteById(id);
    }

    //Alterar missoes
    public MissoesDTO atualizarMissao(Long id, MissoesDTO missoesDTO){
        Optional<MissoesModel> missoesModelOptional = missoesRepository.findById(id);
        if (missoesModelOptional.isPresent()) {
            MissoesModel missoesExistem = missoesModelOptional.get();

            if (missoesDTO.getNinja() != null ) {
                missoesExistem.setNinja(missoesDTO.getNinja());
            }

            if(missoesDTO.getTitulo() != null){
                missoesExistem.setTitulo(missoesDTO.getTitulo());
            }

            if(missoesDTO.getDificuldade() != null){
                missoesExistem.setDificuldade(missoesDTO.getDificuldade());
            }


            MissoesModel missoesSalvo = missoesRepository.save(missoesExistem);
            return missoesMapper.map(missoesSalvo);
        }
        return null;
    }


}
