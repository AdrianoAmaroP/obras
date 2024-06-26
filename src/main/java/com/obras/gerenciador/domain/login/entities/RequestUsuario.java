package com.obras.gerenciador.domain.login.entities;

import jakarta.validation.constraints.NotBlank;

public record RequestUsuario(@NotBlank String nome, @NotBlank String senha) {
}