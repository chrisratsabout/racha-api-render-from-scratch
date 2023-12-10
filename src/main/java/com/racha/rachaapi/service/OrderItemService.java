package com.racha.rachaapi.service;

import com.racha.rachaapi.dao.MenuItemDao;
import com.racha.rachaapi.dao.OrderItemDao;
import com.racha.rachaapi.dto.MenuItem;
import com.racha.rachaapi.dto.Order;
import com.racha.rachaapi.dto.OrderItem;
import com.racha.rachaapi.dto.SelectedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;



    public OrderItem addItemToOrder(OrderItem orderItem) {

        OrderItem newOrderItem = orderItemDao.getOrderItemByMenuId(orderItem.getMenuItemId());

        if (newOrderItem == null) {
            int orderItemId = orderItemDao.addItemToOrder(orderItem);
            return orderItemDao.getOrderItemById(orderItemId);
        } else {
            newOrderItem.setQuantity(orderItem.getQuantity() + newOrderItem.getQuantity());
            orderItemDao.updateItemInOrder(newOrderItem);
            return newOrderItem;
        }
    }

    public Order getAllOrderItems() {
        Order order = new Order();
        List<SelectedItem> selectedItemList = orderItemDao.getAll();

        BigDecimal subtotal = BigDecimal.ZERO;

        for(SelectedItem item : selectedItemList){
            subtotal = subtotal.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }

        BigDecimal taxAmount = subtotal.multiply(new BigDecimal(".0575")).setScale(2, RoundingMode.UP);
        BigDecimal total = subtotal.add(taxAmount).setScale(2);
        order.setSelectedItemList(selectedItemList);

        order.setSubtotal(subtotal);
        order.setTaxAmount(taxAmount);
        order.setTotal(total);

        return order;

    }

    public void deleteById(int id) {
        orderItemDao.deleteById(id);

    }
}
