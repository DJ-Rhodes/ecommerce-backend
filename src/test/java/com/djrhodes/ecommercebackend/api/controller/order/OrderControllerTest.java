package com.djrhodes.ecommercebackend.api.controller.order;

import com.djrhodes.ecommercebackend.model.WebOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class for testing OrderController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    /** The Mock MV. */
    @Autowired
    private MockMvc mvc;

    /**
     * Tests that when requested authenticated order list it belongs to UserA.
     * @throws Exception
     */
    @Test
    @WithUserDetails("UserA")
    public void testUserAAuthenticatedOrderList() throws Exception {
        testAuthenticatedListBelongsToUser("UserA");
    }

    /**
     * Tests that when requested authenticated order list it belongs to UserB.
     * @throws Exception
     */
    @Test
    @WithUserDetails("UserB")
    public void testUserBAuthenticatedOrderList() throws Exception {
        testAuthenticatedListBelongsToUser("UserB");
    }

    /**
     * Tests that requested authenticated order list belongs to the given user.
     * @param username the username to test for.
     * @throws Exception
     */
    private void testAuthenticatedListBelongsToUser(String username) throws Exception {
        mvc.perform(get("/order")).andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    List<WebOrder> orders = new ObjectMapper().readValue(json, new TypeReference<List<WebOrder>>() {});
                    for(WebOrder order : orders) {
                        Assertions.assertEquals(username, order.getUser().getUsername(), "Order should only be those belonging to the user.");
                    }
                });
    }

    /**
     * Tests that the unauthenticated users do not receive data.
     * @throws Exception
     */
    @Test
    public void testUnauthenticatedOrderList() throws Exception {
        mvc.perform(get("/order")).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

}
