package com.example.study_project.repository;

import com.example.study_project.entity.Item;
import com.example.study_project.entity.QItem;
import com.example.study_project.entity.QOrder;
import com.example.study_project.entity.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.study_project.entity.QItem.item;
import static com.example.study_project.entity.QOrder.order;
import static com.example.study_project.entity.QUser.user;

@RequiredArgsConstructor
public class ItemRepositoryQueryImpl implements ItemRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;

    //가격 범위와 수량에 따라 아이템 조회

    @Override
    public List<Item> findItemsByPriceRangeAndQuantity(Integer minPrice, Integer maxPrice, Integer minQuantity){
        jpaQueryFactory.selectFrom(item)
                .where(itemsByPriceRangeAndQuantity(minPrice,maxPrice,minQuantity))
                .fetch();
        return null;
    }

    private BooleanBuilder itemsByPriceRangeAndQuantity(Integer minPrice, Integer maxPrice, Integer minQuantity){
        BooleanBuilder builder = new BooleanBuilder();
        if (minPrice != null) {
            builder.and(item.price.goe(minPrice));
        }
        if (maxPrice != null) {
            builder.and(item.price.loe(maxPrice));
        }
        if (minQuantity != null) {
            builder.and(item.quantity.goe(minQuantity));
        }

        return builder;
    }
}
