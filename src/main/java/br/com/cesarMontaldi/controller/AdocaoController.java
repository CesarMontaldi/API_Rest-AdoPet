package br.com.cesarMontaldi.controller;

import br.com.cesarMontaldi.dto.adocao.AprovacaoAdocaoDto;
import br.com.cesarMontaldi.dto.adocao.ReprovacaoAdocaoDto;
import br.com.cesarMontaldi.dto.adocao.SolicitacaoAdocaoDto;
import br.com.cesarMontaldi.exception.ValidacaoException;
import br.com.cesarMontaldi.service.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    @Autowired
    private AdocaoService adocaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitacaoAdocaoDto dados) {
        try {
            this.adocaoService.solicitar(dados);
            return ResponseEntity.ok("Adoção solicitada com sucesso!");
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/aprovar")
    @Transactional
    public ResponseEntity<String> aprovar(@RequestBody @Valid AprovacaoAdocaoDto dados) {
        this.adocaoService.aprovar(dados);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/reprovar")
    @Transactional
    public ResponseEntity<String> reprovar(@RequestBody @Valid ReprovacaoAdocaoDto dados) {
        this.adocaoService.reprovar(dados);

        return ResponseEntity.ok().build();
    }

}
