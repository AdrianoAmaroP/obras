package com.obras.gerenciador.domain.usuario.entities;

import jakarta.validation.constraints.NotBlank;

public record RequestUsuario(@NotBlank String nome, @NotBlank String senha) {
}