package com.japan.Japan_BE.Repository;

import com.japan.Japan_BE.Entity.JPQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPQuestionRepository extends JpaRepository<JPQuestionEntity, Integer> {

    List<JPQuestionEntity> findByJpRoom_Id(int roomId);

    JPQuestionEntity findQuestionById(int questionId);

    List<JPQuestionEntity> findQuestionByjpRoom_Id (int RoomId);


}

