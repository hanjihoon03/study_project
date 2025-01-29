package com.example.study_project.repository;

import com.example.study_project.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryQuery {
}
