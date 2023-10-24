package br.com.usp.mac0332.snippettool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usp.mac0332.snippettool.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
