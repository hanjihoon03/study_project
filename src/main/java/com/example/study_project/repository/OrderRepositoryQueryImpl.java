package com.example.study_project.repository;

import com.example.study_project.entity.Order;
import com.example.study_project.entity.QItem;
import com.example.study_project.entity.QOrder;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.study_project.entity.QItem.item;
import static com.example.study_project.entity.QOrder.*;

@RequiredArgsConstructor
public class OrderRepositoryQueryImpl implements OrderRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Order> findByOrderFromUserId(Long userId) {
        return jpaQueryFactory.selectFrom(order)
                .where(order.user.id.eq(userId))
                .fetch();
    }

    @Override
    public List<Order> countOrderQuantity(int quantity) {
        return jpaQueryFactory.selectFrom(order)
                .where(order.orderQuantity.goe(quantity))
                .fetch();
    }

    @Override
    public List<Order> countAllOrderQuantitySort() {
        return jpaQueryFactory.selectFrom(order)
                .orderBy(order.orderQuantity.desc())
                .fetch();
    }

}
