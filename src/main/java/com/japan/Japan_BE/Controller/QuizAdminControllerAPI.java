package com.japan.Japan_BE.Controller;


import com.japan.Japan_BE.Entity.JPAnswerEntity;
import com.japan.Japan_BE.Entity.JPLevelEntity;
import com.japan.Japan_BE.Entity.JPQuestionEntity;
import com.japan.Japan_BE.Entity.JPRoomEntity;
import com.japan.Japan_BE.Repository.JPAnswerRepository;
import com.japan.Japan_BE.Service.JPAnswerService;
import com.japan.Japan_BE.Service.JPLevelService;
import com.japan.Japan_BE.Service.JPQuestionService;
import com.japan.Japan_BE.Service.JPRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-admin")
@CrossOrigin
public class QuizAdminControllerAPI {
    @Autowired
    private JPQuestionService jpQuestionService;

    @Autowired
    private JPAnswerService jpAnswerService;

    @Autowired
    private JPLevelService jpLevelService;

    @Autowired
    private JPRoomService jpRoomService;

    // ...

    @GetMapping("")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok().body("hello admin");
    }

    @GetMapping("/JPLevel")
    public ResponseEntity<List<JPLevelEntity>> GetAllLevel(){
        List<JPLevelEntity> jpLevelEntities = jpLevelService.GetAllJPLevel();


        return ResponseEntity.ok().body(jpLevelEntities);
    }

    @PostMapping("/JPLevel/Insert")
    public ResponseEntity<JPLevelEntity> addLevel(@RequestBody JPLevelEntity jpLevelEntity){
        JPLevelEntity jpLevel = jpLevelService.InsertLevel(jpLevelEntity);
        return ResponseEntity.ok().body(jpLevel);
    }

    @DeleteMapping("/JPLevel/{levelId}/Delete")
    public ResponseEntity<String> deleteLevel(@PathVariable int levelId){
        String message = jpLevelService.DeleteLevelByLevelId(levelId);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/JPLevel/{levelId}/Update")
    public ResponseEntity<JPLevelEntity> updateLevel(@PathVariable int levelId,@RequestBody JPLevelEntity jpLevelEntity){
        JPLevelEntity jpLevel = jpLevelService.UpdateLevelByLevelId(levelId,jpLevelEntity);
        return ResponseEntity.ok().body(jpLevel);
    }


    @GetMapping("/JPLevel/{levelId}/JPRoom")
    public ResponseEntity<List<JPRoomEntity>> Room(@PathVariable int levelId){
        List<JPRoomEntity> jpRoomEntities = jpRoomService.GetAllJPRoomByLevelId(levelId);

        return ResponseEntity.ok().body(jpRoomEntities);
    }


    @PostMapping("/JPLevel/{levelId}/JPRoom/InsertRoom")
    public ResponseEntity<JPRoomEntity> insertRoom(@RequestBody JPRoomEntity jpRoom,
                                                   @PathVariable int levelId)  {

        JPRoomEntity jpRoomEntity = jpRoomService.InsertRoom( jpRoom, levelId);

        return ResponseEntity.ok().body(jpRoomEntity);

    }

    @PutMapping("/JPLevel/{levelId}/JPRoom/{roomId}/UpdateRoom")
    public ResponseEntity<?> UpdateRoom(@RequestBody JPRoomEntity jpRoom,
                                                   @PathVariable int roomId)  {

        JPRoomEntity jpRoomEntity = jpRoomService.UpdateRoom( jpRoom,roomId);

        if (jpRoomEntity == null ) {
            return ResponseEntity.badRequest().body("phòng không tồn tại");
        }

        return ResponseEntity.ok().body(jpRoomEntity);
    }

    @DeleteMapping("/JPLevel/{levelId}/JPRoom/{roomId}/Delete")
    public ResponseEntity<?> DeleteRoom(@PathVariable int roomId)  {

        String message = jpRoomService.DeleteRoom(roomId);

        return ResponseEntity.ok().body(message);
    }


    @GetMapping("/JPLevel/{levelId}/JPRoom/{roomId}/Question")
    public ResponseEntity<List<JPQuestionEntity>> showQuestion(@PathVariable int roomId) {
        List<JPQuestionEntity> listJPQuestion = jpQuestionService.GetAllJPQuestionByRoomId(roomId);

        return ResponseEntity.ok().body(listJPQuestion);
    }

    @GetMapping("/JPLevel/{levelId}/JPRoom/{roomId}/Question/{questionId}")
    public ResponseEntity<List<JPAnswerEntity>> showAnswer(@PathVariable int questionId) {

        List<JPAnswerEntity> listJPAnswer = jpAnswerService.GetAllJPAnswerByQuestionId(questionId);


        return ResponseEntity.ok().body(listJPAnswer);
    }


    @Autowired
    private JPAnswerRepository jpAnswerRepository;


    @PostMapping("/JPLevel/{levelId}/JPRoom/{roomId}/Question/InsertQuestion")
    public ResponseEntity<JPQuestionEntity> insertQuiz(@RequestBody JPQuestionEntity question,
                                                       @PathVariable int roomId) {

        JPQuestionEntity jpQuestionEntity = jpQuestionService.insertQuestion(question,roomId);

            return ResponseEntity.ok().body(jpQuestionEntity) ;

    }


    @DeleteMapping("/JPLevel/{levelId}/JPRoom/{roomId}/Question/{questId}/DeleteQuestion")
    public ResponseEntity<?> deleteQuiz(@PathVariable int questId) {

        jpAnswerService.DeleteAllAnswerByQuestId(questId);
        String messgae = jpQuestionService.deleteQuestById(questId);

        return ResponseEntity.ok().body(messgae) ;

    }

    @PostMapping("/JPLevel/{levelId}/JPRoom/{roomId}/Question/{questionId}/InsertAnswer")
    public ResponseEntity<List<JPAnswerEntity>> insertQuiz(@RequestBody List<JPAnswerEntity> answers,
                                                       @PathVariable int questionId) {

        List<JPAnswerEntity> jpAnswerEntity = jpAnswerService.insertAnswers(answers,questionId);

        return ResponseEntity.ok().body(jpAnswerEntity) ;

    }


//    @PostMapping("/{levelId}/JPRoom/{roomId}/Quiz-japan/InsertQuestion")
//    public String insertQuiz(@RequestParam String content,
//                             @RequestParam String suggest,
//                             @RequestParam String answerContents1,
//                             @RequestParam String answerContents2,
//                             @RequestParam String answerContents3,
//                             @RequestParam String answerContents4,
//                             @RequestParam(required = false) boolean correctAnswer1,
//                             @RequestParam(required = false) boolean correctAnswer2,
//                             @RequestParam(required = false) boolean correctAnswer3,
//                             @RequestParam(required = false) boolean correctAnswer4,
//                             @PathVariable int levelId,
//                             @PathVariable int roomId,
//                             RedirectAttributes redirectAttributes) {
//
//        JPQuestionEntity jpQuestionEntity = jpQuestionService.insertQuestion(content,suggest,roomId);
//
//
//
//        if (jpQuestionEntity != null) {
//            // Thêm câu trả lời chỉ khi câu hỏi đã được thêm thành công
//            List<JPAnswerEntity> answers = new ArrayList<>();
//
//            JPAnswerEntity answer1 = new JPAnswerEntity();
//            answer1.setContent(answerContents1);
//            answer1.setCorrect(correctAnswer1);
//            answer1.setJpQuestion(jpQuestionEntity);
//            answers.add(answer1);
//
//            JPAnswerEntity answer2 = new JPAnswerEntity();
//            answer2.setContent(answerContents2);
//            answer2.setCorrect(correctAnswer2);
//            answer2.setJpQuestion(jpQuestionEntity);
//            answers.add(answer2);
//
//            JPAnswerEntity answer3 = new JPAnswerEntity();
//            answer3.setContent(answerContents3);
//            answer3.setCorrect(correctAnswer3);
//            answer3.setJpQuestion(jpQuestionEntity);
//            answers.add(answer3);
//
//            JPAnswerEntity answer4 = new JPAnswerEntity();
//            answer4.setContent(answerContents4);
//            answer4.setCorrect(correctAnswer4);
//            answer4.setJpQuestion(jpQuestionEntity);
//            answers.add(answer4);
//
//            jpAnswerRepository.saveAll(answers);
//
//            System.out.println("Thêm Quiz thành công");
//
//            return "redirect:/JPLevel-admin/"+levelId+"/JPRoom/"+roomId+"/Quiz-japan";
//        } else {
//            System.out.println("Thêm Quiz thất bại");
//
//            return "redirect:/JPLevel-admin/"+levelId+"/JPRoom/"+roomId+"/Quiz-japan";
//        }
//    }

    // xóa quest
//    @GetMapping("/{levelId}/JPRoom/{roomId}/Quiz-japan/Delete/{questId}")
//    public String deleteQuest(@PathVariable int questId,
//                              @PathVariable int levelId,
//                              @PathVariable int roomId) {
//
//        if (!jpAnswerService.DeleteAllAnswerByQuestId(questId)) {
//            System.out.println("Xoa Answer that bai");
//            return "redirect:/JPLevel-admin/"+levelId+"/JPRoom/"+roomId+"/Quiz-japan";
//        }
//
//        if(!jpQuestionService.deleteQuestById(questId)) {
//            System.out.println("Xoa quest that bai");
//            return "redirect:/JPLevel-admin/"+levelId+"/JPRoom/"+roomId+"/Quiz-japan";
//        }
//
//        jpAnswerService.DeleteAllAnswerByQuestId(questId);
//        jpQuestionService.deleteQuestById(questId);
//        System.out.println("Xoa quest thanh cong");
//        return "redirect:/JPLevel-admin/"+levelId+"/JPRoom/"+roomId+"/Quiz-japan";




//
//        if(jpQuestionService.deleteQuestById(questId)){
//            jpAnswerService.DeleteAllAnswerByQuestId(questId);
//
//            System.out.println("Xoa quest thanh cong");
//            return "redirect:/JPLevel-admin/"+levelId+"/JPRoom/"+roomId+"/Quiz-japan";
//        }
//        else{
//            System.out.println("Xoa quest that bai");
//            return "redirect:/JPLevel-admin/"+levelId+"/JPRoom/"+roomId+"/Quiz-japan";
//        }
//    }

}
