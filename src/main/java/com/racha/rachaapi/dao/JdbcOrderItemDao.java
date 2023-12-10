package com.racha.rachaapi.dao;

import com.racha.rachaapi.dto.OrderItem;
import com.racha.rachaapi.dto.SelectedItem;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOrderItemDao implements OrderItemDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderItemDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public OrderItem getOrderItemById(Integer orderItemId) {
        OrderItem orderItem = null;

        String sql = "SELECT * FROM order_item WHERE order_item_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderItemId);
        if (results.next()) {
            orderItem = mapRowToOrderItem(results);
        }
        return orderItem;
    }

    @Override
    public int addItemToOrder(OrderItem orderItem) {
        Integer newOrderItemId = null;

        String sql = "INSERT INTO order_item (menu_item_id, quantity) VALUES (?, ?) RETURNING order_item_id";
        try {
            newOrderItemId = jdbcTemplate.queryForObject(sql, int.class, orderItem.getMenuItemId(), orderItem.getQuantity());

        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
        return newOrderItemId;


    }

    @Override
    public void updateItemInOrder(OrderItem newOrderItem) {
        String sql = "UPDATE order_item SET quantity = ? WHERE order_item_id = ?;";
        try {
            jdbcTemplate.update(sql, newOrderItem.getQuantity(), newOrderItem.getOrderItemId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new RuntimeException("Unable to connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation", e);
        }
    }

    @Override
    public List<SelectedItem> getAll() {
        List<SelectedItem> selectedItemList = new ArrayList<>();

        String sql = "SELECT * FROM order_item JOIN menu_item ON order_item.menu_item_id = menu_item.menu_item_id ORDER BY order_item_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()){
            selectedItemList.add(mapRowToSelectedItem(results));
        }
        return selectedItemList;
    }

    @Override
    public OrderItem getOrderItemByMenuId(Integer menuItemId) {
        OrderItem orderItem = null;

        String sql = "SELECT * FROM order_item WHERE menu_item_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, menuItemId);
        if(results.next()){
            orderItem = mapRowToOrderItem(results);
        }
        return orderItem;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM order_item WHERE order_item_id = ?;";

        jdbcTemplate.update(sql, id);
    }

    private OrderItem mapRowToOrderItem(SqlRowSet results) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(results.getInt("order_item_id"));
        orderItem.setMenuItemId(results.getInt("menu_item_id"));
        orderItem.setQuantity(results.getInt("quantity"));
        return orderItem;
    }

    private SelectedItem mapRowToSelectedItem(SqlRowSet results) {
        SelectedItem selectedItem = new SelectedItem();
        selectedItem.setOrderItemId(results.getInt("order_item_id"));
        selectedItem.setMenuItemId(results.getInt("menu_item_id"));
        selectedItem.setName(results.getString("name"));
        selectedItem.setQuantity(results.getInt("quantity"));
        selectedItem.setPrice(results.getBigDecimal("price"));
        return selectedItem;
    }
}
