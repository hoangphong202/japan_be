//package com.japan.Japan_BE.Controller;
//
//
//import com.japan.Japan_BE.Entity.JPAnswerEntity;
//import com.japan.Japan_BE.Entity.JPLevelEntity;
//import com.japan.Japan_BE.Entity.JPQuestionEntity;
//import com.japan.Japan_BE.Entity.JPRoomEntity;
//import com.japan.Japan_BE.Service.JPAnswerService;
//import com.japan.Japan_BE.Service.JPLevelService;
//import com.japan.Japan_BE.Service.JPQuestionService;
//import com.japan.Japan_BE.Service.JPRoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/JPLevel")
//public class QuizController {
//
//    @Autowired
//    private JPLevelService jpLevelService;
//
//    @Autowired
//    private JPRoomService jpRoomService;
//
//    @GetMapping("")
//    public String Level(Model model){
//        List<JPLevelEntity> jpLevelEntities = jpLevelService.GetAllJPLevel();
//
//        model.addAttribute("jpLevelEntities",jpLevelEntities);
//        return "JPQuiz/jp_level";
//    }
//
//    @GetMapping("/{levelId}/JPRoom")
//    public String Room(Model model, @PathVariable int levelId){
//        List<JPRoomEntity> jpRoomEntities = jpRoomService.GetAllJPRoomByLevelId(levelId);
//
//        model.addAttribute("jpRoomEntities",jpRoomEntities);
//        model.addAttribute("levelId",levelId);
//        return "JPQuiz/jp_room";
//    }
//
//    @Autowired
//    private JPQuestionService jpQuestionService;
//
//    @Autowired
//    private JPAnswerService jpAnswerService;
//
//    @GetMapping("/{levelId}/JPRoom/{roomId}/Quiz-japan")
//    public String showQuestion(Model model, @PathVariable int roomId, @PathVariable int levelId) {
//        List<JPQuestionEntity> listJPQuestion = jpQuestionService.GetAllJPQuestionByRoomId(roomId);
//        List<JPAnswerEntity> listJPAnswer = jpAnswerService.GetAllJPAnswer();
//
//        model.addAttribute("listJPQuestion", listJPQuestion);
//        model.addAttribute("listJPAnswer", listJPAnswer);
//        model.addAttribute("roomId", roomId);
//        model.addAttribute("levelId", levelId);
//
//        return "JPQuiz/jp_quiz";
//    }
//
//
//
//}
