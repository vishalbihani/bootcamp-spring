package com.bootcamp.spring.bookstore.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.bootcamp.spring.bookstore.dto.InventoryUpdateRequest;
import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.entity.Inventory;
import com.bootcamp.spring.bookstore.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {
    private static ObjectMapper mapper;
    private static Inventory inventory;
    private static InventoryUpdateRequest request;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InventoryService service;

    @BeforeAll
    public static void init() {
        mapper = new ObjectMapper();
        request = new InventoryUpdateRequest(25);

        Book book = new Book("1", "Darkness of Light", "A1234");
        inventory = new Inventory(30, 25, book);
    }

    @Test
    public void updateAvailableQuantityShouldReturnUpdatedInventory() throws Exception {
        when(service.updateAvailableQuantity(anyString(), anyInt())).thenReturn(inventory);

        this.mvc.perform(put("/inventory").queryParam("id", "1").contentType(APPLICATION_JSON_VALUE)
                        .content(
                            mapper.writeValueAsBytes(request)
                        ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("message.availableQuantity", is(inventory.getAvailableQuantity())));
    }

    @Test
    public void updateAvailableQuantityShouldReturnNotFoundIfIdDoesNotExist() throws Exception {
        when(service.updateAvailableQuantity(anyString(), anyInt())).thenThrow(NoSuchElementException.class);

        this.mvc.perform(put("/inventory").queryParam("id", "1").contentType(APPLICATION_JSON_VALUE)
                        .content(
                                mapper.writeValueAsBytes(request)
                        ))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is("Inventory not found")));
    }
}
