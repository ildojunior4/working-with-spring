package com.algaworks.wine.dto;
//Data transfer object Patern

public class Photo {
	private String name;

	public Photo(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
