package com.algaworks.wine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.wine.dto.Photo;
import com.algaworks.wine.service.CreateWineService;

@RestController
@RequestMapping("/photos")
public class PhotosController {
	
	@Autowired
	private CreateWineService createWineService;
	
	@RequestMapping(value= "/{code}", method= RequestMethod.POST)
	public Photo upload(@PathVariable Long code
			,@RequestParam("files[]") MultipartFile[] files){
		
		String uri = createWineService.savePhoto(code, files[0]);
		return new Photo(uri);
	}

}
