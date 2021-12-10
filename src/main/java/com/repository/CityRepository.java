package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
	@Query("SELECT c FROM City c WHERE UPPER(c.name) = UPPER(:name) AND UPPER(c.prov) = UPPER(:prov) ")
	public Optional<City> findByName(@Param("name") String name, @Param("prov") String prov);
	
	@Query("SELECT c FROM City c WHERE UPPER(c.code) = UPPER(:code)")
	public Optional<City> findByCode(@Param("code") String code);
}