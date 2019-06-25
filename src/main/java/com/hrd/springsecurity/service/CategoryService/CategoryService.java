package com.hrd.springsecurity.service.CategoryService;

import com.hrd.springsecurity.repository.model.Category;

import java.util.List;

public interface CategoryService {
    void add(Category article);
    int remove(int id);
    List<Category> findAll();

    List<Category> paginate(int page, int limit);

    Category find(int id);

    void update(Category article);

    int countCategory();

    int getLastId();
}
