package pe.edu.cibertec.T2_SW_HuamanchumoManuel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.T2_SW_HuamanchumoManuel.exception.ResourceNotFoundException;
import pe.edu.cibertec.T2_SW_HuamanchumoManuel.model.bd.Medico;
import pe.edu.cibertec.T2_SW_HuamanchumoManuel.service.MedicoService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/medico")
public class MedicoController {
    private MedicoService medicoService;

    @GetMapping("")
    public ResponseEntity<List<Medico>> listarMedicos() {
        List<Medico> medicoList = medicoService.listarMedicos();
        if (medicoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medicoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedicoPorId(@PathVariable Integer id) {
        Medico medico = medicoService.obtenerMedicoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El médico con ID " + id + " no existe"));
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Medico> registrarMedico(@RequestBody Medico medico) {
        return new ResponseEntity<>(medicoService.guardarMedico(medico), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable Integer id, @RequestBody Medico medico) {
        Medico medicoExistente = medicoService.obtenerMedicoPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El médico con ID " + id + " no existe"));
        medicoExistente.setNomMedico(medico.getNomMedico());
        medicoExistente.setApeMedico(medico.getApeMedico());
        medicoExistente.setFechNacMedico(medico.getFechNacMedico());
        return new ResponseEntity<>(medicoService.guardarMedico(medicoExistente), HttpStatus.OK);
    }
}