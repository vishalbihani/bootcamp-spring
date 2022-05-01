package com.bootcamp.spring.bookstore.controller;

import com.bootcamp.spring.basics.exchange.ResponseBody;
import com.bootcamp.spring.bookstore.dto.InventoryUpdateRequest;
import com.bootcamp.spring.bookstore.entity.Inventory;
import com.bootcamp.spring.bookstore.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PutMapping("/inventory")
    public ResponseEntity<ResponseBody> updateAvailableQuantity(@RequestParam(name = "id") String id,
                                                                @RequestBody InventoryUpdateRequest request) {
        try {
            Inventory inventory = inventoryService.updateAvailableQuantity(id, request.getAvailableQuantity());
            return new ResponseEntity<>(
                    new ResponseBody(HttpStatus.OK.value(), inventory),
                    HttpStatus.OK
            );

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(
                    new ResponseBody(HttpStatus.NOT_FOUND.value(), "Inventory not found"),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseBody(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
