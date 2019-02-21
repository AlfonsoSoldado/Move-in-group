INSERT INTO Valoracion (id, rango, puntos, puntos_negativos, medalla) VALUES
('00', 35, 39, 4, 'EXTRAORDINARIO'),
('01', 0, 2, 14, 'MALÍSIMO'),
('02', 5, 10, 5, 'BUENO');

INSERT INTO Usuario (id, nombre, apellidos, email, telefono, lg_valoracion) VALUES 
('00','Alfonso','Soldado Caro','alfonso@mail.com','666666666', '00'),
('01','Federico','García Lorca','fedelorca@mail.com','999999999', '01'),
('02','Juan','Hernández Jiménez','juher@mail.com','777777777', '02');


INSERT INTO Actividad (id, nombre, momento, ciudad, pais, direccion, tipo_actividad, lg_usuario) VALUES 
('00','Running',null,'Sevilla','España','C/ Ada','Outdoor','00'),
('01','Basket',null,'Madrid','España','C/ Gran Vía','Outdoor','01'),
('02','Football',null,'Barcelona','España','C/ Ramblas','Indoor','02');

INSERT INTO Rol (id, tipo_rol) VALUES
('00', 'USUARIO');

INSERT INTO User_Account (id, username, password, lg_usuario, lg_rol) VALUES
('00', 'usuario1', 'usuario1', 00, 00);
