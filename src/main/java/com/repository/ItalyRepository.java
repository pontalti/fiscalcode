package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.Italy;

public interface ItalyRepository extends JpaRepository<Italy, Long> {
	
	@Query("SELECT i FROM Italy i WHERE UPPER(i.cityName) = UPPER(:name) AND UPPER(i.prov) = UPPER(:prov) ")
	public Optional<Italy> findByCityName(@Param("name") String name, @Param("prov") String prov);
	
	@Query("SELECT i FROM Italy i WHERE UPPER(i.code) = UPPER(:code)")
	public Optional<Italy> findByCode(@Param("code") String code);
}