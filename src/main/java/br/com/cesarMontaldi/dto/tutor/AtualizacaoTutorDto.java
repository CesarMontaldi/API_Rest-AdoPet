package br.com.cesarMontaldi.dto.tutor;

import br.com.cesarMontaldi.model.Tutor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AtualizacaoTutorDto(
        Long id,

        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String telefone,

        @NotBlank
        @Email
        String email) {

    public AtualizacaoTutorDto(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getTelefone(), tutor.getEmail());
    }
}
