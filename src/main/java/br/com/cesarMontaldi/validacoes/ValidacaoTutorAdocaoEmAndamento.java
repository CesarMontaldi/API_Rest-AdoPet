package br.com.cesarMontaldi.validacoes;

import br.com.cesarMontaldi.dto.adocao.SolicitacaoAdocaoDto;
import br.com.cesarMontaldi.exception.ValidacaoException;
import br.com.cesarMontaldi.model.StatusAdocao;
import br.com.cesarMontaldi.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaoDto dados) {
        boolean tutorAdocaoEmAndamento = adocaoRepository.existsByTutorIdAndStatus(dados.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);

        if (tutorAdocaoEmAndamento) {
            throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
        }
    }
}
