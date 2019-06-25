package com.hrd.springsecurity.service;



import com.hrd.springsecurity.repository.CategoryRepository.CategoryRepository;
import com.hrd.springsecurity.repository.model.Category;
import com.hrd.springsecurity.service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void add(Category category) {
        categoryRepository.add(category);
    }

    @Override
    public int remove(int id) {
        return categoryRepository.remove(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> paginate(int page, int limit) {
        return categoryRepository.paginate(page, limit);
    }

    @Override
    public int countCategory() {
        return categoryRepository.countCategory();
    }

    @Override
    public Category find(int id) {
        return categoryRepository.find(id);
    }

    @Override
    public void update(Category category) {
        categoryRepository.update(category);
    }

    @Override
    public int getLastId() {
        return 0;
    }
}
