package com.oktenweb.javaadvanced.dao;

import com.oktenweb.javaadvanced.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.StoredProcedureParameter;

public interface DirectorDao extends JpaRepository<Director, Integer> {

  @Query("select d from Director d join fetch d.movies where d.name=:name")
  Director findByName(String name);
}
