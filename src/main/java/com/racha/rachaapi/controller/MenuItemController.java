package com.racha.rachaapi.controller;

import com.racha.rachaapi.dto.MenuItem;
import com.racha.rachaapi.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/menu-items")
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;

    public MenuItem createMenuItem(MenuItem menuItem){
        return null;
    }

    @GetMapping("")
    public List<MenuItem> getAllMenuItems(){
        return menuItemService.getAllMenuItems();
    }

    public MenuItem getMenuItemById(@PathVariable int id){
        return null;
    }

    public MenuItem getMenuItemByName(@RequestParam String name){
        return null;
    }

    public MenuItem updateMenuItem(int id, MenuItem menuItem){
        return null;
    }

    public MenuItem replaceMenuItem(int id, MenuItem menuItem){
        return null;
    }

    public void deleteMenuItem(int id){

    }


}
