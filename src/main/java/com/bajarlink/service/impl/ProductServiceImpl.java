package com.bajarlink.service.impl;

import com.bajarlink.mapper.ProductMapper;
import com.bajarlink.model.Categories;
import com.bajarlink.model.Product;
import com.bajarlink.model.Store;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.ProductDto;
import com.bajarlink.repo.CategoriesRepo;
import com.bajarlink.repo.ProductRepo;
import com.bajarlink.repo.StoreRepo;
import com.bajarlink.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final StoreRepo storeRepo;
    private final CategoriesRepo categoriesRepo;

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepo.findById(productDto.getStoreId()).orElseThrow(
                () -> new Exception("Store not found")
        );
        if(productDto.getCategoryId() == null){
            throw new Exception("Category Id is required");
        }
        Categories categories = categoriesRepo.findById(productDto.getCategoryId()).orElseThrow(
                () -> new Exception("Category not found")
        );

        Product product = ProductMapper.toEntity(productDto, store, categories);
        Product updatedProduct = productRepo.save(product);
        return ProductMapper.toDto(updatedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product = productRepo.findById(id).orElseThrow(
                () -> new Exception("product not found")
        );
        if(productDto.getCategoriesDto() != null) {
            Categories categories = categoriesRepo.findById(productDto.getCategoryId()).orElseThrow(
                    () -> new Exception("Category not found")
            );
            product.setCategory(categories);
        }

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setBrand(productDto.getBrand());
        product.setSku(productDto.getSku());
        product.setMrp(productDto.getMrp());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setImage(productDto.getImage());
        product.setUpdatedAt(productDto.getUpdatedAt());

        Product updatedProduct = productRepo.save(product);
        return ProductMapper.toDto(updatedProduct);

    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepo.findById(id).orElseThrow(
                () -> new Exception("Product not found")
        );
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductDto> getProductByStoreId(Long storeId) {
        List<Product> products = productRepo.findByStoreId(storeId);
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {
        List<Product> products = productRepo.searchByKeyword(storeId, keyword);
        return  products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }
}
