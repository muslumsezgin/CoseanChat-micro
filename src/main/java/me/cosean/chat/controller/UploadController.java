package me.cosean.chat.controller;

import me.cosean.chat.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/v1/")
public class UploadController {

    @Autowired
    StorageService storageService;

    List<String> files = new ArrayList<String>();

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        String uniqueID = UUID.randomUUID().toString();
        String fileName = uniqueID + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        try {
            storageService.store(file, fileName);
            files.add(fileName);

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            message = MvcUriComponentsBuilder.fromMethodName
                    (UploadController.class, "getFile", fileName).build().toString();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
