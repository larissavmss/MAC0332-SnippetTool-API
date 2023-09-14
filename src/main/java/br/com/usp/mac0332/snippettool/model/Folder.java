package br.com.usp.mac0332.snippettool.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Date;

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

	private Date creationDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
