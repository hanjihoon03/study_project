package com.example.study_project.repository;

import com.example.study_project.entity.Item;

import java.util.List;

public interface ItemRepositoryQuery {

    List<Item> findItemsByPriceRangeAndQuantity(Integer minPrice, Integer maxPrice, Integer minQuantity);


}
