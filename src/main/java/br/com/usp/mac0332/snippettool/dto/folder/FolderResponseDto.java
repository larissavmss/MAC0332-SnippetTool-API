package br.com.usp.mac0332.snippettool.dto.folder;

import java.util.Date;

import br.com.usp.mac0332.snippettool.model.Folder;
import lombok.Builder;

@Builder
public record FolderResponseDto(Integer id, String name, Date creationDate) {

	public FolderResponseDto(Folder folder) {
		this(folder.getId(), folder.getName(), folder.getCreationDate());
	}
}
