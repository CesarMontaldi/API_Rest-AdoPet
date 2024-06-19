package br.com.cesarMontaldi.service;

import br.com.cesarMontaldi.dto.tutor.AtualizacaoTutorDto;
import br.com.cesarMontaldi.dto.tutor.CadastroTutorDto;
import br.com.cesarMontaldi.exception.ValidacaoException;
import br.com.cesarMontaldi.model.Tutor;
import br.com.cesarMontaldi.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired Tutor tutor;

    public void cadastrar(CadastroTutorDto dados) {
        boolean dadosCadastroExists = repository.existsByTelefoneAndEmail(dados.telefone(), dados.email());

        if (dadosCadastroExists) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }
        else {
            Tutor tutor = new Tutor(dados.nome(), dados.telefone(), dados.email());
            repository.save(tutor);
        }
    }

    public void atualizar(AtualizacaoTutorDto dados) {
        tutor.atualizarDados(dados);
    }

}
