package br.com.usp.mac0332.snippettool.dto.folder;

import jakarta.validation.constraints.NotBlank;

public record FolderCreateDto(@NotBlank String name) {

}
