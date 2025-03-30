package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaDTO> listarNinjas(){
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    public NinjaDTO listarNinjasPorId(Long id){
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    public NinjaDTO criarNinja(NinjaDTO ninjaDTO){
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }



    public void deletarNinjaPorId(Long id){
        ninjaRepository.deleteById(id);
    }

    //Atualizar o Ninja
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO){
        Optional<NinjaModel> ninjaExistenteOptional = ninjaRepository.findById(id);

        if (ninjaExistenteOptional.isPresent()) {
            NinjaModel ninjaExistente = ninjaExistenteOptional.get();

            if (ninjaDTO.getNome() != null) {
                ninjaExistente.setNome(ninjaDTO.getNome());
            }
            if (ninjaDTO.getRank() != null) {
                ninjaExistente.setRank(ninjaDTO.getRank());
            }
            if (Objects.nonNull(ninjaDTO.getIdade())) {
                ninjaExistente.setIdade(ninjaDTO.getIdade());
            }
            if(ninjaDTO.getEmail() != null){
                ninjaExistente.setEmail(ninjaDTO.getEmail());
            }
            if(ninjaDTO.getImgURL() != null){
                ninjaExistente.setImgURL(ninjaDTO.getImgURL());
            }

            if(ninjaDTO.getMissoes() != null){
                ninjaExistente.setMissoes(ninjaDTO.getMissoes());
            }


            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaExistente);
            return ninjaMapper.map(ninjaSalvo);
        }
        return null;
    }



}
