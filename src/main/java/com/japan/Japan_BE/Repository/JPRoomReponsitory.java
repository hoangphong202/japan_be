package com.japan.Japan_BE.Repository;

import com.japan.Japan_BE.Entity.JPRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPRoomReponsitory extends JpaRepository<JPRoomEntity, Integer> {
    List<JPRoomEntity> findByJpLevel_Id(int levelId);
    JPRoomEntity findById(int roomId);

    JPRoomEntity findRoomByJpLevel_Id(int levelId);
}
