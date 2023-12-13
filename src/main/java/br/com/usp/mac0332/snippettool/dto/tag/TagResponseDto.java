package br.com.usp.mac0332.snippettool.dto.tag;

import br.com.usp.mac0332.snippettool.model.Tag;

public record TagResponseDto(Integer id, String name, String color) {

	public TagResponseDto(Tag tag) {
		this(tag.getId(), tag.getName(), tag.getColor().toString());
	}
}
