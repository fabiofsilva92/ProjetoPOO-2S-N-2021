
create table agendamento(
   id int auto_increment primary key,

   nome varchar(30) not null,

   sobreNome varchar(30),

   aula varchar(30) not null,

   dataAgendamento date,

   horario varchar(10)
   );

   select * from agendamento;


create table treino(
   id int primary key not null,
   nome varchar(30) not null,
   sobrenome varchar(30),
   tipo varchar(1) not null,
   musculo varchar(30),
   aparelho int,
   repeticoesQTD int,
   series int
   );


create table funcionario(
   id int auto_increment not null primary key,

   nome varchar(30) not null,

   sobrenome varchar(30),

   cpf varchar(30) not null,

   dtNascimento date,

   dtInicio date,

   sexo varchar(30),

   salario double,

   ddd int,

   telefone int,

   email char(100)

   );

create table equipamento(
   id int auto_increment not null primary key,

   nome varchar(30) not null,

   fabricante varchar(30),

   ano int,

   preco double

   );

CREATE TABLE cliente
(id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(20) NOT NULL,
sobrenome VARCHAR(30) NOT NULL,
cpf VARCHAR(20) NOT NULL,
dataNascimento DATE,
telefone VARCHAR(20),
sexo VARCHAR(10));