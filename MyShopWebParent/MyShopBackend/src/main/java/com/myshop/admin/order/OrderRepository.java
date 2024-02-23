package com.myshop.admin.order;

import com.myshop.common.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from Order o where o.firstName like ?2 or o.lastName like ?2" +
            " or o.phoneNumber like ?2 or o.address like ?2 or o.status = ?2 or o.customer.firstName = ?2 or " +
            "o.customer.lastName like ?2 ")
    Page<Order> findAll(Pageable pageable,String keyword);
}
