package org.delivery.storeadmin.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.Store;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.vo.StoreStatus;
import org.delivery.db.storeuser.StoreUser;
import org.delivery.storeadmin.domain.user.converter.StoreUserConverter;
import org.delivery.storeadmin.domain.user.dto.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.user.dto.StoreUserResponse;
import org.delivery.storeadmin.domain.user.service.StoreUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUserBusiness {

    private final StoreUserConverter storeUserConverter;
    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    public StoreUserResponse register(
        StoreUserRegisterRequest request
    ) {
        String storeName = request.storeName();
        Store store = storeRepository.findFirstByNameAndStatusOrderByIdDesc(storeName, StoreStatus.REGISTERED)
            .orElseThrow(IllegalAccessError::new);

        StoreUser storeUser = storeUserConverter.toEntity(request, store);
        StoreUser registeredUser = storeUserService.register(storeUser);
        return storeUserConverter.toResponse(registeredUser, store);
    }

}