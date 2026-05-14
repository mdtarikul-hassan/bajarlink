package com.bajarlink.service.impl;

import com.bajarlink.domain.StoreStatus;
import com.bajarlink.exception.UserException;
import com.bajarlink.mapper.StoreMapper;
import com.bajarlink.model.Store;
import com.bajarlink.model.StoreContact;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.StoreDto;
import com.bajarlink.repo.StoreRepo;
import com.bajarlink.repo.UserRepo;
import com.bajarlink.service.StoreService;
import com.bajarlink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepo storeRepo;
    private final UserService userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto,user);
        return StoreMapper.toDto(storeRepo.save(store));
    }

    @Override
    public StoreDto getStoreById(Long id) throws UserException {
        Store store = storeRepo.findById(id).orElseThrow(
                () -> new UserException("Store not found...")
        );
        return StoreMapper.toDto(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        List<Store> dtos = storeRepo.findAll();
        return dtos.stream().map(StoreMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();
        return storeRepo.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws UserException {
        User currentUser = userService.getCurrentUser();
        Store existingStore = storeRepo.findByStoreAdminId(currentUser.getId());
        if(existingStore == null){
            throw new UserException("Store not found...");
        }
        existingStore.setBrand(existingStore.getBrand());
        existingStore.setDescription(existingStore.getDescription());

        if(storeDto.getStoreType() != null){
            existingStore.setStoreType(storeDto.getStoreType());
        }

        if(storeDto.getContact() != null){
            StoreContact contact = StoreContact.builder()
                    .email(storeDto.getContact().getEmail())
                    .phone(storeDto.getContact().getPhone())
                    .address(storeDto.getContact().getAddress())
                    .build();
            existingStore.setContact(contact);
        }

        Store updatedStore = storeRepo.save(existingStore);

        return StoreMapper.toDto(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws UserException {
        Store store = getStoreByAdmin();

        storeRepo.delete(store);
    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User curreentUser = userService.getCurrentUser();

        if(curreentUser == null){
            throw new UserException("You don't have permission to access this");
        }
        return StoreMapper.toDto(curreentUser.getStore());
    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus storeStatus) throws UserException {
        Store store = storeRepo.findById(id).orElseThrow(
                () -> new UserException("store not found...")
        );
        store.setStatus(storeStatus);
        Store updateStore = storeRepo.save(store);
        return StoreMapper.toDto(updateStore);
    }
}
