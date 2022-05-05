package com.bootcamp.spring.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.bootcamp.spring.bookstore.dto.ItemDetails;
import com.bootcamp.spring.bookstore.dto.OrderRequest;
import com.bootcamp.spring.bookstore.dto.OrderResponse;
import com.bootcamp.spring.bookstore.repositoryservice.OrderRepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
public class OrderServiceImplTest {

    @Mock
    OrderRepositoryService repositoryService;

    @Mock
    InventoryService inventoryService;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    public void testProcessOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId("user1");

        List<ItemDetails> orderList = new ArrayList<>();
        orderList.add(new ItemDetails("book1", 2));
        orderList.add(new ItemDetails("book2", 3));
        orderRequest.setOrderList(orderList);

        when(inventoryService.getAvailableQuantityById("book1")).thenReturn(5);
        when(inventoryService.getAvailableQuantityById("book2")).thenReturn(10);

        OrderResponse response = orderService.processOrder(orderRequest);

        assertEquals("user1", response.getUserId());
        assertEquals(2, response.getSuccessful().size());
    }

    @Test
    public void testProcessOrderQuantityNotAvailable() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId("user1");
        List<ItemDetails> orderList = new ArrayList<>();
        orderList.add(new ItemDetails("book1", 10));
        orderRequest.setOrderList(orderList);

        when(inventoryService.getAvailableQuantityById("book1")).thenReturn(5);

        OrderResponse response = orderService.processOrder(orderRequest);

        assertEquals("user1", response.getUserId());
        assertEquals(0, response.getSuccessful().size());
        assertEquals(1, response.getFailed().size());
    }
}