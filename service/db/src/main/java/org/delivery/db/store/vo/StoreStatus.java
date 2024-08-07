package org.delivery.db.store.vo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreStatus {

	REGISTERED("등록"),
	UNREGISTERED("해지");

	private String description;

}
