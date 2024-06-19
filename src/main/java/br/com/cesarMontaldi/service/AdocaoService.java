package br.com.cesarMontaldi.service;

import br.com.cesarMontaldi.dto.adocao.AprovacaoAdocaoDto;
import br.com.cesarMontaldi.dto.adocao.ReprovacaoAdocaoDto;
import br.com.cesarMontaldi.dto.adocao.SolicitacaoAdocaoDto;
import br.com.cesarMontaldi.model.Adocao;
import br.com.cesarMontaldi.model.Pet;
import br.com.cesarMontaldi.model.Tutor;
import br.com.cesarMontaldi.repository.AdocaoRepository;
import br.com.cesarMontaldi.repository.PetRepository;
import br.com.cesarMontaldi.repository.TutorRepository;
import br.com.cesarMontaldi.validacoes.ValidacaoSolicitacaoAdocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private List<ValidacaoSolicitacaoAdocao> validacoes;


    public void solicitar(SolicitacaoAdocaoDto dados) {
        Pet pet = petRepository.getReferenceById(dados.idPet());
        Tutor tutor = tutorRepository.getReferenceById(dados.idTutor());
        validacoes.forEach(v -> v.validar(dados));

        Adocao adocao = new Adocao(tutor, pet, dados.motivo());
        repository.save(adocao);

        emailService.enviarEmail(adocao.getPet().getAbrigo().getEmail(),
                "Solicitação de adoção",
                "Olá " +adocao.getPet().getAbrigo().getNome() +
                        "!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +
                        adocao.getPet().getNome() +". \nFavor avaliar para aprovação ou reprovação.");
    }

    public void aprovar(AprovacaoAdocaoDto dados) {
        Adocao adocao = repository.getReferenceById(dados.idAdocao());
        adocao.statusAprovado();

        emailService.enviarEmail(adocao.getTutor().getEmail(),
                "Adoção aprovada",
                "Parabéns " + adocao.getTutor().getNome() +"!\n\nSua adoção do pet " + adocao.getPet().getNome() +
                        ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                        ", foi aprovada.\nFavor entrar em contato com o abrigo " + adocao.getPet().getAbrigo().getNome() +
                        " para agendar a busca do seu pet.");
    }

    public void reprovar(ReprovacaoAdocaoDto dados) {
        Adocao adocao = repository.getReferenceById(dados.idAdocao());
        adocao.statusReprovado(dados.justificativa());

        emailService.enviarEmail(adocao.getTutor().getEmail(),
                "Adoção reprovada",
                "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " + adocao.getPet().getNome() +
                        ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                        ", foi reprovada pelo abrigo " + adocao.getPet().getAbrigo().getNome() +
                        " com a seguinte justificativa: " + adocao.getJustificativaStatus());
    }

}
