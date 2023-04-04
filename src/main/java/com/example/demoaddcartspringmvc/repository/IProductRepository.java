package com.example.demoaddcartspringmvc.repository;

import com.example.demoaddcartspringmvc.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {

}
