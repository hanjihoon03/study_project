package com.example.study_project.repository;

import com.example.study_project.entity.QOrder;
import com.example.study_project.entity.User;
import com.example.study_project.entity.UserRoleEnum;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.study_project.entity.QOrder.*;
import static com.example.study_project.entity.QUser.*;

@RequiredArgsConstructor
public class UserRepositoryQueryImpl implements UserRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;

    /**
     *  파라미터로 받은 유저의 이름을 포함한 user 들을 반환
     *  select user from user where username like "username"
     * @param username 포함할 username
     * @return 포함된 user
     */
    @Override
    public List<User> findByUsernameContain(String username) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.username.contains(username))
                .fetch();
    }

    /**
     *  파라미터로 받은 이름으로 시작하는 user를 찾는 쿼리
     *  select user from user where username like "username"%
     * @param username
     * @return
     */
    @Override
    public List<User> findByUsernameStartWith(String username) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.username.startsWith(username))
                .fetch();
    }

    /**
     * 특정 이메일 도메인을 가진 유저를 조회
     * @param email
     * @return
     */
    @Override
    public List<User> findByEmailContain(String email) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.email.contains(email))
                .fetch();
    }

    /**
     * 고정된 이메일 도메인을 가진 유저를 조회
     * @param
     * @return
     */
    @Override
    public List<User> findByFixEmailContain() {
        return jpaQueryFactory.selectFrom(user)
                .where(user.email.contains("@naver.com"))
                .fetch();
    }

    @Override
    public List<User> checkRole() {
        return jpaQueryFactory.selectFrom(user)
                .where(user.role.eq(UserRoleEnum.USER))
                .fetch();

    }

    @Override
    public List<User> userHasOrder() {
        return jpaQueryFactory.selectFrom(user)
                .where(user.orderList.isNotEmpty())
                .fetch();
    }

    @Override
    public List<User> orderCountSort() {
        return jpaQueryFactory.selectFrom(user)
                .leftJoin(user.orderList, order)
                .groupBy(user.id)
                .orderBy(order.count().desc())
                .fetch();
    }
}
