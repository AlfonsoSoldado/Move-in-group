INSERT INTO Valoracion (id, rango, puntos, puntos_negativos, medalla) VALUES
('1', 35, 39, 4, 'EXTRAORDINARIO'),
('2', 0, 2, 14, 'MALÍSIMO'),
('3', 5, 10, 5, 'BUENO');

INSERT INTO Usuario (id, nombre, apellidos, email, telefono, lg_valoracion) VALUES 
('1','Alfonso','Soldado Caro','alfonso@mail.com','666666666', '1'),
('2','Federico','García Lorca','fedelorca@mail.com','999999999', '2'),
('3','Juan','Hernández Jiménez','juher@mail.com','777777777', '3');

INSERT INTO Empresa (id, nombre, ciudad, pais, email, telefono, web) VALUES 
('1','New Balance','Madrid','España','info@newbalance.com', '666666666', 'http://www.newbalance.com');


INSERT INTO Actividad (id, nombre, momento, ciudad, pais, direccion, tipo_actividad, lg_usuario, lg_empresa, cancelada) VALUES 
('800','Running',null,'Sevilla','España','C/ Ada','Outdoor','1', null, false),
('801','Basket',null,'Madrid','España','C/ Gran Vía','Outdoor','2', null, false),
('802','Football',null,'Barcelona','España','C/ Ramblas','Indoor', null, '1', false);

INSERT INTO Rol (id, tipo_rol) VALUES
('1', 'USUARIO'),
('2', 'EMPRESA');

INSERT INTO User_Account (id, username, password, lg_usuario, lg_empresa, lg_rol) VALUES
('1', 'usuario1', 'usuario1', 1, null, 1),
('2', 'empresa1', 'empresa1', null, 1, 2);

INSERT INTO Usuario_Apuntado (id,lg_usuario,lg_actividad) values 
('1','1','802'),
('2','2','800'),
('3','3','801');