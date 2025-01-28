package com.example.study_project.repository;

import com.example.study_project.entity.Order;

import java.util.List;

public interface OrderRepositoryQuery {

    List<Order> findByOrderFromUserId(Long userId);
    List<Order> countOrderQuantity(int quantity);
    List<Order> countAllOrderQuantitySort();

}
