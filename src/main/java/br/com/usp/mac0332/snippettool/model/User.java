package br.com.usp.mac0332.snippettool.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private String username;

	private String email;

	private String password;

	private Date registrationDate;

}
