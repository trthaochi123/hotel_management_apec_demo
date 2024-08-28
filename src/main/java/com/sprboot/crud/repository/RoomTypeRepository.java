package com.sprboot.crud.repository;

import com.sprboot.crud.entity.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Integer> {
}
