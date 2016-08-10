package com.algaworks.wine.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.wine.model.Wine;
import com.algaworks.wine.repository.Wines;
import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class CreateWineService {
	
	@Autowired
	private Wines wines;
	
	@Autowired
	private AmazonS3 s3Client;
	
	public void save(Wine wine){
		//Write job rules
		this.wines.save(wine);
	}
	
	public String savePhoto(Long code, MultipartFile photo){
		Wine wine = wines.findOne(code);
		String photoName = photo.getOriginalFilename();
		
		wine.setPhoto(photoName);
		wines.save(wine);
		
		try{
			ObjectMetadata metaData = new ObjectMetadata();
			metaData.setContentType(photo.getContentType());
			metaData.setContentLength(photo.getSize());
			s3Client.putObject("wine", photoName, photo.getInputStream(), metaData);
		} catch (AmazonClientException | IOException e) {
			throw new RuntimeException("Error salving s3 file");
		}
		return "http://localhost:9444/s3/wine/"+photoName+"?noAuth=true";
	}

}