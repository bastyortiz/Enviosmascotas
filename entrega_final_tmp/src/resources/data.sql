INSERT INTO seguimiento_envios (numero_seguimiento, estado, ubicacion_actual, destinatario, detalle_producto)
SELECT 'TRK-1001', 'Pendiente', 'Centro logistica Santiago', 'Bastian Soto', 'Alimento premium para perro 12kg' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM seguimiento_envios WHERE numero_seguimiento = 'TRK-1001');

INSERT INTO seguimiento_envios (numero_seguimiento, estado, ubicacion_actual, destinatario, detalle_producto)
SELECT 'TRK-1002', 'En transito', 'Ruta a Valparaiso', 'Camila Rojas', 'Transportadora para gato mediana' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM seguimiento_envios WHERE numero_seguimiento = 'TRK-1002');

INSERT INTO seguimiento_envios (numero_seguimiento, estado, ubicacion_actual, destinatario, detalle_producto)
SELECT 'TRK-1003', 'Entregado', 'Domicilio del cliente', 'Diego Perez', 'Juguete interactivo para cachorro' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM seguimiento_envios WHERE numero_seguimiento = 'TRK-1003');
