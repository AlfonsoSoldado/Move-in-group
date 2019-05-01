INSERT INTO Valoracion (id, rango, puntos, puntos_negativos, medalla) VALUES
('1', 30, 40, 10, 'extraordinario'),
('2', -12, 2, 14, 'malo'),
('3', 5, 10, 5, 'novato'),
('4', 5, 10, 5, 'novato');

INSERT INTO Usuario (id, nombre, apellidos, email, telefono, lg_valoracion) VALUES 
('1','Alfonso','Soldado Caro','alfonso@mail.com','666666666', '1'),
('2','Federico','García Lorca','fedelorca@mail.com','999999999', '2'),
('3','Juan','Hernández Jiménez','juher@mail.com','777777777', '3'),
('4','Pepu','Hernández','pepuestudiantes@mail.com','888888888', '4');

INSERT INTO Empresa (id, nombre, ciudad, pais, email, telefono, web) VALUES 
('1','New Balance','Madrid','España','info@newbalance.com', '666666666', 'http://www.newbalance.com');


INSERT INTO Actividad (id, nombre, momento, ciudad, pais, direccion, tipo_actividad, rango, lg_usuario, lg_empresa, cancelada) VALUES 
('800','Running',TO_DATE('03/07/2018 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Sevilla','España','C/ Ada','Outdoor','5','1', null, false),
('801','Basket',TO_DATE('03/07/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Madrid','España','C/ Gran Vía','Outdoor','1','2', null, false),
('802','Football',TO_DATE('03/01/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Barcelona','España','C/ Ramblas','Indoor','9',null, '1', false);

INSERT INTO Rol (id, tipo_rol) VALUES
('1', 'USUARIO'),
('2', 'EMPRESA');

INSERT INTO User_Account (id, username, password, lg_usuario, lg_empresa, lg_rol) VALUES
('1', 'usuario1', 'usuario1', 1, null, 1),
('2', 'empresa1', 'empresa1', null, 1, 2);

INSERT INTO Usuario_Apuntado (id,lg_usuario,lg_actividad) values 
('900','1','802'),
('901','2','800'),
('902','3','801'),
('903','4','800');

INSERT INTO Amigos (id,lg_amigo_a,lg_amigo_b,ya_es_amigo) values
('900','1','2', true),
('901','3','1', false);