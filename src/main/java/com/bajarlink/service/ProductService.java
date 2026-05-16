package com.bajarlink.service;

import com.bajarlink.model.Product;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, User user) throws Exception;
    ProductDto updateProduct(Long id,ProductDto productDto, User user) throws Exception;
    void deleteProduct(Long id, User user) throws Exception;
    List<ProductDto> getProductByStoreId(Long storeId);
    List<ProductDto> searchByKeyword(Long storeId, String keyword);
}
