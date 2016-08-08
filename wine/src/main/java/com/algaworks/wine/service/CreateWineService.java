package com.algaworks.wine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.wine.model.Wine;
import com.algaworks.wine.repository.Wines;

@Service
public class CreateWineService {
	
	@Autowired
	private Wines wines;
	
	public void save(Wine wine){
		//Write job rules
		this.wines.save(wine);
	}
	
	public void addPhoto(Long code, String name){
		Wine wine = wines.findOne(code);
		wine.setPhoto(name);
		wines.save(wine);
	}

}