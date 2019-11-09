INSERT INTO Admin (id) VALUES 
('1000');

INSERT INTO Valoracion (id, rango, puntos, puntos_negativos, medalla) VALUES
('800', 30, 40, 10, 'extraordinario'),
('801', -12, 2, 14, 'malo'),
('802', 5, 10, 5, 'novato'),
('803', 5, 10, 5, 'novato');

INSERT INTO Usuario (id, nombre, apellidos, email, telefono, lg_valoracion, descripcion) VALUES 
('801','Alfonso','García Fernández','alfonso@mail.com','666666666', '800', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.'),
('802','Federico','Jimenez Lorca','fedelorca@mail.com','999999999', '801', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.'),
('803','Juan','Hernández Jiménez','juher@mail.com','777777777', '802', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.'),
('804','Guillermo','Tirado Leiva','guitir@mail.com','888888888', '803', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.');

INSERT INTO Empresa (id, nombre, ciudad, pais, email, telefono, web, descripcion) VALUES 
('801','New Balance','Madrid','España','info@newbalance.com', '666666666', 'http://www.newbalance.com', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.');


INSERT INTO Actividad (id, nombre, momento, ciudad, pais, direccion, tipo_actividad, rango, lg_usuario, lg_empresa, cancelada, precio) VALUES 
('800','Carrera nocturna',TO_DATE('10/09/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Sevilla','España','C/ Ada','Outdoor','5','801', null, false, null),
('801','Paseo por la montaña',TO_DATE('30/10/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Madrid','España','C/ Gran Vía','Outdoor','1','802', null, false, null),
('802','Salida en bici de montaña',TO_DATE('30/10/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Fuentes de Andalucía','España','C/ Ramblas','Indoor','0',null, '801', false, 10),
('803','Entrenamiento triatlon',TO_DATE('16/11/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Málaga','España','C/ Ada','Outdoor','0','803', null, false, null),
('804','Pachanga de baloncesto',TO_DATE('04/11/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Almuñecar','España','C/ Gran Vía','Outdoor','1','804', null, false, null),
('805','Partido de tenis',TO_DATE('27/10/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Barcelona','España','C/ Ramblas','Indoor','0',null, '801', false, 10),
('806','Partido de padel',TO_DATE('03/01/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Bilbao','España','C/ Ada','Outdoor','5','804', null, false, null),
('807','Jugar a la petanca',TO_DATE('06/01/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'A Coruña','España','C/ Gran Vía','Outdoor','2','803', null, false, null),
('808','Billar en el bar',TO_DATE('20/11/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Pontevedra','España','C/ Ramblas','Indoor','0',null, '801', false, 10),
('809','Competición de natación',TO_DATE('13/11/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Sevilla','España','C/ Ada','Outdoor','5','803', null, false, null),
('810','Partido de futbito',TO_DATE('10/11/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Cádiz','España','C/ Gran Vía','Outdoor','0','801', null, false, null),
('811','Quedada para jugar a frontón',TO_DATE('03/01/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Huelva','España','C/ Ramblas','Indoor','0',null, '801', false, 10),
('812','Salida padel surf',TO_DATE('07/01/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Granada','España','C/ Ada','Outdoor','5','802', null, false, null),
('813','Partido de badminton',TO_DATE('10/05/2020 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Sevilla','España','C/ Gran Vía','Outdoor','0','804', null, false, null),
('814','Partido balonmano',TO_DATE('01/12/2019 14:00:00', 'DD/MM/YYYY hh24:mi:ss'),'Barcelona','España','C/ Ramblas','Indoor','0',null, '801', false, 10);

INSERT INTO Rol (id, tipo_rol) VALUES
('1', 'USUARIO'),
('2', 'EMPRESA'),
('3', 'ADMIN');

INSERT INTO User_Account (id, username, password, lg_usuario, lg_empresa, lg_rol, lg_admin) VALUES
('801', 'alfonso_22', 'contraseña', 801, null, 1, null),
('802', 'newbalance', 'contraseña', null, 801, 2, null),
('803', 'admin', 'admin', null, null, 3, '1000'),
('804', 'federico45', 'contraseña', 802, null, 1, null),
('805', '_Juan_', 'contraseña', 803, null, 1, null),
('806', 'guille', 'contraseña', 804, null, 1, null);

INSERT INTO Usuario_Apuntado (id,lg_usuario,lg_actividad) values 
('900','801','800'),
('901','802','801'),
('902','804','802'),
('903','803','803'),
('904','804','804'),
('905','802','805'),
('906','804','806'),
('907','803','807'),
('908','803','808'),
('909','803','809'),
('910','801','810'),
('911','801','811'),
('912','802','812'),
('913','804','813'),
('914','801','814'),

('915','802','800'),
('916','803','801'),
('917','802','802'),
('918','804','803'),
('919','801','804'),
('920','802','805'),
('921','801','806'),
('922','804','807'),
('923','802','808'),
('924','804','809'),
('925','802','810'),
('926','802','811'),
('927','803','812'),
('928','801','813'),
('929','803','814');

INSERT INTO Amigos (id,lg_amigo_a,lg_amigo_b,ya_es_amigo) values
('900','801','802', true),
('901','803','801', false);