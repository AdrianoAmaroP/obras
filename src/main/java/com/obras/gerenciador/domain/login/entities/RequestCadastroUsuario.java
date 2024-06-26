package com.obras.gerenciador.domain.login.entities;

import jakarta.validation.constraints.NotBlank;

public record RequestCadastroUsuario(@NotBlank String nome,@NotBlank String senha,@NotBlank String chave) {
}