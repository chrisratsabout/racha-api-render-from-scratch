package com.racha.rachaapi.dao;

import com.racha.rachaapi.dto.OrderItem;
import com.racha.rachaapi.dto.SelectedItem;

import java.util.List;

public interface OrderItemDao {
    OrderItem getOrderItemById(Integer orderItemId);

    int addItemToOrder(OrderItem orderItem);

    void updateItemInOrder(OrderItem newOrderItem);

    List<SelectedItem> getAll();

    OrderItem getOrderItemByMenuId(Integer menuItemId);

    void deleteById(int id);
}
