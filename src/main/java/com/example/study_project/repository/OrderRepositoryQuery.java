package com.example.study_project.repository;

import com.example.study_project.entity.Order;
import com.example.study_project.entity.UserRoleEnum;

import java.util.List;

public interface OrderRepositoryQuery {

    List<Order> findByOrderFromUserId(Long userId);
    List<Order> countOrderQuantity(int quantity);
    List<Order> countAllOrderQuantitySort();

    List<Order> findOrdersByUsernameAndRole(String userName, UserRoleEnum role);

    List<Order> findOrdersWithMostExpensiveItemByUsername(String username);
}
