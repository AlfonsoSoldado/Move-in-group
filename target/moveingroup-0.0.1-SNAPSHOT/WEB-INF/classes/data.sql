INSERT INTO Admin (id) VALUES 
('1000');

INSERT INTO Valoracion (id, rango, puntos, puntos_negativos, medalla) VALUES
('800', 30, 40, 10, 'extraordinario'),
('801', -12, 2, 14, 'malo'),
('802', 5, 10, 5, 'novato'),
('803', 5, 10, 5, 'novato');

INSERT INTO Usuario (id, nombre, apellidos, email, telefono, lg_valoracion, descripcion) VALUES 
('801','Alfonso','Soldado Caro','alfonso@mail.com','666666666', '800', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.'),
('802','Federico','García Lorca','fedelorca@mail.com','999999999', '801', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.'),
('803','Juan','Hernández Jiménez','juher@mail.com','777777777', '802', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.'),
('804','Pepu','Hernández','pepu@mail.com','888888888', '803', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.');

INSERT INTO Empresa (id, nombre, ciudad, pais, email, telefono, web, descripcion) VALUES 
('801','New Balance','Madrid','España','info@newbalance.com', '666666666', 'http://www.newbalance.com', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.');


INSERT INTO Actividad (id, nombre, momento, ciudad, pais, direccion, tipo_actividad, rango, lg_usuario, lg_empresa, cancelada, precio) VALUES 
('800','Carrera nocturna',TO_DATE('10/09/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Sevilla','España','C/ Ada','Outdoor','5','801', null, false, null),
('801','Paseo por la montaña',TO_DATE('30/10/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Madrid','España','C/ Gran Vía','Outdoor','1','802', null, false, null),
('802','Salida en bici de montaña',TO_DATE('01/02/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Fuentes de Andalucía','España','C/ Ramblas','Indoor','9',null, '801', false, 10),
('803','Entrenamiento triatlon',TO_DATE('01/11/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Málaga','España','C/ Ada','Outdoor','5','801', null, false, null),
('804','Pachanga de baloncesto',TO_DATE('04/11/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Almuñecar','España','C/ Gran Vía','Outdoor','1','802', null, false, null),
('805','Partido de tenis',TO_DATE('27/10/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Barcelona','España','C/ Ramblas','Indoor','9',null, '801', false, 10),
('806','Partido de padel',TO_DATE('03/01/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Bilbao','España','C/ Ada','Outdoor','5','801', null, false, null),
('807','Jugar a la petanca',TO_DATE('06/01/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'A Coruña','España','C/ Gran Vía','Outdoor','1','802', null, false, null),
('808','Billar en el bar',TO_DATE('20/11/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Pontevedra','España','C/ Ramblas','Indoor','9',null, '801', false, 10),
('809','Competición de natación',TO_DATE('02/11/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Sevilla','España','C/ Ada','Outdoor','5','801', null, false, null),
('810','Partido de futbito',TO_DATE('03/11/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Cádiz','España','C/ Gran Vía','Outdoor','1','802', null, false, null),
('811','Quedada para jugar a frontón',TO_DATE('03/01/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Huelva','España','C/ Ramblas','Indoor','9',null, '801', false, 10),
('812','Salida padel surf',TO_DATE('07/01/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Granada','España','C/ Ada','Outdoor','5','801', null, false, null),
('813','Partido de badminton',TO_DATE('10/05/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Sevilla','España','C/ Gran Vía','Outdoor','1','802', null, false, null),
('814','Partido balonmano',TO_DATE('01/12/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Barcelona','España','C/ Ramblas','Indoor','9',null, '801', false, 10);

INSERT INTO Rol (id, tipo_rol) VALUES
('1', 'USUARIO'),
('2', 'EMPRESA'),
('3', 'ADMIN');

INSERT INTO User_Account (id, username, password, lg_usuario, lg_empresa, lg_rol, lg_admin) VALUES
('801', 'usuario1', 'usuario1', 801, null, 1, null),
('802', 'empresa1', 'empresa1', null, 801, 2, null),
('803', 'admin1', 'admin1', null, null, 3, '1000'),
('804', 'usuario2', 'usuario2', 802, null, 1, null);

INSERT INTO Usuario_Apuntado (id,lg_usuario,lg_actividad) values 
('900','801','802'),
('901','802','800'),
('902','803','801'),
('903','804','800');

INSERT INTO Amigos (id,lg_amigo_a,lg_amigo_b,ya_es_amigo) values
('900','801','802', true),
('901','803','801', false);