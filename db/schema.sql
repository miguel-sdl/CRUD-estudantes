CREATE DATABASE cadastro_estudante;

CREATE TABLE `cadastro_estudante`.`estudante` (
  `idestudante` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `idade` INT NOT NULL,
  `serie` INT NOT NULL,
  PRIMARY KEY (`idestudante`)
);

CREATE TABLE `cadastro_estudante`.`notas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_estudante` INT NOT NULL,
  `portugues` DECIMAL(4,2) NULL,
  `matematica` DECIMAL(4,2) NULL,
  `historia` DECIMAL(4,2) NULL,
  `geografia` DECIMAL(4,2) NULL,
  `fisica` DECIMAL(4,2) NULL,
  `quimica` DECIMAL(4,2) NULL,
  `biologia` DECIMAL(4,2) NULL,
  `ingles` DECIMAL(4,2) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_id_estudante`
    FOREIGN KEY (`id_estudante`)
    REFERENCES `cadastro_estudante`.`estudante` (`idestudante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
