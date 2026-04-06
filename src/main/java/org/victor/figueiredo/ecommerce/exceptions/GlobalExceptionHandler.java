package org.victor.figueiredo.ecommerce.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.victor.figueiredo.ecommerce.dtos.ErrorResponseDTO;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // exceção de recurso não encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {

        var error = new ErrorResponseDTO(
                404,
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(404).body(error);
    }

    // exceção de validação (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining(", "));

        var error = new ErrorResponseDTO(400, message, request.getRequestURI(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    // qualquer outra exceção não tratada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(
            Exception ex, HttpServletRequest request) {

        var error = new ErrorResponseDTO(
                500,
                "Erro interno do servidor",
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.internalServerError().body(error);
    }

}
