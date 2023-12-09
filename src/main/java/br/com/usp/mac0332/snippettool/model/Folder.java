package br.com.usp.mac0332.snippettool.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

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

	private Date creationDate;

	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "user_id")
	private User user;

	public Folder (String folderName, User user) {
		this.creationDate = Date.valueOf(LocalDate.now());
		this.name = folderName;
		this.user = user;
	}

	@PrePersist
	public void onCreate() {
		this.creationDate = Date.valueOf(LocalDate.now());
	}

	public void update(Folder updatedFolder) {
		if (updatedFolder.getName() != null) {
			this.name = updatedFolder.getName();
		}
	}
}
