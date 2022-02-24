package com.sheryian.major.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.sheryian.major.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
