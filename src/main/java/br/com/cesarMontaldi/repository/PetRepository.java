package br.com.cesarMontaldi.repository;

import br.com.cesarMontaldi.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
