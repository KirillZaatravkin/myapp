package ru.myapp.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.myapp.system.dto.QuestionDto;
import ru.myapp.system.dto.ScoreDto;
import ru.myapp.system.model.Question;
import ru.myapp.system.model.Score;
import ru.myapp.system.service.FileStorageService;
import ru.myapp.system.service.QuestionService;
import ru.myapp.system.service.ScoreService;
import ru.myapp.system.utils.ResponseMessage;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainRESTController {

    @Autowired
    private FileStorageService storageService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ScoreService scoreService;


    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/question")
    public List<QuestionDto> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @PostMapping("/questionadd")
    public void questionAdd(@RequestBody Question question) {
        questionService.addQuestion(question);
    }

    @RequestMapping(value = "/questionabout", method = {RequestMethod.GET})
    public QuestionDto getQuestion(@RequestParam(value = "id", required = false) Integer id) {
        return questionService.getQuestion(id);
    }

    @RequestMapping(value = "/questionaboutimg", method = {RequestMethod.GET})
    public QuestionDto getQuestionWithImage(@RequestParam(value = "id", required = false) Integer id, Model model) throws SQLException {
        return questionService.getQuestionWithImage(id);
    }

    @PostMapping("/questionclose")
    public void questionClose(@RequestBody Question Question) {
        questionService.closeQuestion(Question);
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String idQuestion) {
        String message = "";
        try {
            storageService.save(file, idQuestion);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/score")
    public List<ScoreDto> getAllScore() {
        return scoreService.getAllScore();
    }

    @PostMapping("/scoreUpdate")
    public void scoreUpdate(@RequestBody Score score) {
        scoreService.updateScore(score);
    }
}