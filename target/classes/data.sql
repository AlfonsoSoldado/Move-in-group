INSERT INTO Admin (id) VALUES 
('1000');

INSERT INTO Valoracion (id, rango, puntos, puntos_negativos, medalla) VALUES
('800', 30, 40, 10, 'extraordinario'),
('801', -12, 2, 14, 'malo'),
('802', 5, 10, 5, 'novato'),
('803', 5, 10, 5, 'novato');

INSERT INTO Usuario (id, nombre, apellidos, email, telefono, lg_valoracion) VALUES 
('801','Alfonso','Soldado Caro','alfonso@mail.com','666666666', '800'),
('802','Federico','García Lorca','fedelorca@mail.com','999999999', '801'),
('803','Juan','Hernández Jiménez','juher@mail.com','777777777', '802'),
('804','Pepu','Hernández','pepu@mail.com','888888888', '803');

INSERT INTO Empresa (id, nombre, ciudad, pais, email, telefono, web) VALUES 
('801','New Balance','Madrid','España','info@newbalance.com', '666666666', 'http://www.newbalance.com');


INSERT INTO Actividad (id, nombre, momento, ciudad, pais, direccion, tipo_actividad, rango, lg_usuario, lg_empresa, cancelada, precio) VALUES 
('800','Running',TO_DATE('03/07/2021 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Sevilla','España','C/ Ada','Outdoor','5','801', null, false, null),
('801','Basket',TO_DATE('03/07/2021 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Madrid','España','C/ Gran Vía','Outdoor','1','802', null, false, null),
('802','Football',TO_DATE('03/01/2021 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Barcelona','España','C/ Ramblas','Indoor','9',null, '801', false, 10);

INSERT INTO Rol (id, tipo_rol) VALUES
('1', 'USUARIO'),
('2', 'EMPRESA'),
('3', 'ADMIN');

INSERT INTO User_Account (id, username, password, lg_usuario, lg_empresa, lg_rol, lg_admin) VALUES
('801', 'usuario1', 'usuario1', 801, null, 1, null),
('802', 'empresa1', 'empresa1', null, 801, 2, null),
('803', 'admin1', 'admin1', null, null, 3, '1000');

INSERT INTO Usuario_Apuntado (id,lg_usuario,lg_actividad) values 
('900','801','802'),
('901','802','800'),
('902','803','801'),
('903','804','800');

INSERT INTO Amigos (id,lg_amigo_a,lg_amigo_b,ya_es_amigo) values
('900','801','802', true),
('901','803','801', false);