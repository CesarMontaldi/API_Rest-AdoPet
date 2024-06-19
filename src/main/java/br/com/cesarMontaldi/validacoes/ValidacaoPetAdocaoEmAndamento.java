package br.com.cesarMontaldi.validacoes;

import br.com.cesarMontaldi.dto.adocao.SolicitacaoAdocaoDto;
import br.com.cesarMontaldi.exception.ValidacaoException;
import br.com.cesarMontaldi.model.StatusAdocao;
import br.com.cesarMontaldi.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDto dados) {
        boolean petAdocaoEmAndamento = adocaoRepository.existsByPetIdAndStatus(dados.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO);

        if (petAdocaoEmAndamento) {
            throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
