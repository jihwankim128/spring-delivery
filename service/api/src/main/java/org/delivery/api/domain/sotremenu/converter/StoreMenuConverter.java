package org.delivery.api.domain.sotremenu.converter;

import java.util.List;
import java.util.Optional;
import org.delivery.api.domain.sotremenu.dto.request.StoreMenuRegisterRequest;
import org.delivery.api.domain.sotremenu.dto.response.StoreMenuResponse;
import org.delivery.api.domain.sotremenu.exception.StoreMenuExceptionType;
import org.delivery.common.annotation.Converter;
import org.delivery.common.exception.model.BadRequestException;
import org.delivery.db.store.Store;
import org.delivery.db.storemenu.StoreMenu;

@Converter
public class StoreMenuConverter {

    public StoreMenu toEntity(final StoreMenuRegisterRequest request, final Store store) {
        return Optional.ofNullable(request)
            .map(it -> StoreMenu.builder()
                .store(store)
                .name(request.name())
                .amount(request.amount())
                .thumbnailUrl(request.thumbnailUrl())
                .build()
            ).orElseThrow(() -> new BadRequestException(StoreMenuExceptionType.NULL_POINT_EXCEPTION));
    }

    public StoreMenuResponse toResponse(final StoreMenu storeMenu) {
        return Optional.ofNullable(storeMenu)
            .map(it -> new StoreMenuResponse(
                storeMenu.getId(),
                storeMenu.getStore().getId(),
                storeMenu.getName(),
                storeMenu.getAmount(),
                storeMenu.getStatus(),
                storeMenu.getThumbnailUrl(),
                storeMenu.getLikeCount(),
                storeMenu.getSequence()
            )).orElseThrow(() -> new BadRequestException(StoreMenuExceptionType.NULL_POINT_EXCEPTION));
    }

    public List<StoreMenuResponse> toResponse(final List<StoreMenu> storeMenus) {
        return storeMenus.stream()
            .map(this::toResponse)
            .toList();
    }

}
