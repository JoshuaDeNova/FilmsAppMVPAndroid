
-- -----------------------------------------------------
-- Table pelicula
-- -----------------------------------------------------
CREATE TABLE Pelicula(
  idpelicula INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL,
  descripcion VARCHAR(80) NOT NULL,
  numVotos INT NULL,
  trailer VARCHAR(255) NULL,
  imagen VARCHAR(255) NULL,
  genero VARCHAR(50) NOT NULL,
  PRIMARY KEY (idpelicula));


-- -----------------------------------------------------
-- Table Usuario
-- -----------------------------------------------------
CREATE TABLE Usuario(
  idUsuario INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellidos VARCHAR(50) NULL,
  email VARCHAR(255) NOT NULL,
  contraseña VARCHAR(255) NOT NULL,
  PRIMARY KEY (idUsuario));


-- -----------------------------------------------------
-- Table Historico
-- -----------------------------------------------------
CREATE TABLE Historico(
  idHistorico INT NOT NULL AUTO_INCREMENT,
  pelicula INT NOT NULL,
  usuario INT NOT NULL,
  INDEX fk_pelicula_has_Usuario_Usuario1_idx (Usuario ASC),
  INDEX fk_pelicula_has_Usuario_pelicula_idx (pelicula ASC),
  PRIMARY KEY (idHistorico),
  CONSTRAINT fk_pelicula_has_Usuario_pelicula
    FOREIGN KEY (pelicula)
    REFERENCES pelicula (idpelicula)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_pelicula_has_Usuario_Usuario1
    FOREIGN KEY (Usuario)
    REFERENCES Usuario (idUsuario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table Cine
-- -----------------------------------------------------
CREATE TABLE Cine(
  idCine INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(45) NOT NULL,
  direccion VARCHAR(45) NOT NULL,
  precioEntrada VARCHAR(45) NOT NULL,
  UNIQUE INDEX idCine_UNIQUE (idCine ASC),
  PRIMARY KEY (idCine));


-- -----------------------------------------------------
-- Table butaca
-- -----------------------------------------------------
CREATE TABLE Butaca(
  idbutaca INT NOT NULL AUTO_INCREMENT,
  estado VARCHAR(1) NULL,
  PRIMARY KEY (idbutaca),
  UNIQUE INDEX idbutaca_UNIQUE (idbutaca ASC));


-- -----------------------------------------------------
-- Table sesion
-- -----------------------------------------------------
CREATE TABLE Sesion(
  idsesion INT NOT NULL AUTO_INCREMENT,
  butaca INT NOT NULL,
  sala VARCHAR(2) NOT NULL,
  aforo VARCHAR(10) NULL,
  UNIQUE INDEX idsesion_UNIQUE (idsesion ASC),
  PRIMARY KEY (idsesion, butaca),
  INDEX fk_sesion_butaca1_idx (butaca ASC),
  CONSTRAINT fk_sesion_butaca1
    FOREIGN KEY (butaca)
    REFERENCES butaca (idbutaca)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table Proyecciones
-- -----------------------------------------------------
CREATE TABLE Proyecciones(
  idProyeccion VARCHAR(45) NOT NULL,
  pelicula INT NOT NULL,
  sesion INT NOT NULL,
  fecha VARCHAR(45) NOT NULL,
  horario VARCHAR(45) NOT NULL,
  PRIMARY KEY (idProyeccion),
  INDEX fk_pelicula_has_sesion_sesion1_idx (sesion ASC),
  INDEX fk_pelicula_has_sesion_pelicula1_idx (pelicula ASC),
  CONSTRAINT fk_pelicula_has_sesion_pelicula1
    FOREIGN KEY (pelicula)
    REFERENCES pelicula (idpelicula)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_pelicula_has_sesion_sesion1
    FOREIGN KEY (sesion)
    REFERENCES sesion (idsesion)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table sesionCinema
-- -----------------------------------------------------
CREATE TABLE sesionCinema(
  idSesionCinema INT NOT NULL AUTO_INCREMENT,
  cine INT NOT NULL,
  sesion INT NOT NULL,
  INDEX fk_Cine_has_sesion_sesion1_idx (sesion ASC),
  INDEX fk_Cine_has_sesion_Cine1_idx (Cine ASC),
  PRIMARY KEY (idSesionCinema),
  CONSTRAINT fk_Cine_has_sesion_Cine1
    FOREIGN KEY (Cine)
    REFERENCES Cine (idCine)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Cine_has_sesion_sesion1
    FOREIGN KEY (sesion)
    REFERENCES sesion (idsesion)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);