package com.ejemplo.pipeline.repository;

import com.ejemplo.pipeline.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
