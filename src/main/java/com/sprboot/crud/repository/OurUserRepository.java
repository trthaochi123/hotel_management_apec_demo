package com.sprboot.crud.repository;

import com.sprboot.crud.entity.OurUserEntity;
import com.sprboot.crud.entity.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OurUserRepository extends JpaRepository<OurUserEntity, Integer> {
    Optional<OurUserEntity> findByEmail(String email);
}
