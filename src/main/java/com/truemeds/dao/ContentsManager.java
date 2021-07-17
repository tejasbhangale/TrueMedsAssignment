package com.truemeds.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.truemeds.model.Contents;

public interface ContentsManager extends JpaRepository<Contents, Integer>{

}
