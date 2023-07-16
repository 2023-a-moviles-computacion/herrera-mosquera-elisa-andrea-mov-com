//Tabla para usuarios

CREATE TABLE Usuarios (
    ID INT PRIMARY KEY,
    NombreUsuario VARCHAR(50),
    Contraseña VARCHAR(50),
    NombreCompleto VARCHAR(100),
    CorreoElectronico VARCHAR(100)
);

-- Ejemplo de consulta SELECT de todos los usuarios
SELECT * FROM Usuarios;

//Tabla para recetas

CREATE TABLE Recetas (
    ID INT PRIMARY KEY,
    IDUsuario INT,
    Titulo VARCHAR(100),
    Descripcion VARCHAR(200),
    Ingredientes VARCHAR(500),
    PasosPreparacion VARCHAR(1000),
    TiempoPreparacion INT,
    Categoria VARCHAR(50),
    FOREIGN KEY (IDUsuario) REFERENCES Usuarios(ID)
);

-- Ejemplo de consulta SELECT de todas las recetas
SELECT * FROM Recetas;

//Tabla compartir
CREATE TABLE Compartir (
    IDReceta INT,
    IDUsuario INT,
    PRIMARY KEY (IDReceta, IDUsuario),
    FOREIGN KEY (IDReceta) REFERENCES Recetas(ID),
    FOREIGN KEY (IDUsuario) REFERENCES Usuarios(ID)
);

-- Ejemplo de consulta SELECT de todas las recetas compartidas por un usuario específico
SELECT Recetas.*
FROM Recetas
JOIN Compartir ON Recetas.ID = Compartir.IDReceta
WHERE Compartir.IDUsuario = 1;

//Tabla de crear sesion


-- Tabla Sesiones
CREATE TABLE Sesiones (
    ID INT PRIMARY KEY,
    IDUsuario INT,
    FechaInicio DATETIME,
    FOREIGN KEY (IDUsuario) REFERENCES Usuarios(ID)
);

//crear cuenta
-- Tabla Cuentas
CREATE TABLE Cuentas (
    ID INT PRIMARY KEY,
    NombreUsuario VARCHAR(50) UNIQUE,
    Contraseña VARCHAR(50),
    NombreCompleto VARCHAR(100),
    CorreoElectronico VARCHAR(100)
);


