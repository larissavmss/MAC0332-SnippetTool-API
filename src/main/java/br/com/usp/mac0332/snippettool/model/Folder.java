package br.com.usp.mac0332.snippettool.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import br.com.usp.mac0332.snippettool.dto.folder.FolderCreateDto;
import br.com.usp.mac0332.snippettool.dto.folder.FolderUpdateDto;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "folder", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Folder {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private String name;

	@CreationTimestamp
	private Date creationDate;

	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "folder_id")
	private Folder folder;

	public Folder (FolderCreateDto folderCreateDto, User user) {
		this.name = folderCreateDto.name();
		this.user = user;
	}

	public void update(FolderUpdateDto updatedFolder) {
		if (StringUtils.isNotBlank(updatedFolder.name())) {
			this.name = updatedFolder.name();
		}
	}
}
