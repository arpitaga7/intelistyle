package com.project.intelistyle.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchCriteria {
	private String key;
	private String operation;
	private List<String> value;
}
