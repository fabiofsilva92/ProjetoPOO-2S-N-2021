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