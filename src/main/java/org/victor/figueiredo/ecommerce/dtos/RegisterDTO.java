package org.victor.figueiredo.ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.victor.figueiredo.ecommerce.enums.UserRole;

public record RegisterDTO(

        @NotBlank(message = "Username é obrigatório")
        String username,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password,

        UserRole role
) {
}
