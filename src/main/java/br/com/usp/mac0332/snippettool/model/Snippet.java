package br.com.usp.mac0332.snippettool.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import br.com.usp.mac0332.snippettool.dto.snippet.SnippetCreateDto;
import br.com.usp.mac0332.snippettool.dto.snippet.SnippetUpdateDto;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "snippet", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Snippet {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private String name;

	private String content;

	@CreationTimestamp
	private Date creationDate;

	@ManyToOne
	@JoinColumn(name = "folder_id")
	private Folder folder;

	@ManyToMany(mappedBy = "snippets")
	private Set<Tag> tags;

	public Snippet(SnippetCreateDto snippetDto, Folder folder) {
		this.name = snippetDto.name();
		this.content = snippetDto.content();
		this.folder = folder;
	}

	public void update(SnippetUpdateDto updatedSnippet, Folder folder) {
		if (StringUtils.isNotBlank(updatedSnippet.name())) {
			this.name = updatedSnippet.name();
		}
		if (StringUtils.isNotBlank(updatedSnippet.content())) {
			this.content = updatedSnippet.content();
		}
		if (Objects.nonNull(folder)) {
			this.folder = folder;
		}
	}

}