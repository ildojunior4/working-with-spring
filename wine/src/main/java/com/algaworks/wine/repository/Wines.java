package com.algaworks.wine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.wine.model.Wine;

public interface Wines extends JpaRepository<Wine, Long>{

}
