package br.com.cesarMontaldi.repository;

import br.com.cesarMontaldi.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByTelefoneAndEmail(String telefone, String email);
}
