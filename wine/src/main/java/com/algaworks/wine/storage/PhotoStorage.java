package com.algaworks.wine.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorage {
	
	public String getUri(String photoName);
	public String save(MultipartFile photo);
}
