package com.djrhodes.ecommercebackend.api.controller.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class to test ProductController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    /** The Mock MVC. */
    @Autowired
    private MockMvc mvc;

    /**
     * Tests getting of the product list.
     * @throws Exception
     */
    @Test
    public void testProductList() throws Exception {
        mvc.perform(get("/product")).andExpect(status().is(HttpStatus.OK.value()));
    }

}
