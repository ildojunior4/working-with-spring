package com.algaworks.wine.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "wine")
public class Wine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotNull(message= "Type is required")
	@Enumerated(EnumType.STRING)
	@Column(name="otype")
	private WineType type;
	
	@NotNull(message= "Harvest is required")
	private Integer harvest;
	
	@NotNull(message= "Volume is required")
	private Integer volume;

	@NotNull(message= "Value is required")
	@Column(name="ovalue")
	private BigDecimal value;
	
	private String photo;


	//POJO
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WineType getType() {
		return type;
	}

	public void setType(WineType type) {
		this.type = type;
	}

	public Integer getHarvest() {
		return harvest;
	}

	public void setHarvest(Integer harvest) {
		this.harvest = harvest;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wine other = (Wine) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}
