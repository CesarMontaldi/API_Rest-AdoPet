package br.com.cesarMontaldi.repository;

import br.com.cesarMontaldi.model.Adocao;
import br.com.cesarMontaldi.model.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

    boolean existsByPetIdAndStatus(Long idPet, StatusAdocao status);
    boolean existsByTutorIdAndStatus(Long idTutor, StatusAdocao status);
    List<Adocao> findByTutorIdAndStatus(Long idTutor, StatusAdocao status);
}
