package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.dto.ItemDetails;
import com.bootcamp.spring.bookstore.dto.OrderRequest;
import com.bootcamp.spring.bookstore.dto.OrderResponse;
import com.bootcamp.spring.bookstore.entity.Order;
import com.bootcamp.spring.bookstore.repositoryservice.OrderRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepositoryService repositoryService;

    @Autowired
    InventoryService inventoryService;

    /**
     * It will take in the order request containing the user id,
     * book ids, and their respective required quantities. It will
     * check for the available quantity in the inventory, if it can
     * be fulfilled then it will update the inventory and add record
     * to the order repository.
     *
     * @param orderRequest Order request
     * @return Order response
     */
    @Override
    public OrderResponse processOrder(OrderRequest orderRequest) {
        String orderId = UUID.randomUUID().toString();
        String userId = orderRequest.getUserId();
        OrderResponse response = new OrderResponse(userId);
        List<Order> orders = new ArrayList<>();

        for (ItemDetails item : orderRequest.getOrderList()) {
            if (isQuantityAvailable(item.getBookId(), item.getQuantity())) {

                try {
                    // Insert the order record
                    Order order = new Order(orderId, userId, item.getBookId(), item.getQuantity());
                    repositoryService.save(order);

                    // Update the available quantity in the inventory table
                    updateAvailableQuantity(item.getBookId(), item.getQuantity());

                } catch (Exception e) {
                    response.addItemToFailedList(item);
                    continue;
                }

                response.addItemToSuccessList(item);
                continue;
            }

            // Required quantity could not be fulfilled so adding to failed list.
            response.addItemToFailedList(item);
        }
        return response;
    }

    @Override
    public List<Order> findAllByUserId(String userId) {
        return repositoryService.findAllByUserId(userId);
    }

    private boolean isQuantityAvailable(String id, int quantity) {
        int availableQuantity = inventoryService.getAvailableQuantityById(id);

        return availableQuantity >= quantity;
    }

    private void updateAvailableQuantity(String id, int orderQuantity) throws Exception {
        int availableQuantity = inventoryService.getAvailableQuantityById(id);
        int updatedQuantity = availableQuantity - orderQuantity;

        inventoryService.updateAvailableQuantity(id, updatedQuantity);
    }
}
