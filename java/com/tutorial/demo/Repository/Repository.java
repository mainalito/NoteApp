package com.tutorial.demo.Repository;
import java.util.List;

import com.tutorial.demo.models.Notebook;

import org.springframework.data.repository.CrudRepository;


public interface Repository extends CrudRepository<Notebook, Long> {
    List<Notebook> findByTitleContaining(String title);
}
