USE BusesExtraurbanosXela;

CREATE TABLE Sucursal(
    id_sucursal INT AUTO_INCREMENT PRIMARY KEY,
    departamento VARCHAR(50) NOT NULL,
    municipio VARCHAR(20) NOT NULL,
    latitud DOUBLE(10,6) NOT NULL,
    longitud DOUBLE(10,6) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    estado ENUM('HABILITADO', 'DESHABILITADO') DEFAULT 'HABILITADO'

);

CREATE TABLE ConfiguracionSucursal(
    id_configuracion INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursal INT NOT NULL,
    depreciacionPorKm DOUBLE NOT NULL,
    costoCombustible DOUBLE(10,2) NOT NULL,
    FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Usuario(
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursal INT,
    usuario VARCHAR(20) NOT NULL UNIQUE,
    clave VARCHAR(20) NOT NULL,
    rol ENUM('VIAJERO', 'ADMIN_SISTEMA', 'ADMIN_SUCURSAL') NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    dpi VARCHAR(20) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    direccion VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL UNIQUE,
    nit VARCHAR(20) NOT NULL unique,
    saldoCartera DOUBLE(10,2) DEFAULT 0.0,
    FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Bus(
    id_bus INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursal INT NOT NULL,
    id_sucursalUbicacionActual INT,
    imagen VARCHAR(50) NOT NULL,
    numeroPlaca VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    añoFabricacion INT NOT NULL,
    capacidadPasajeros INT NOT NULL,
    kilometrajeActual INT NOT NULL,
    estadoActividad ENUM('LIBRE', 'PROGRAMADO', 'VIAJANDO') DEFAULT 'LIBRE',
    estado ENUM('HABILITADO', 'DESHABILITADO') DEFAULT 'HABILITADO',
    FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal) ON DELETE CASCADE,
    FOREIGN KEY (id_sucursalUbicacionActual) REFERENCES Sucursal(id_sucursal) ON DELETE CASCADE
);

CREATE TABLE Chofer(
    id_chofer INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursal INT NOT NULL,
    imagen VARCHAR(50) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    licencia VARCHAR(50) NOT NULL,
    tipoLicencia VARCHAR(50) NOT NULL,
    vencimientoLicencia DATE NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    salarioBaseViaje DOUBLE(10,2) NOT NULL,
    estadoActividad ENUM('LIBRE', 'PROGRAMADO', 'VIAJANDO') DEFAULT 'LIBRE',
    estado ENUM('HABILITADO', 'DESHABILITADO') DEFAULT 'HABILITADO',
    FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE RutaRegular(
    id_rutaRegular INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursalInicio INT NOT NULL,
    id_sucursalDestino INT NOT NULL,
    distancia DOUBLE(10,2) NOT NULL,
    precio DOUBLE(10,2) NOT NULL,
    FOREIGN KEY (id_sucursalInicio) REFERENCES Sucursal(id_sucursal) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_sucursalDestino) REFERENCES Sucursal(id_sucursal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE RutaPrivada(
    id_rutaPrivada INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursalInicio INT NOT NULL,
    latitudB DOUBLE(10,6) NOT NULL,
    longitudB DOUBLE(10,6) NOT NULL,
    direccionB VARCHAR(50) NOT NULL,
    distancia DOUBLE(10,2) NOT NULL,
    precio DOUBLE(10,2) NOT NULL,
    FOREIGN KEY (id_sucursalInicio) REFERENCES Sucursal(id_sucursal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ViajeRegular(
    id_viajeRegular INT AUTO_INCREMENT PRIMARY KEY,
    id_sucursal INT NOT NULL,
    id_bus INT NOT NULL,
    id_chofer INT NOT NULL,
    id_rutaRegular INT NOT NULL,
    pasajeros INT DEFAULT 0,
    fechaSalida DATE NOT NULL,
    horaSalida TIME NOT NULL,
    fechaEstimadaLlegada DATE NOT NULL,
    HoraEstimadaLlegada TIME NOT NULL,
    estadoViaje ENUM('PROGRAMADO', 'VIAJANDO') DEFAULT 'PROGRAMADO',
    id_detallesViaje INT,
    FOREIGN KEY (id_bus) REFERENCES Bus(id_bus) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_chofer) REFERENCES Chofer(id_chofer) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_rutaRegular) REFERENCES RutaRegular(id_rutaRegular) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ViajeAlquiler(
    id_viajeAlquiler INT AUTO_INCREMENT PRIMARY KEY,
    id_UsuarioContratista INT NOT NULL,
    id_bus INT NOT NULL,
    id_chofer INT NOT NULL,
    id_rutaPrivada INT NULL,
    fechaSalida DATE NOT NULL,
    horaSalida TIME NOT NULL,
    fechaEstimadaRegreso DATE NOT NULL,
    HoraEstimadaRegreso TIME NOT NULL,
    estadoViaje ENUM('PROGRAMADO', 'VIAJANDO', 'FINALIZADO', 'CANCELADO') DEFAULT 'PROGRAMADO',
    id_detallesViaje INT,
    FOREIGN KEY (id_UsuarioContratista) REFERENCES Usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_bus) REFERENCES Bus(id_bus) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_chofer) REFERENCES Chofer(id_chofer) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_rutaPrivada) REFERENCES RutaPrivada(id_rutaPrivada) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE DetallesViaje(
    id_detalleViaje INT AUTO_INCREMENT PRIMARY KEY,
    id_viajeRegular INT NULL,
    id_viajeAlquiler INT NULL,
    horaSalida TIME NOT NULL,
    kilometrajeSalida INT NOT NULL,
    horaLlegada TIME NOT NULL,
    kilometrajeLlegada INT NOT NULL,
    combustibleUtilizado DOUBLE(10,2) NOT NULL,
    salarioChofer DOUBLE(10,2) NOT NULL,
    aproximadoDepreciacion DOUBLE(10,2) NOT NULL,
    FOREIGN KEY (id_viajeRegular) REFERENCES ViajeRegular(id_viajeRegular),
    FOREIGN KEY (id_viajeAlquiler) REFERENCES ViajeAlquiler(id_viajeAlquiler)
);

CREATE TABLE Boleto(
    id_boleto INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_viajeRegular INT NOT NULL,
    numeroAsiento INT NOT NULL,
    precio DOUBLE(10,2) NOT NULL,
    fechaCompra DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_viajeRegular) REFERENCES ViajeRegular(id_viajeRegular) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE GastoTaller(
    id_gastoTaller INT AUTO_INCREMENT PRIMARY KEY,
    id_bus INT NOT NULL,
    montoManoObra DOUBLE(10,2) NOT NULL,
    montoRepuesto DOUBLE(10,2) NOT NULL,
    fechaMantenimiento DATE NOT NULL,
    FOREIGN KEY (id_bus) REFERENCES Bus(id_bus) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE GastoCombustible(
    id_gastoCombustible INT AUTO_INCREMENT PRIMARY KEY,
    id_bus INT NOT NULL,
    fecha DATE NOT NULL,
    galones DOUBLE NOT NULL,
    FOREIGN KEY (id_bus) REFERENCES Bus(id_bus) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Transaccion(
    id_transaccion INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    id_compraBoleto INT NULL,
    id_gastoCombustible INT NULL,
    id_gastoTaller INT NULL,
    id_viajeAlquiler INT NULL,
    total DOUBLE NOT NULL,
    FOREIGN KEY (id_compraBoleto) REFERENCES Boleto(id_boleto)ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_gastoCombustible) REFERENCES GastoCombustible(id_gastoCombustible)ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_gastoTaller) REFERENCES GastoTaller(id_gastoTaller)ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_viajeAlquiler) REFERENCES ViajeAlquiler(id_viajeAlquiler)ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Asiento (
    numero_asiento INT NOT NULL,
    id_bus INT NOT NULL,
    estado ENUM('HABILITADO', 'DESHABILITADO') DEFAULT 'HABILITADO',
    PRIMARY KEY (numero_asiento, id_bus),
    CONSTRAINT fk_asiento_bus FOREIGN KEY (id_bus) REFERENCES Bus(id_bus)ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE SolicitudViajePrivado(
    id_solicitudViajePrivado INT AUTO_INCREMENT PRIMARY KEY,
    cantidadPasajeros INT NOT NULL,
    distancia INT NOT NULL,
    id_usuario INT NOT NULL,
    id_sucursal INT NOT NULL,
    direccionDestino VARCHAR(100) NOT NULL,
    latitud DOUBLE(10,6) NOT NULL,
    longitud DOUBLE(10,6) NOT NULL,
    fechaSalida DATE NOT NULL,
    horaSalida TIME NOT NULL,
    fechaEstimadaRegreso DATE NOT NULL,
    HoraEstimadaRegreso TIME NOT NULL,
    costo DOUBLE(10,2) NOT NULL,
    estadoSolicitud ENUM ('PENDIENTE', 'ATENDIDA') DEFAULT 'PENDIENTE',
    estadoPago ENUM('CANCELADO', 'PENDIENTE') DEFAULT 'PENDIENTE',
    FOREIGN KEY (id_sucursal) REFERENCES Sucursal(id_sucursal),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);