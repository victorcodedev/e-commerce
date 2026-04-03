package org.victor.figueiredo.ecommerce.dtos;

import org.victor.figueiredo.ecommerce.enums.UserRole;

public record RegisterDTO(String username, String email, String password, UserRole role) {
}
