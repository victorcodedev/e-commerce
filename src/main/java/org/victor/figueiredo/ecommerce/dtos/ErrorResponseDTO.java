package org.victor.figueiredo.ecommerce.dtos;

import java.time.LocalDateTime;

public record ErrorResponseDTO(int status, String message, String path, LocalDateTime timestamp) {
}
