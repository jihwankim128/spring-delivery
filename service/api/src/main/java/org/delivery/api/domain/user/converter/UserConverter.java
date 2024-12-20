package org.delivery.api.domain.user.converter;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.common.annotation.Converter;
import org.delivery.db.user.User;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public User toEntity(final UserRegisterRequest request) {
        return Optional.ofNullable(request)
            .map(it -> User.builder()
                .name(request.name())
                .email(request.email())
                .address(request.address())
                .password(request.password())
                .build()
            ).orElseThrow();
    }

    public UserResponse toResponse(final User entity) {
        return Optional.ofNullable(entity)
            .map(it -> UserResponse.from(entity))
            .orElseThrow();
    }
}
