package com.algaworks.wine.storage;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Profile("storage-s3")
@Component
public class PhotoStorageS3 implements PhotoStorage{

	@Autowired
	private AmazonS3 s3Client;

	@Override
	public String save(MultipartFile photo) {
		String photoName = photo.getOriginalFilename();
		try {
			ObjectMetadata metaData = new ObjectMetadata();
			metaData.setContentType(photo.getContentType());
			metaData.setContentLength(photo.getSize());
			// Folder to Save
			s3Client.putObject("wine", photoName, photo.getInputStream(), metaData);
		} catch (AmazonClientException | IOException e) {
			throw new RuntimeException("Error salving s3 file", e);
		}
		return photoName;
	}
	
	@Override
	public String getUri(String photoName){
		return "http://localhost:9444/s3/wine/"+photoName+"?noAuth=true";
	}

}
