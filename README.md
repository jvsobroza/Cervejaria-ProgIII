# Cervejaria-ProgIII

Tabela cerveja:
CREATE TABLE `cervejeria`.`cerveja` ( `id_cerveja` INT NOT NULL AUTO_INCREMENT , `nome` VARCHAR(50) NOT NULL , `tipo` VARCHAR(30) NOT NULL , `teor_alcolico` DOUBLE NOT NULL , `ibu` INT NOT NULL , `pais` VARCHAR(50) NOT NULL , PRIMARY KEY (`id_cerveja`)) ENGINE = InnoDB;
Tabela Usuario_Cerveja:
CREATE TABLE `cervejeria`( `id_cerveja_usuario` INT NOT NULL AUTO_INCREMENT , `id_usuario` INT NOT NULL , `id_cerveja` INT NOT NULL , `data_degustacao` DATE NOT NULL , `local_degustacao` VARCHAR(50) NOT NULL , `avaliacao` INT(2) NOT NULL , `critica` VARCHAR(200) NOT NULL , `foto` VARCHAR(100) NOT NULL , PRIMARY KEY (`id_cerveja_usuario`), FOREIGN KEY (`id_usuario`) REFERENCES usuario (`id_usuario`), FOREIGN KEY (`id_cerveja`) REFERENCES cerveja (`id_cerveja`))
