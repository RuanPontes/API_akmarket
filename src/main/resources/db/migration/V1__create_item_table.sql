CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    usuario VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(13),
    is_habilitado BOOLEAN NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    pode_negociar BOOLEAN NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT unique_users UNIQUE (usuario, email)
);

CREATE TABLE roles (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT unique_roles UNIQUE (nome)
);

CREATE TABLE user_roles(
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE itens (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    valor DECIMAL(10,2),
    tipo VARCHAR(100),
    adds VARCHAR(255),
    classe VARCHAR(50),
    user_id INT NOT NULL,
    data_criacao DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE validation_codes(
    id INT NOT NULL AUTO_INCREMENT,
    codigo INT NOT NULL,
    data_validade TIMESTAMP NOT NULL,
    is_validado BOOLEAN NOT NULL,
    user_id INT NOT NULL,
    data_validacao TIMESTAMP,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
)