package com.rabindra.cvgenerator.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabindra.cvgenerator.entity.CVGenerator;

public interface CVRepository extends JpaRepository<CVGenerator, Long> {
	
}

