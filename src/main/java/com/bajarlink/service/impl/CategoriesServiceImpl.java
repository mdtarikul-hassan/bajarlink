package com.bajarlink.service.impl;

import com.bajarlink.domain.UserRole;
import com.bajarlink.exception.UserException;
import com.bajarlink.mapper.CategoriesMapper;
import com.bajarlink.model.Categories;
import com.bajarlink.model.Store;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.CategoriesDto;
import com.bajarlink.repo.CategoriesRepo;
import com.bajarlink.repo.StoreRepo;
import com.bajarlink.service.CategoriesService;
import com.bajarlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepo categoriesRepo;
    private final StoreRepo storeRepo;
    private final UserService userService;

    @Override
    public CategoriesDto createCategories(CategoriesDto categoriesDto) throws Exception {
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new UserException("User not logged in");
        }
        Store store = storeRepo.findById(categoriesDto.getStoreId()).orElseThrow(
                () -> new Exception("Store not found..")
        );
        Categories categories = Categories.builder()
                .name(categoriesDto.getName())
                .store(store)
                .build();

        checkAuthhorities(user, categories.getStore());

        return CategoriesMapper.toDto(categoriesRepo.save(categories));
    }

    @Override
    public List<CategoriesDto> getCategoriesByStore(Long storeId) {
        List<Categories> dtos = categoriesRepo.findByStoreId(storeId);
        return dtos.stream().map(
                CategoriesMapper::toDto
        ).collect(Collectors.toList());
    }

    @Override
    public CategoriesDto updateCategories(Long id, CategoriesDto categoriesDto) throws Exception {
        Categories categories = categoriesRepo.findById(id).orElseThrow(
                () -> new Exception("Categories not found..")
        );
        User user = userService.getCurrentUser();

        checkAuthhorities(user, categories.getStore());
        categories.setName(categoriesDto.getName());

        return CategoriesMapper.toDto(categoriesRepo.save(categories));
    }

    @Override
    public void deleteCategories(Long id) throws Exception {
        Categories categories = categoriesRepo.findById(id).orElseThrow(
                () -> new Exception("Categories not found")
        );
        User user = userService.getCurrentUser();

        checkAuthhorities(user, categories.getStore());
        categoriesRepo.delete(categories);
    }

    @Override
    public void checkAuthhorities(User user, Store store) throws Exception {
        boolean isStoreAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.getRole().equals(store.getStoreAdmin().getRole());

        if(!(isStoreAdmin && isSameStore) && !isManager){
            throw new Exception("you don't have to permission to do this..");
        }
    }


}
