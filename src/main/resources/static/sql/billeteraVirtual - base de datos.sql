Create database billeteraVirtual;

use billeteraVirtual;

CREATE TABLE billeteraVirtual.Usuarios (
  USU_ID INT NOT NULL AUTO_INCREMENT,
  USU_NAME VARCHAR(45) NOT NULL,
  USU_EMAIL VARCHAR(45) NOT NULL,
  USU_PASSWORD VARCHAR(45) NOT NULL,
  USU_SALDO LONG NOT NULL,
  USU_ROLE VARCHAR(45) NOT NULL,
  PRIMARY KEY (USU_ID)
  );

CREATE TABLE billeteraVirtual.Transacciones (
	TRAN_ID INT NOT NULL auto_increment,
    TRAN_TYPE VARCHAR(25) NOT NULL,
    TRAN_AMOUNT LONG NOT NULL,
    TRAN_DATE TIMESTAMP NOT NULL,
    TRAN_USU_ID_SENDER INT,
    TRAN_USU_ID_RECEIVER INT,
    PRIMARY KEY (TRAN_ID),
	FOREIGN KEY (TRAN_USU_ID_SENDER) REFERENCES billeteraVirtual.Usuarios (USU_ID),
	FOREIGN KEY (TRAN_USU_ID_RECEIVER) REFERENCES billeteraVirtual.Usuarios (USU_ID)
	);

  INSERT INTO billeteraVirtual.Usuarios (USU_NAME, USU_EMAIL, USU_PASSWORD, USU_SALDO, USU_ROLE) VALUE 
  ('Sebastian',  'sebastian@correo.cl', '12345', 10000, 'admin'),
  ('Roberto', 'roberto@gmail.com', '12345', 5000, 'usuario'),
  ('Manuel', 'manu123@gmail.com', 'asddfg', 5000, 'usuario'),
  ('Leonardo', 'leodelosfanchis@hotmail.com', 'j3g4u4ij', 5000, 'usuario'),
  ('Cintia', 'cintiaperalta@gmail.com', '888d8d78', 5000, 'usuario'),
  ('Manuela', 'manuelita@gmail.com', 'fgfgfgf', 5000, 'usuario'),
  ('Roberto', 'robin@hotmail.com',  'contraseña', 5000, 'usuario'),
  ('Josefa', 'josejose888@gmail.com', 'password', 5000, 'usuario'),
  ('Iris', 'siriris@gmail.com', 'holamundo', 5000, 'usuario');

INSERT INTO billeteraVirtual.Transacciones (TRAN_TYPE, TRAN_AMOUNT, TRAN_DATE, TRAN_USU_ID_SENDER, TRAN_USU_ID_RECEIVER) VALUE 
('retiro', 5000, '2023-02-24 12:30:30', 1, null),
('retiro', 4000, '2023-03-24 13:30:30', 1, null),
('retiro', 3000, '2023-04-24 14:30:30', 2, null),
('retiro', 5000, '2023-05-24 15:30:30', 2, null),
('retiro', 4000, '2023-06-24 16:30:30', 3, null),
('deposito', 3000, '2023-07-24 17:30:30', null, 4),
('deposito', 5000, '2023-08-24 18:30:30', null, 5),
('deposito', 4000, '2023-09-24 12:30:30', null, 6),
('deposito', 3000, '2023-10-24 13:30:30', null, 1),
('transferencia', 5000, '2023-11-24 14:30:30', 7, 2),
('transferencia', 4000, '2023-12-24 15:30:30', 8, 3),
('transferencia', 3000, '2024-01-24 16:30:30', 9, 4),
('transferencia', 5000, '2024-02-24 17:30:30', 9, 5);

-- Mostrar todo
use billeteraVirtual;
SELECT * FROM Usuarios;
SELECT * FROM Transacciones;