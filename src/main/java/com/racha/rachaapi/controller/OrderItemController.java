package com.racha.rachaapi.controller;

import com.racha.rachaapi.dto.Order;
import com.racha.rachaapi.dto.OrderItem;
import com.racha.rachaapi.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("")
    public Order getAll(){
        return orderItemService.getAllOrderItems();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/items")
    public OrderItem addItemToOrder(@RequestBody OrderItem orderItem){
        return orderItemService.addItemToOrder(orderItem);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
       orderItemService.deleteById(id);
    }


}
