package br.com.usp.mac0332.snippettool.dto.tag;

import jakarta.validation.constraints.NotBlank;

public record TagCreateDto(@NotBlank String name, @NotBlank String color) {

}
