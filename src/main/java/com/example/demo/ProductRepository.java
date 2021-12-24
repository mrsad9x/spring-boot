package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {		// có các phương thức crud tương tác với entity

}
