package org.victor.figueiredo.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(

        @NotBlank(message = "Username é obrigatório")
        String username,

        @NotBlank(message = "Password é obrigatório")
        String password
) {
}
