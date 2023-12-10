INSERT INTO users (id, usuario, senha, email, telefone, habilitado)
VALUES (1, 'admin', '$2a$10$dD39J/vyB6Lsn9GiVhXytOQUQu7ojItGqJAfuwQyyIPkoHUYin9Fi', 'email@email.com', null, true);

INSERT INTO roles (id, nome) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, nome) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles(user_id, role_id)
VALUES (1, 1);

INSERT INTO user_roles(user_id, role_id)
VALUES (1, 2);