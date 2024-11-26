package com.sportify.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patrocinador {
    @NotBlank(message = "CNPJ é obrigatório")
    @Size(max = 14, message = "CNPJ deve ter no máximo 14 caracteres")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve ter no máximo 14 caracteres")
    private String cnpj;

    @NotBlank(message = "Esfera De Poder é obrigatório")
    @Size(max = 255, message = "Esfera De Poder deve ter no máximo 255 caracteres")
    private String esferaDePoder;

}