Tecnologia PHP

1º Banco

Responsaveis (Professores)

NOME VARCHAR(255) NOT NULL,
EMAIL VARCHAR(255) NOT NULL UNIQUE

2º Banco

Salas

NUMERO_SALA VARCHAR(255) NOT NULL UNIQUE,
EMAIL VARCHAR(255) (RESPONSAVEIS)

FOREIGN KEY (EMAIL) REFERENCES

3º Banco

Ativos Patrimonial (Itens)

PATRIMONIO PRIMARY KEY VARCHAR(255) NOT NULL 
DESCRICAO PRIMARY KEY VARCHAR(255) NOT NULL
CATEGORIA VARCHAR(255) NOT NULL
STATUS VARCHAR(255) NOT NULL
NUMERO_SALA VARCHAR(255) (SALA)

FOREIGN KEY (NUMERO_SALA) REFERENCES

Tecnologia PHP: Linguagem de programação usada para criar aplicativos web dinâmicos.

Bancos de Dados: Parecem ser utilizados para armazenar informações sobre responsáveis, salas e ativos patrimoniais.

Responsáveis (Professores ): Armazena informações sobre os responsáveis, como nome e email. O email é definido como único (UNIQUE), garantindo que cada responsável tenha um email único no sistema.

Salas: Registra as informações das salas, como número e email do responsável. O número da sala é definido como único (UNIQUE), e há uma chave estrangeira (FOREIGN KEY) referenciando o email do responsável.

Ativos Patrimoniais (Itens): Contém informações sobre os ativos patrimoniais, como patrimônio, descrição, categoria, status e número da sala em que estão localizados. Tanto o patrimônio quanto a descrição são definidos como chaves primárias (PRIMARY KEY), o que é incomum e pode ser um erro. Há também uma chave estrangeira (FOREIGN KEY) referenciando o número da sala onde o ativo está localizado.