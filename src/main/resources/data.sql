INSERT INTO Valoracion (id, rango, puntos, puntos_negativos, medalla) VALUES
('0', 35, 39, 4, 'EXTRAORDINARIO'),
('1', 0, 2, 14, 'MALÍSIMO'),
('2', 5, 10, 5, 'BUENO');

INSERT INTO Usuario (id, nombre, apellidos, email, telefono, lg_valoracion) VALUES 
('0','Alfonso','Soldado Caro','alfonso@mail.com','666666666', '0'),
('1','Federico','García Lorca','fedelorca@mail.com','999999999', '1'),
('2','Juan','Hernández Jiménez','juher@mail.com','777777777', '2');

INSERT INTO Empresa (id, nombre, ciudad, pais, email, telefono, web) VALUES 
('0','New Balance','Madrid','España','info@newbalance.com', '666666666', 'http://www.newbalance.com');


INSERT INTO Actividad (id, nombre, momento, ciudad, pais, direccion, tipo_actividad, lg_usuario, lg_empresa, cancelada) VALUES 
('800','Running',null,'Sevilla','España','C/ Ada','Outdoor','0', null, false),
('801','Basket',null,'Madrid','España','C/ Gran Vía','Outdoor','1', null, false),
('802','Football',null,'Barcelona','España','C/ Ramblas','Indoor', null, '0', false);

INSERT INTO Rol (id, tipo_rol) VALUES
('0', 'USUARIO'),
('1', 'EMPRESA');

INSERT INTO User_Account (id, username, password, lg_usuario, lg_empresa, lg_rol) VALUES
('0', 'usuario1', 'usuario1', 0, null, 0),
('1', 'empresa1', 'empresa1', null, 0, 1);
