package com.dagmar87.medico.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dagmar87.medico.model.crm;

public interface crmRepository extends JpaRepository<crm, Long> {

	List<crm> findByUserId(Long userId);

	List<crm> findBySpecialty(String specialty);

	@Transactional
	void deleteByUserId(long userId);

}
