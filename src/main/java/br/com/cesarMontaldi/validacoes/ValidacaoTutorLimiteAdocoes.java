package br.com.cesarMontaldi.validacoes;

import br.com.cesarMontaldi.dto.adocao.SolicitacaoAdocaoDto;
import br.com.cesarMontaldi.exception.ValidacaoException;
import br.com.cesarMontaldi.model.Adocao;
import br.com.cesarMontaldi.model.StatusAdocao;
import br.com.cesarMontaldi.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoTutorLimiteAdocoes implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDto dados) {
        List<Adocao> adocoesTutor = adocaoRepository.findByTutorIdAndStatus(dados.idTutor(), StatusAdocao.APROVADO);

        if (adocoesTutor.size() >= 5) {
            throw new ValidacaoException("Tutor chegou ao limite máximo de 5 adoções!");
        }
    }
}
