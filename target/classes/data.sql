INSERT INTO Valoracion (id, rango, puntos, puntos_negativos, medalla) VALUES
('00', 35, 39, 4, 'EXTRAORDINARIO'),
('01', 0, 2, 14, 'MALÍSIMO'),
('02', 5, 10, 5, 'BUENO');

INSERT INTO Usuario (id, nombre, apellidos, email, telefono, lg_valoracion) VALUES 
('00','Alfonso','Soldado Caro','alfonso@mail.com','666666666', '00'),
('01','Federico','García Lorca','fedelorca@mail.com','999999999', '01'),
('02','Juan','Hernández Jiménez','juher@mail.com','777777777', '02');

INSERT INTO Empresa (id, nombre, ciudad, pais, email, telefono, web) VALUES 
('00','New Balance','Madrid','España','info@newbalance.com', '666666666', 'http://www.newbalance.com');


INSERT INTO Actividad (id, nombre, momento, ciudad, pais, direccion, tipo_actividad, lg_usuario, lg_empresa, cancelada) VALUES 
('00','Running',null,'Sevilla','España','C/ Ada','Outdoor','00', null, false),
('01','Basket',null,'Madrid','España','C/ Gran Vía','Outdoor','01', null, false),
('02','Football',null,'Barcelona','España','C/ Ramblas','Indoor', null, '00', false);

INSERT INTO Rol (id, tipo_rol) VALUES
('00', 'USUARIO'),
('01', 'EMPRESA');

INSERT INTO User_Account (id, username, password, lg_usuario, lg_empresa, lg_rol) VALUES
('00', 'usuario1', 'usuario1', 00, null, 00),
('01', 'empresa1', 'empresa1', null, 00, 01);
