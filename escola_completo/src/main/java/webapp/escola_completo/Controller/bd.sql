CREATE DATABASE Escola;


CREATE TABLE Verifica_cadastro_adm(
    Cpf INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

INSERT INTO Verifica_cadastro_adm (cpf, nome) VALUES ('999', 'diogo123');


SELECT * FROM "verifica_cadastro_adm";
SELECT * FROM "administrador";
SELECT * FROM "professor";
SELECT * FROM "aluno";
SELECT * FROM "materias";




