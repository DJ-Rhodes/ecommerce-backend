package com.djrhodes.ecommercebackend.service;

import com.djrhodes.ecommercebackend.model.LocalUser;
import com.djrhodes.ecommercebackend.model.WebOrder;
import com.djrhodes.ecommercebackend.model.repository.WebOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for handling order actions.
 */
@Service
public class OrderService {

    /** The Web Order Repository. */
    private WebOrderRepository webOrderRepository;

    /**
     * Constructor for spring injection.
     * @param webOrderRepository The Web Order Repository
     */
    public OrderService(WebOrderRepository webOrderRepository) {
        this.webOrderRepository = webOrderRepository;
    }

    /**
     * Gets the list of orders for a given user.
     * @param user The user to search for.
     * @return The list of orders.
     */
    public List<WebOrder> getOrders(LocalUser user) {
        return  webOrderRepository.findByUser(user);
    }
}
