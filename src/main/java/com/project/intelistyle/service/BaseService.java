package com.project.intelistyle.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.MultiValueMap;

import com.project.intelistyle.utils.SearchCriteria;


public class BaseService {

	protected static final List<String> PAGEABLE_KEYS = Arrays.asList(new String[] { "page", "sort", "size" });

	public List<SearchCriteria> getCriteria(MultiValueMap<String, String> filters) {
		List<SearchCriteria> conditions = new ArrayList<SearchCriteria>();	
		for(String key: filters.keySet()) {		
			if (!PAGEABLE_KEYS.contains(key)) {
				List<String> conditionList = filters.get(key);
				for(String cond: conditionList) {
					String[] condition = cond.split(";");
					List<String> values = new ArrayList<>();
					values.add(condition[1]);
					conditions.add(new SearchCriteria(key, condition[0], values));
				}
			}	
		}
		return conditions;
	}

}
