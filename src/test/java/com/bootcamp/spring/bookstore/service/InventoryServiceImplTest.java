package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.entity.Inventory;
import com.bootcamp.spring.bookstore.repositoryservice.InventoryRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class InventoryServiceImplTest {

    @Mock
    private InventoryRepositoryService repositoryService;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    public void testUpdateAvailableQuantity() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setId("1");
        inventory.setMaxAllowedQuantity(10);
        inventory.setAvailableQuantity(5);

        Inventory updatedInventory = new Inventory();
        updatedInventory.setId("1");
        updatedInventory.setMaxAllowedQuantity(10);
        updatedInventory.setAvailableQuantity(8);

        when(repositoryService.findById(Mockito.anyString())).thenReturn(inventory);
        when(repositoryService.update(any())).thenReturn(updatedInventory);

        Inventory receivedInventory = inventoryService.updateAvailableQuantity("1", 8);

        Assertions.assertEquals(8, receivedInventory.getAvailableQuantity());
    }

    @Test
    public void testUpdateAvailableQuantityMaxAllowedQuantityIsLessThanAvailableQuantity() throws Exception {
        String id = "1";
        int maxAllowedQuantity = 10;
        int availableQuantity = 20;

        Inventory inventory = new Inventory();
        inventory.setId(id);
        inventory.setMaxAllowedQuantity(maxAllowedQuantity);
        inventory.setAvailableQuantity(availableQuantity);

        when(repositoryService.findById(id)).thenReturn(inventory);

        Assertions.assertThrows(Exception.class, () -> inventoryService.updateAvailableQuantity(id, availableQuantity));
    }

}
