package com.bajarlink.controller;

import com.bajarlink.payload.dto.CategoriesDto;
import com.bajarlink.payload.response.ApiResponse;
import com.bajarlink.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @PostMapping
    public ResponseEntity<CategoriesDto> createCategories(@RequestBody CategoriesDto categoriesDto) throws Exception {

        return ResponseEntity.ok(categoriesService.createCategories(categoriesDto));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoriesDto>> getCategoriesFromStore(@PathVariable Long storeId) throws Exception {

        return ResponseEntity.ok(categoriesService.getCategoriesByStore(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriesDto> updateCategories(@PathVariable Long id,
                                                          @RequestBody CategoriesDto categoriesDto) throws Exception {

        return ResponseEntity.ok(categoriesService.updateCategories(id, categoriesDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategories(@PathVariable Long id) throws Exception {
        categoriesService.deleteCategories(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Categories successfully deleted");
        return ResponseEntity.ok(apiResponse);
    }
}
