package pe.edu.cibertec.T2_SW_HuamanchumoManuel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.T2_SW_HuamanchumoManuel.model.dto.ArchivoDto;
import pe.edu.cibertec.T2_SW_HuamanchumoManuel.service.FileService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/files")
public class FileController {
    private FileService fileService;

    @PostMapping("")
    public ResponseEntity<ArchivoDto> subirArchivos(@RequestParam("files") List<MultipartFile> multipartFileList) throws Exception {
        for (MultipartFile file : multipartFileList) {
            if (!file.getContentType().equals("application/pdf") &&
                    !file.getContentType().equals("image/png") &&
                    !file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                return new ResponseEntity<>(ArchivoDto.builder().mensaje("Invalid file type").build(), HttpStatus.BAD_REQUEST);
            }
        }
        fileService.guardarArchivos(multipartFileList);
        return new ResponseEntity<>(ArchivoDto.builder().mensaje("Archivos subidos correctamente").build(), HttpStatus.OK);
    }

    @PostMapping("/single")
    public ResponseEntity<ArchivoDto> subirArchivo(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.getSize() > 25 * 1024 * 1024) {
            return new ResponseEntity<>(ArchivoDto.builder().mensaje("El archivo es demasiado grande").build(), HttpStatus.BAD_REQUEST);
        }
        fileService.guardarArchivo(file);
        return new ResponseEntity<>(ArchivoDto.builder().mensaje("Archivo subido correctamente").build(), HttpStatus.OK);
    }
}
