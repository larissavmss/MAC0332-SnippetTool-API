package br.com.usp.mac0332.snippettool.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRegisterDto(@NotBlank String username, @NotBlank String email, @NotBlank String password) {

}
