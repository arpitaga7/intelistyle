package com.project.intelistyle.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.project.intelistyle.model.Garment;
import com.project.intelistyle.repository.GarmentRepository;
import com.project.intelistyle.specification.GarmentSpecification;
import com.project.intelistyle.utils.SearchCriteria;

@Service
public class GarmentService extends BaseService{

	@Autowired
	private GarmentRepository garmentRepository;

	public Page<Garment> getAllItems(Pageable page, MultiValueMap<String, String> filters) throws Exception {
		List<SearchCriteria> criteriaList = null;
		Specification<Garment> spec = null;
		Specification<Garment> distinctSpec = null;

		if(filters != null && !filters.isEmpty())
			criteriaList = getCriteria(filters);
		if(criteriaList!= null && !criteriaList.isEmpty())
			spec = buildSpecification(criteriaList);	
		distinctSpec = GarmentSpecification.distinct();
		if(distinctSpec != null)
			spec = distinctSpec.and(spec);

		Page<Garment> garments = garmentRepository.findAll(spec, page);

		return garments;
	}
	
	public Garment createGarment(Garment garment) throws IOException {
		return garmentRepository.save(garment);
	}

	private Specification<Garment> buildSpecification(List<SearchCriteria> criteriaList) {

		List<Specification<Garment>> specs = criteriaList.stream()
				.map(GarmentSpecification::new)
				.collect(Collectors.toList());

		Specification<Garment> result = specs.get(0);

		for (int i = 1; i < criteriaList.size(); i++) {
			result = Specification.where(result)
					.and(specs.get(i));
		}
		return result;
	}
}
