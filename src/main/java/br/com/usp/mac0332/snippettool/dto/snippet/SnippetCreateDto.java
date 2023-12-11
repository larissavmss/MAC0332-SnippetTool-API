package br.com.usp.mac0332.snippettool.dto.snippet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SnippetCreateDto(@NotBlank String name, @NotBlank String content, @NotNull Integer folderId) {

}
