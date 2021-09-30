package com.project.intelistyle.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.project.intelistyle.model.Garment;
import com.project.intelistyle.utils.SearchCriteria;

public class GarmentSpecification implements Specification<Garment> {

	private SearchCriteria criteria;

	public GarmentSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate
	(Root<Garment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Expression<String> rootPath = null;
		rootPath = getRootPath(root, criteria.getKey());
		
		if (criteria.getOperation().equalsIgnoreCase("gt")) 
			return builder.greaterThanOrEqualTo(
					rootPath, criteria.getValue().get(0).toString()); 
		else if (criteria.getOperation().equalsIgnoreCase("lt"))
			return builder.lessThanOrEqualTo(
					rootPath, criteria.getValue().get(0).toString()); 
		else if (criteria.getOperation().equalsIgnoreCase("like"))
			return builder.like(rootPath, "%" + criteria.getValue().get(0) + "%");
		else if (criteria.getOperation().equalsIgnoreCase("ne"))
			return builder.notEqual(rootPath, criteria.getValue().get(0).toString());
		else if (criteria.getOperation().equalsIgnoreCase("in")) {
			In<String> inClause = builder.in(rootPath);
			for(String val: criteria.getValue())
				inClause.value(val);
			return inClause;
		}
		else 
			return builder.equal(rootPath, criteria.getValue().get(0));

	}

	private Expression<String> getRootPath(Root<Garment> root, String key) {		
		Path<Object> rootPath = null;
		String[] keyList =  key.split("\\.");
		String lastCriteria = null;
		for(int i=0;i<keyList.length; i++) {
			if(i == keyList.length-1) 
				lastCriteria = keyList[i];
			else if(i == 0)
				rootPath = root.join(keyList[i]);
			else
				rootPath = ((From<Object, Object>) rootPath).join(keyList[i]);		
		}
		if(rootPath != null)
			return rootPath.<String>get(lastCriteria.trim());	
		else
			return root.<String>get(lastCriteria.trim());
	}

	public static Specification<Garment> distinct(){
		return (root, query, cb) -> {
			query.distinct(true);
			return null;
		};
	}
}
