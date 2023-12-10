package com.racha.rachaapi.dao;

import com.racha.rachaapi.dto.MenuItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcMenuItemDaoTests extends BaseDaoTests{
    private JdbcMenuItemDao dao;

    @Before
    public void setup() {
        dao = new JdbcMenuItemDao(dataSource);
    }

    @Test
    public void getMenuList_returns_correct_number_of_items_for_list(){
        List<MenuItem> menuItemList = dao.getAll();
        Assert.assertEquals(24, menuItemList.size());
    }

    @Test
    public void getMenuList_returns_incorrect_number_of_items_for_list(){
        List<MenuItem> menuItemList = dao.getAll();
        Assert.assertNotEquals(20, menuItemList.size());
    }
}
