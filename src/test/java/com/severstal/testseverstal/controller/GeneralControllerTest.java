package com.severstal.testseverstal.controller;

import com.severstal.testseverstal.entity.Supply;
import com.severstal.testseverstal.service.ProductService;
import com.severstal.testseverstal.service.ProviderService;
import com.severstal.testseverstal.service.SupplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(GeneralController.class)
class GeneralControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SupplyService supplyService;

    @MockitoBean
    private ProductService productService;

    @MockitoBean
    private ProviderService providerService;

    private Supply supply;

    @BeforeEach
    void setUp() {
        supply = new Supply();
        supply.setId(1);
        supply.setTotalCost(250000.0);
    }

    @Test
    void createSupply_Success() throws Exception {
        when(supplyService.save(any(), eq(1), anyList())).thenReturn(supply);

        mockMvc.perform(post("/provider/{providerId}/supply", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                  "date": "2026-02-05T12:00:00.000Z",
                  "productIds": [1, 2]
                }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(250000.0));
    }
}