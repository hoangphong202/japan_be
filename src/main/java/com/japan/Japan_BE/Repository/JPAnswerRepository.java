package com.japan.Japan_BE.Repository;



import com.japan.Japan_BE.Entity.JPAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAnswerRepository extends JpaRepository<JPAnswerEntity, Integer> {
List<JPAnswerEntity> findByJpQuestion_Id(int questId);

}

