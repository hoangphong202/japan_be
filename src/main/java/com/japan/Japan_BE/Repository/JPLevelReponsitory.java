package com.japan.Japan_BE.Repository;


import com.japan.Japan_BE.Entity.JPLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPLevelReponsitory extends JpaRepository<JPLevelEntity, Integer> {
    JPLevelEntity findById(int levelId);
}
