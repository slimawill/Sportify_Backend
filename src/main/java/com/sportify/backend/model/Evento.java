package com.sportify.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {

    private Long id;

    @NotNull(message = "Data Hora é obrigatório")
    private LocalDateTime dataHora;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "deve ter no máximo 255 caracteres")
    private String nome;

    @NotBlank(message = "Patrocinador CNPJ é obrigatório")
    @Size(max = 14, message = "Patrocinador CNPJ deve ter no máximo 14 caracteres")
    @Pattern(regexp = "\\d{14}", message = "Patrocinador CNPJ deve ter exatamente 14 caracteres")
    private String patrocinadorCnpj;

    @NotBlank(message = "CEP é obrigatório")
    @Size(max = 9, message = "CEP deve ter no máximo 9 caracteres")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve seguir o padrão 12345-678")
    private String cep;

    @NotNull(message = "Numero Local is obrigatório")
    @Positive(message = "Numero Local deve ser maior que 0")
    private Integer numeroLocal;

    @NotNull(message = "Capacidade é obrigatório")
    @Positive(message = "Capacidade deve ser maior que 0")
    private Integer capacidade;

}
