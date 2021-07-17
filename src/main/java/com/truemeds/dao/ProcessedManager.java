package com.truemeds.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truemeds.model.Processed;

public interface ProcessedManager extends JpaRepository<Processed, Integer>{

}
