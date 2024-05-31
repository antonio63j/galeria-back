INSERT INTO usuarios (username, password, finalizada_activacion, enabled, nombre, apellidos, email) VALUES ('antonio63j@hotmail.com','12345', true, true, 'Antonio','fernandez','antonio63j@hotmail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 2);

INSERT INTO adminindex ( cabecera, imagen, body, route) VALUES ('Datos de la empresa', 'empresa.png',        'Aquí se podrán asignar los datos de la empresa, nombre, teléfono, ...', 'empresa' )
INSERT INTO adminindex ( cabecera, imagen, body, route) VALUES ('Imagenes de la página principal', 'home.png', 'Establecer las imagenes de la página de entrada (home)', 'admslider' )
INSERT INTO adminindex ( cabecera, imagen, body, route) VALUES ('Clasificación de las obras', 'tipos.png', 'Aquí se definen los tipo o grupos en los que se clasificarán las obras', 'admtipoobra')
INSERT INTO adminindex ( cabecera, imagen, body, route) VALUES ('Obras', 'sugerencias.png', 'Desde aquí se gestionan las obras de la galeria, asignado precio y clasificación, ...', 'admsugerencia')
INSERT INTO adminindex ( cabecera, imagen, body, route) VALUES ('lotes de obras', 'lote.png', 'Se definen los grupos con obras de la galeria. Se asigna precio, descripción, ....', 'admlote')
INSERT INTO adminindex ( cabecera, imagen, body, route) VALUES ('Control de pedidos', 'orders.png', 'Gestión y control sobre los pedidos', 'admpedido')

INSERT INTO empresa(descripcion_breve, direccion, email, nombre, provincia, telefono, urlweb, hora_apertura, hora_cierre, horas_min_preparacion_pedido, dias_max_entrega_pedido) VALUES ('descripcion breve', 'c/Cortubi n.22', 'info@fernandezlucena.es', 'Arte Fluido', 'Madrid', '627336511', 'http://localhost:4200', '09:00', '22:00', 2, 7)

INSERT INTO sliders ( img_file_name, label, descripcion) VALUES ('slider1.jpg', 'slider1', 'descripcionx')
INSERT INTO sliders ( img_file_name, label, descripcion) VALUES ('slider2.jpg', 'slider2', 'descripcionx')
INSERT INTO sliders ( img_file_name, label, descripcion) VALUES ('slider3.jpg', 'slider3', 'descripcion')

INSERT INTO tiposugerencia ( img_file_name, nombre, label, descripcion) VALUES ('slider3.jpg', 'Sopas y verduras', 'Sopas y verduras', 'descripcion')
INSERT INTO tiposugerencia ( img_file_name, nombre, label, descripcion) VALUES ('slider3.jpg', 'Pasta y arroz', 'Pasta y arroz', 'Arroces nacionales')
INSERT INTO tiposugerencia ( img_file_name, nombre, label, descripcion) VALUES ('slider3.jpg', 'Legunbres', 'Legunbres', 'Legunbres nacionales')
INSERT INTO tiposugerencia ( img_file_name, nombre, label, descripcion) VALUES ('slider3.jpg', 'Carnes y aves', 'Carnes y aves', 'Legunbres nacionales')
INSERT INTO tiposugerencia ( img_file_name, nombre, label, descripcion) VALUES ('slider3.jpg', 'Pescados y mariscos', 'Pescados y mariscos', 'Legunbres nacionales')
INSERT INTO tiposugerencia ( img_file_name, nombre, label, descripcion) VALUES ('slider3.jpg', 'Postres', 'Postres', 'Legunbres nacionales')

INSERT INTO sugerencia ( img_file_name, visible, label, tipo, precio, estado, descripcion, alto, ancho, fondo) VALUES ('slider3.jpg', 'si', 'helado choco', 'Postres', 4, 0, 'descripcion', 20, 20, 2)


--INSERT INTO lote (img_file_name, visible, label, precio, descripcion) VALUES ('slider3.jpg', 'si', 'lote 1', 12, 'descripcion'))

--INSERT INTO pedido ( usuario_id, estado_pedido, total) VALUES (1, 'PENDIENTE', 10)


