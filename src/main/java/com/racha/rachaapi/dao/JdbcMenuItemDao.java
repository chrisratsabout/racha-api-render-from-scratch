package com.racha.rachaapi.dao;

import com.racha.rachaapi.dto.MenuItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMenuItemDao implements MenuItemDao{


    private final JdbcTemplate jdbcTemplate;

//    public JdbcMenuItemDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public JdbcMenuItemDao(DataSource dataSource) {
        jdbcTemplate =new JdbcTemplate(dataSource);
    }

    @Override
    public List<MenuItem> getAll() {
        List<MenuItem> menuItemList = new ArrayList<>();

        String sql = "SELECT * FROM menu_item;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()){
            menuItemList.add(mapRowToMenuItem(results));
        }
        return menuItemList;
    }

    private MenuItem mapRowToMenuItem(SqlRowSet results){
        MenuItem menuItem = new MenuItem();
        menuItem.setMenuItemId(results.getInt("menu_item_id"));
        menuItem.setDescription(results.getString("description"));
        menuItem.setName(results.getString("name"));
        menuItem.setPrice(results.getBigDecimal("price"));
        return menuItem;
    }

}
