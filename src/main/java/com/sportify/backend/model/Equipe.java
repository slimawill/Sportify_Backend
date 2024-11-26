package com.sportify.backend.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipe {
    @NotBlank(message = "CNPJ é obrigatório")
    @Size(max = 14, message = "CNPJ deve ter no máximo 14 caracteres")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve ter exatamente 14 caracteres")
    private String cnpj;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;

    @Min(value = 1, message = "Numero Integrantes deve ser no mínimo 1")
    private int numeroIntegrantes;

}