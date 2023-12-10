package com.racha.rachaapi.dao;

import com.racha.rachaapi.dto.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MenuItemDao {
    List<MenuItem> getAll();
}
