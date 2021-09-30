package com.project.intelistyle.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.intelistyle.model.Garment;

@Repository
public interface GarmentRepository extends PagingAndSortingRepository<Garment, Long>, JpaSpecificationExecutor<Garment> {
	
}
