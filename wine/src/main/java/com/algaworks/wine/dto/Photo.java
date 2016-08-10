package com.algaworks.wine.dto;
//Data transfer object Patern

public class Photo {
	private String uri;

	public Photo(String uri) {
		this.setName(uri);
	}

	public String getUri() {
		return uri;
	}

	public void setName(String uri) {
		this.uri = uri;
	}
}
