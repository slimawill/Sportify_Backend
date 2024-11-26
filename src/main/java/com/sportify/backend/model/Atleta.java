package com.sportify.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Past;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atleta {
    @NotBlank(message = "Nome Usuario é obrigatório")
    @Size(max = 255, message = "Nome Usuario deve ter no máximo 255 caracteres")
    private String nomeUsuario;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;

    @NotNull(message = "Data Nascimento é obrigatória")
    @Past(message = "Data Nascimento deve ser uma data no passado")
    private Date dataNascimento;

    @NotBlank(message = "Esporte é obrigatório")
    @Size(max = 255, message = "Esporte deve ter no máximo 255 caracteres")
    private String esporte;

    @NotBlank(message = "Genero é obrigatório")
    @Pattern(regexp = "^[MF]$", message = "Genero deve ser 'M' ou 'F'")
    private char genero;

    @NotBlank(message = "Agencia CNPJ é obrigatório")
    @Size(max = 14, message = "Agencia CNPJ deve ter no máximo 14 caracteres")
    @Pattern(regexp = "\\d{14}", message = "Agencia CNPJ deve ter no exatamente 14 caracteres")
    private String agenciaCnpj;

    @NotBlank(message = "Equipe CNPJ é obrigatório")
    @Size(max = 14, message = "Equipe CNPJ deve ter no máximo 14 caracteres")
    @Pattern(regexp = "\\d{14}", message = "Equipe CNPJ deve ter exatamente 14 caracteres")
    private String equipeCnpj;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;
}