package org.delivery.db.storeuser.vo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreUserStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지");

    private final String description;

}
