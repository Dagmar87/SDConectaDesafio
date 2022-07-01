package com.dagmar87.medico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dagmar87.medico.model.user;

public interface userRepository extends JpaRepository<user, Long>{	
	
	List<user> findByName(String name);

}
