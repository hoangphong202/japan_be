//package com.japan.Japan_BE.Controller;
//
//
//import com.japan.Japan_BE.Entity.JPAnswerEntity;
//import com.japan.Japan_BE.Entity.JPLevelEntity;
//import com.japan.Japan_BE.Entity.JPQuestionEntity;
//import com.japan.Japan_BE.Entity.JPRoomEntity;
//import com.japan.Japan_BE.Repository.JPAnswerRepository;
//import com.japan.Japan_BE.Service.JPAnswerService;
//import com.japan.Japan_BE.Service.JPLevelService;
//import com.japan.Japan_BE.Service.JPQuestionService;
//import com.japan.Japan_BE.Service.JPRoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/JPLevel-admin")
//public class QuizAdminController {
//    @Autowired
//    private JPQuestionService jpQuestionService;
//
//    @Autowired
//    private JPAnswerService jpAnswerService;
//
//    @Autowired
//    private JPLevelService jpLevelService;
//
//    @Autowired
//    private JPRoomService jpRoomService;
//
//    // ...
//
//    @GetMapping("")
//    public String Level(Model model){
//        List<JPLevelEntity> jpLevelEntities = jpLevelService.GetAllJPLevel();
//
//        model.addAttribute("jpLevelEntities",jpLevelEntities);
//        return "JPQuiz/jp_level_admin";
//    }
//
//    @GetMapping("/{levelId}/JPRoom")
//    public String Room(Model model, @PathVariable int levelId){
//        List<JPRoomEntity> jpRoomEntities = jpRoomService.GetAllJPRoomByLevelId(levelId);
//
//        model.addAttribute("jpRoomEntities",jpRoomEntities);
//        model.addAttribute("levelId",levelId);
//        return "JPQuiz/jp_room_admin";
//    }
//
//    @GetMapping("/{levelId}/JPRoom/InsertRoom")
//    public String InertRoom(Model model, @PathVariable int levelId){
//
//        model.addAttribute("levelId",levelId);
//        return "JPQuiz/jp_room_admin_insert";
//    }
//
//    @PostMapping("/{levelId}/JPRoom/InsertRoom")
//    public String insertRoom(@RequestParam("file") MultipartFile file,
//                             @ModelAttribute JPRoomEntity jpRoomEntity,
//                             @RequestParam String name,
//                             @PathVariable int levelId) throws IOException {
//
//
//            if(jpRoomService.InsertRoom(file, jpRoomEntity,name, levelId)){
//                System.out.println("Them Room thanh cong");
//                return "redirect:/JPLevel-admin/"+ levelId+"/JPRoom";
//            }
//            else {
//                System.out.println("Them Room that bai");
//                return "redirect:/JPLevel-admin/JPRoom/"+ levelId+"/JPRoom";
//            }
//    }
//
//
//    @GetMapping("/{levelId}/JPRoom/{roomId}/Quiz-japan")
//    public String showQuestion(Model model,@PathVariable int levelId,@PathVariable int roomId) {
//        List<JPQuestionEntity> listJPQuestion = jpQuestionService.GetAllJPQuestionByRoomId(roomId);
//        List<JPAnswerEntity> listJPAnswer = jpAnswerService.GetAllJPAnswer();
//
//        model.addAttribute("listJPQuestion", listJPQuestion);
//        model.addAttribute("listJPAnswer", listJPAnswer);
//        model.addAttribute("levelId", levelId);
//        model.addAttribute("roomId", roomId);
//
//        return "JPQuiz/jp_quiz_admin";
//    }
//
//    @GetMapping("/{levelId}/JPRoom/{roomId}/Quiz-japan/InsertQuiz")
//    public String insertQuiz(Model model,@PathVariable int levelId,@PathVariable int roomId) {
//
//        model.addAttribute("levelId", levelId);
//        model.addAttribute("roomId", roomId);
//        return "JPQuiz/jp_quiz_admin_insert";
//    }
//
//    @Autowired
//    private JPAnswerRepository jpAnswerRepository;
//
//
//    @PostMapping("/{levelId}/JPRoom/{roomId}/Quiz-japan/InsertQuiz")
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
//
//    // xóa quest
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
//
//
//
////
////        if(jpQuestionService.deleteQuestById(questId)){
////            jpAnswerService.DeleteAllAnswerByQuestId(questId);
////
////            System.out.println("Xoa quest thanh cong");
////            return "redirect:/JPLevel-admin/"+levelId+"/JPRoom/"+roomId+"/Quiz-japan";
////        }
////        else{
////            System.out.println("Xoa quest that bai");
////            return "redirect:/JPLevel-admin/"+levelId+"/JPRoom/"+roomId+"/Quiz-japan";
////        }
//    }
//
//}
