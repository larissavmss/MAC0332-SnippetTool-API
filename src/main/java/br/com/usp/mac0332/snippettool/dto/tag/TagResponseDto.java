package br.com.usp.mac0332.snippettool.dto.tag;

import br.com.usp.mac0332.snippettool.model.Tag;

public record TagResponseDto(String name, String color, Integer id) {

	public TagResponseDto(Tag tag) {
		this(tag.getName(), tag.getColor().toString(), tag.getId());
	}
}
