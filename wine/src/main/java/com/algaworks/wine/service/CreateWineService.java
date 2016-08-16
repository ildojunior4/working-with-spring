package com.algaworks.wine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.wine.model.Wine;
import com.algaworks.wine.repository.Wines;
import com.algaworks.wine.storage.PhotoStorage;
import com.algaworks.wine.storage.PhotoStorageS3;

@Service
public class CreateWineService {
	
	@Autowired
	private Wines wines;
	
	@Autowired
	private PhotoStorage photoStorage;
	
	public void save(Wine wine){
		//Write job rules
		this.wines.save(wine);
	}
	
	public String savePhoto(Long code, MultipartFile photo){
		String photoName = photoStorage.save(photo);

		Wine wine = wines.findOne(code);
		wine.setPhoto(photoName);
		wines.save(wine);
		return photoStorage.getUri(photoName);
	}

}