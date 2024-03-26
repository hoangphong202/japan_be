package com.japan.Japan_BE.Controller;


import com.japan.Japan_BE.Entity.JPAnswerEntity;
import com.japan.Japan_BE.Entity.JPLevelEntity;
import com.japan.Japan_BE.Entity.JPQuestionEntity;
import com.japan.Japan_BE.Entity.JPRoomEntity;
import com.japan.Japan_BE.Service.JPAnswerService;
import com.japan.Japan_BE.Service.JPLevelService;
import com.japan.Japan_BE.Service.JPQuestionService;
import com.japan.Japan_BE.Service.JPRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class QuizControllerAPI {

    @Autowired
    private JPLevelService jpLevelService;

    @Autowired
    private JPRoomService jpRoomService;

    @GetMapping("")
    public ResponseEntity<String> hello(){
        String h = "hello";
        return ResponseEntity.ok().body(h);
    }


    @GetMapping("/JPLevel")
    public ResponseEntity<List<JPLevelEntity>> getJPLevels(){

        List<JPLevelEntity> listLevel = jpLevelService.GetAllJPLevel();
        return ResponseEntity.ok().body(listLevel);
    }

    @GetMapping("/JPLevel/{levelId}/JPRoom")
    public ResponseEntity<List<JPRoomEntity>> getJPRoomsByIdLevel(@PathVariable int levelId){
        List<JPRoomEntity> jpRooms = jpRoomService.GetAllJPRoomByLevelId(levelId);

//        return new ResponseEntity<>(jpRooms, HttpStatus.OK);
        return ResponseEntity.ok().body(jpRooms);
    }

    @Autowired
    private JPQuestionService jpQuestionService;

    @Autowired
    private JPAnswerService jpAnswerService;

    @GetMapping("/JPLevel/{levelId}/JPRoom/{roomId}/Question")
    public ResponseEntity< List<JPQuestionEntity>> showQuestion(@PathVariable int roomId, @PathVariable int levelId) {

        List<JPQuestionEntity> listJPQuestion = jpQuestionService.GetAllJPQuestionByRoomId(roomId);

        // Xáo trộn danh sách
        Collections.shuffle(listJPQuestion);

        return ResponseEntity.ok().body(listJPQuestion);
    }

    @GetMapping("/JPLevel/{levelId}/JPRoom/{roomId}/Question/{questionId}")
    public ResponseEntity<List<JPAnswerEntity>> showAnswer(@PathVariable int roomId, @PathVariable int levelId,@PathVariable int questionId) {

        List<JPAnswerEntity> listJPAnswer = jpAnswerService.GetAllJPAnswerByQuestionId(questionId);


        return ResponseEntity.ok().body(listJPAnswer);
    }


}
