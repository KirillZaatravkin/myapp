package ru.myapp.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.myapp.system.dao.CsetDAO;
import ru.myapp.system.dao.VoprosDAO;
import ru.myapp.system.model.Cset;
import ru.myapp.system.model.Vopros;
import ru.myapp.system.service.FileStorageService;
import ru.myapp.system.utils.ResponseMessage;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainRESTController {

    @Autowired
    private VoprosDAO voprosDAO;
    @Autowired
    private CsetDAO csetDAO;

    private final FileStorageService storageService = new FileStorageService();

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/vopros")
    public List<Vopros> getAllVopros() {
        return voprosDAO.getAllVopros();
    }

    @PostMapping("/voprosadd")
    public void productAdd(@RequestBody Vopros Vopros) {
        voprosDAO.addVopros(Vopros);
    }

    @RequestMapping(value = "/voprosabout", method = {RequestMethod.GET})
    public Vopros getProduct(@RequestParam(value = "id", required = false) String id, Model model) {
        int idInt = Integer.valueOf(id);
        return voprosDAO.getVopros(idInt);
    }

    @RequestMapping(value = "/voprosaboutimg", method = {RequestMethod.GET})
    public Vopros getProductWithImage(@RequestParam(value = "id", required = false) String id, Model model) throws SQLException {
        int idInt = Integer.valueOf(id);
        return voprosDAO.getProductWithImage(idInt);
    }

    @PostMapping("/voprosclose")
    public void productClose(@RequestBody Vopros Vopros) {
        voprosDAO.closeVopros(Vopros);
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String idVopros) {
        String message = "";
        try {
            storageService.save(file, idVopros);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/cset")
    public List<Cset> getAllCset() {
        return csetDAO.getAllCset();
    }

    @PostMapping("/csetUpdate")
    public void csetUpdate(@RequestBody Cset cset) {
        csetDAO.updateCset(cset);
    }
}