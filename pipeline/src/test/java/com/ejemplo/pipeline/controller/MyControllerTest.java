package com.ejemplo.pipeline.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ejemplo.pipeline.model.Producto;
import com.ejemplo.pipeline.repository.ProductoRepository;
import com.ejemplo.pipeline.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private MyController myController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
    }

    @Test
    void testCreateProducto() throws Exception {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto Test");
        producto.setPrecio(100.0);
        when(productoService.saveProducto(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Producto Test"))
                .andExpect(jsonPath("$.precio").value(100.0));
    }
}

