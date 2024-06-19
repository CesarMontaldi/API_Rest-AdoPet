package br.com.cesarMontaldi.controller;

import br.com.cesarMontaldi.dto.tutor.AtualizacaoTutorDto;
import br.com.cesarMontaldi.dto.tutor.CadastroTutorDto;
import br.com.cesarMontaldi.exception.ValidacaoException;
import br.com.cesarMontaldi.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroTutorDto dados) {
        try {
            tutorService.cadastrar(dados);
            return ResponseEntity.ok().body("Cadastro realizado com sucesso!");
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizacaoTutorDto dados) {
        tutorService.atualizar(dados);
        return ResponseEntity.ok().body("Dados atualizados com sucesso!");
    }

}
