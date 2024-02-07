package com.thbraum.demoparkapi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioCreateDto {
    @NotBlank
    @Email(message = "Formato do email está inválido", regexp = "^[a-z0-9.+-]+@[a-z0-9-]+\\.[a-z0-9-.]+$")
    private String username;
    @NotBlank
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;
}
