package com.bajarlink.service;

import com.bajarlink.domain.StoreStatus;
import com.bajarlink.exception.UserException;
import com.bajarlink.model.Store;
import com.bajarlink.model.User;
import com.bajarlink.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {
    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws UserException;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id, StoreDto storeDto) throws UserException;
    void deleteStore(Long id) throws UserException;
    StoreDto getStoreByEmployee() throws UserException;

    StoreDto moderateStore(Long id, StoreStatus storeStatus) throws UserException;
}
