package br.com.usp.mac0332.snippettool.dto.snippet;

import java.sql.Date;
import java.util.List;

import br.com.usp.mac0332.snippettool.dto.tag.TagResponseDto;
import br.com.usp.mac0332.snippettool.model.Snippet;

public record SnippetResponseDto(Integer id, String name, String content, List<TagResponseDto> tags, Date creationDate) {

	public SnippetResponseDto(Snippet snippet, List<TagResponseDto> tags) {
		this(snippet.getId(), snippet.getName(), snippet.getContent(), tags, snippet.getCreationDate());
	}
}
