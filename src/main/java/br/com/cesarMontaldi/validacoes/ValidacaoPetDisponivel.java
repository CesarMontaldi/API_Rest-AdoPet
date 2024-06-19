package br.com.cesarMontaldi.validacoes;

import br.com.cesarMontaldi.dto.adocao.SolicitacaoAdocaoDto;
import br.com.cesarMontaldi.exception.ValidacaoException;
import br.com.cesarMontaldi.model.Pet;
import br.com.cesarMontaldi.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel {

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDto dados) {

        Pet pet = petRepository.getReferenceById(dados.idPet());
        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!");
        }
    }
}
