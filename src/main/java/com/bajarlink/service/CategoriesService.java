package com.bajarlink.service;

import com.bajarlink.exception.UserException;
import com.bajarlink.model.Store;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.CategoriesDto;

import java.util.List;

public interface CategoriesService {

    CategoriesDto createCategories(CategoriesDto categoriesDto) throws Exception;
    List<CategoriesDto> getCategoriesByStore(Long storeId);
    CategoriesDto updateCategories(Long id,CategoriesDto categoriesDto) throws Exception;
    void deleteCategories(Long id) throws Exception;
    void checkAuthhorities(User user, Store store) throws Exception;
}
