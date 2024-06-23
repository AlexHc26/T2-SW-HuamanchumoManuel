package pe.edu.cibertec.T2_SW_HuamanchumoManuel.model.bd;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMedico")
    private Integer idMedico;

    @Column(name = "NomMedico")
    private String nomMedico;

    @Column(name = "ApeMedico")
    private String apeMedico;

    @Column(name = "FechNacMedico")
    private Date fechNacMedico;
}
