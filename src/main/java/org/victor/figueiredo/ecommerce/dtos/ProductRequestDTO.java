package org.victor.figueiredo.ecommerce.dtos;

public record ProductRequestDTO(String name, String description, double price, int quantity) {
}
