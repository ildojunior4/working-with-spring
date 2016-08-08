package com.algaworks.wine.model;

public enum WineType {
	TINTO("Red"),
	BRANCO("White"),
	ROSE("Rose");
	
	private String description;
	
	WineType(String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
