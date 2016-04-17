package com.linkcm.core.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T, ID extends Serializable> extends
		JpaRepository<T, ID>, BaseRepositoryCustom<T,ID> {
}
