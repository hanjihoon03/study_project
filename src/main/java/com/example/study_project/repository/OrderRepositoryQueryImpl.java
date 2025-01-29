package com.example.study_project.repository;

import com.example.study_project.entity.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

import static com.example.study_project.entity.QItem.item;
import static com.example.study_project.entity.QOrder.*;
import static com.example.study_project.entity.QUser.user;

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

    //사용자 이름과 역할에 따라 주문 조회
    @Override
    public List<Order> findOrdersByUsernameAndRole(String userName, UserRoleEnum role) {

        return jpaQueryFactory.select(order)
                .from(order)
                .join(order.user, user)
                .where(checkUsernameAndRole(userName, role))
                .fetch();
    }

    //특정 사용자의 주문 중 가장 비싼 아이템을 포함한 주문 조회
    @Override
    public List<Order> findOrdersWithMostExpensiveItemByUsername(String username) {
        QItem subItem = new QItem("subItem");

        return jpaQueryFactory
                .selectFrom(order)
                .join(order.user, user)
                .join(order.itemList, item)
                .where(user.username.eq(username).and(
                        item.price.eq(
                        JPAExpressions
                                .select(subItem.price.max())
                                .from(subItem)
                                .where(subItem.order.id.eq(order.id))
                )))
                .fetch();
    }

    private BooleanExpression checkUsernameAndRole(String username, UserRoleEnum role) {
        if (Objects.nonNull(username)) {
            return user.username.eq(username).and(user.role.eq(role));
        } else{
            return null;
        }
    }


}
