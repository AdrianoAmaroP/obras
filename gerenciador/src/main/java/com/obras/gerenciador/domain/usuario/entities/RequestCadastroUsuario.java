package com.obras.gerenciador.domain.usuario.entities;

import jakarta.validation.constraints.NotBlank;

public record RequestCadastroUsuario(@NotBlank String nome,@NotBlank String senha,@NotBlank String chave) {
}