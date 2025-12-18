<h1 align="center">Cervejaria ProgIII</h1>

# Cervejaria - Sistema de Gerenciamento de Degustações

Este projeto foi desenvolvido para a disciplina de Programação III e tem como objetivo fornecer uma ferramenta prática para que entusiastas de cerveja possam registrar e gerenciar suas experiências de degustação.

# Sobre o Projeto
O sistema permite que o usuário documente detalhadamente cada cerveja degustada, criando um histórico personalizado de avaliações.

# Principais Funcionalidades:
* Registro de degustações: Cadastro de novas experiências, incluindo nome da cerveja, nota, detalhes, sugestões, rótulo, estilo e data.

* Filtragem por notas: Atribuição de pontuações para cada rótulo degustado.

* Histórico personalizado: Consulta de todas as degustações realizadas para comparação e referência futura.

# Tecnologias Utilizadas
* Linguagem: Java
* Paradigma: Orientação a Objetos (POO)
* Conceitos aplicados: Manipulação de coleções, persistência de dados e interface com o usuário.

# Bibliotecas utilizadas

| Biblioteca | Função |
| :--- | :--- |
| **MySQL Connector** | Conexão com o banco de dados |
| **jBCrypt** | Criptografia de senhas |
| **JCalendar** | Seletor de datas visual |

# Estrutura do Banco de Dados
O projeto utiliza um banco de dados relacional (MySQL) com a seguinte estrutura:
* Tabela usuario: Armazena os dados de acesso (nome, email e senha);
* Tabela cerveja: Catálogo técnico das cervejas (nome, tipo, teor alcoólico, IBU e país de origem);

* Tabela usuario_cerveja: Tabela associativa que registra a degustação. Contém a avaliação (nota), crítica, local, data, foto e sugestões de harmonização;

*O banco conta com um Trigger (apagar_usuario) que garante a integridade dos dados: ao deletar um usuário, todos os seus registros de degustação são removidos automaticamente.*

# Configuração do Ambiente
Para que a aplicação se conecte ao seu banco de dados local, você deve configurar o arquivo de propriedades.
Configurando o config.properties.
No diretório de recursos do projeto cervejeria_xurupis/properties, localize o arquivo de configuração e preencha com suas credenciais:

```Properties
sgbd = mysql
host = localhost
bd = cervejaria
login = seu_usuario_aqui
password = sua_senha_aqui
```
# Script de Criação
Você pode encontrar os scripts SQL de criação das tabelas na seção abaixo:

```SQL
-- Criação do banco
CREATE DATABASE cervejaria;

-- Tabela de Usuários
CREATE TABLE `usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) UNIQUE NOT NULL,
  `senha` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id_usuario`)
);

-- Tabela de Cervejas
CREATE TABLE `cerveja` (
  `id_cerveja` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `tipo` VARCHAR(30) NOT NULL,
  `teor_alcolico` DOUBLE NOT NULL,
  `ibu` INT NOT NULL,
  `pais` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_cerveja`)
);

-- Tabela de Degustações (Relacionamento)
CREATE TABLE `usuario_cerveja` (
  `id_cerveja_usuario` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `id_cerveja` INT NOT NULL,
  `data_degustacao` DATE NOT NULL,
  `local_degustacao` VARCHAR(50) NOT NULL,
  `avaliacao` INT(2) NOT NULL,
  `critica` VARCHAR(200) NOT NULL,
  `foto` VARCHAR(100) NOT NULL,
  `sugestao` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_cerveja_usuario`),
  FOREIGN KEY (`id_usuario`) REFERENCES usuario (`id_usuario`),
  FOREIGN KEY (`id_cerveja`) REFERENCES cerveja (`id_cerveja`)
);
```
Após criar as tabelas acima, você deve popular o banco com o catálogo inicial de cervejas. Para isso:

1. Localize o arquivo `bd.txt` na raiz do projeto.
2. Copie o conteúdo do arquivo.
3. Execute os comandos no seu terminal MySQL ou ferramenta de banco de dados.
**Nota:** Este arquivo contém os comandos `INSERT` necessários para carregar todas as cervejas pré-cadastradas no sistema.

# Como executar
* Certifique-se de ter o MySQL Server instalado e rodando.
* Execute os scripts SQL acima para criar a estrutura.
* Configure o arquivo .properties com seus dados.
* Compile e execute o projeto através da sua IDE (IntelliJ/Eclipse/VS Code).
