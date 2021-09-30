package com.project.intelistyle.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.intelistyle.model.Garment;
import com.project.intelistyle.service.GarmentService;

@RestController
@CrossOrigin("*")
@RequestMapping("intelistyle/v1")
public class GarmentController {

	@Autowired
	private GarmentService garmentService;

	@GetMapping("/garments")
	public Page<Garment> getAllItems(
			@PageableDefault(page = 0, size = Integer.MAX_VALUE)
			@SortDefault(sort = "lastModifiedDate", direction = Direction.DESC )
			Pageable page,
			@RequestParam(required = false) MultiValueMap<String, String> filters) throws Exception {
		return garmentService.getAllItems(page, filters);
	}

	@PostMapping("/garments")
	public Garment createItem(
			 @RequestBody Garment garment) throws IOException {
		return garmentService.createGarment(garment);
	}

}
