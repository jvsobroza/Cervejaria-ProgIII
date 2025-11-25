<h1 align="center">Cervejaria ProgIII</h1>

###


# Tabelas banco:
* Tabela Usuario:
  ```bash
  CREATE TABLE `cervejaria`.`usuario` (`id_usuario` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(50) NOT NULL , `email` VARCHAR(50) UNIQUE NOT NULL , `senha` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id_usuario`));
* Trigger tabela Usuario:
  ```bash
  CREATE TRIGGER apagar_usuario BEFORE delete ON usuario FOR EACH ROW BEGIN DELETE FROM usuario_cerveja WHERE usuario_cerveja.id_usuario = OLD.id_usuario; END
* Tabela Cerveja:
  ```bash
  CREATE TABLE `cerveja` ( `id_cerveja` INT NOT NULL AUTO_INCREMENT , `nome` VARCHAR(50) NOT NULL ,
  `tipo` VARCHAR(30) NOT NULL , `teor_alcolico` DOUBLE NOT NULL , `ibu` INT NOT NULL ,
  `pais` VARCHAR(50) NOT NULL , PRIMARY KEY (`id_cerveja`));
* Tabela Usuario_Cerveja:
  ```bash
  CREATE TABLE `usuario_cerveja`( `id_cerveja_usuario` INT NOT NULL AUTO_INCREMENT , `id_usuario` INT NOT NULL ,
  `id_cerveja` INT NOT NULL , `data_degustacao` DATE NOT NULL , `local_degustacao` VARCHAR(50) NOT NULL,
  `avaliacao` INT(2) NOT NULL , `critica` VARCHAR(200) NOT NULL , `foto` VARCHAR(100) NOT NULL , `sugestao` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_cerveja_usuario`), FOREIGN KEY (`id_usuario`) REFERENCES usuario (`id_usuario`),
  FOREIGN KEY (`id_cerveja`) REFERENCES cerveja (`id_cerveja`));
