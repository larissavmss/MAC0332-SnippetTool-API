package br.com.usp.mac0332.snippettool.dto.snippet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@JsonIgnoreProperties(ignoreUnknown = true)
public record SnippetCreateDto(String name, String content, Integer folderId) {

}
