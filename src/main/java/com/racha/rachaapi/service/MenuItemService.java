package com.racha.rachaapi.service;

import com.racha.rachaapi.dao.MenuItemDao;
import com.racha.rachaapi.dto.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MenuItemService {
    @Autowired
    MenuItemDao dao;


    public List<MenuItem> getAllMenuItems() {
        return dao.getAll();
    }
}
