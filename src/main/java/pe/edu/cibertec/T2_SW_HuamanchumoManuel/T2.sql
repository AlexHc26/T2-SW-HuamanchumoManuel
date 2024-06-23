create database bdcibertec;
use bdcibertec;

 Create table Medico
(
 IdMedico 		INT NOT NULL AUTO_INCREMENT,
 NomMedico		VARCHAR(50) NOT NULL,
 ApeMedico 		VARCHAR(50) NOT NULL,
 FechNacMedico	DATE NOT NULL,
 PRIMARY KEY (IdMedico)
);

INSERT INTO Medico (NomMedico, ApeMedico, FechNacMedico) VALUES 
('John', 'Doe', '1980-01-01'),
('Jane', 'Smith', '1985-05-15'),
('Mike', 'Johnson', '1990-07-20'),
('Emily', 'Davis', '1995-03-10'),
('Laura', 'Wilson', '2000-12-25');
