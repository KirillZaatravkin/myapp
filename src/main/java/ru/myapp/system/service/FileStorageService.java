package ru.myapp.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.myapp.system.dao.QuestionDao;
import ru.myapp.system.model.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private static final Path ROOT = Paths.get("angularclient\\src\\assets\\image");

    @Autowired
    private QuestionDao questionDAO;

    public void init() throws IOException {
            Files.createDirectory(ROOT);
    }

    public void save(MultipartFile file, String idVopros) throws IOException {
            Question question = questionDAO.getQuestion(Integer.parseInt(idVopros));
            questionDAO.addFile(question.getId(), file);
            Files.copy(file.getInputStream(), this.ROOT.resolve(file.getOriginalFilename()));
    }

    public void deleteAll() {

        FileSystemUtils.deleteRecursively(ROOT.toFile());
    }

}
