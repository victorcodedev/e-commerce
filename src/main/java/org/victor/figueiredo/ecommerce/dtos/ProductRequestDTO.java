package org.victor.figueiredo.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Descrição é obrigatória")
        String description,

        @Positive(message = "Preço deve ser maior que zero")
        double price,

        @PositiveOrZero(message = "Quantidade não pode ser negativa")
        int quantity
) {
}
