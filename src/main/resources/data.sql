INSERT INTO user (id, first_name,last_name,phone,email,cpf,avatar) VALUES
(1, 'jaime','nascimento','999504253','jaime@email.com','20999123872','www.endereco.avatar.com.br'),
(2, 'eliane','nascimento','999504214','eliane@email.com','03161451413','www.endereco.avatar.com.br');

INSERT INTO user_login (id, login,password,user_id) VALUES
(3, 'jaime','$2y$12$M6yBmuWSoh97hYU0RkXlouRSf/HM1MoorP.egdw2AJLphpDDwgxtC',1),
(4, 'eliane','$2y$12$M6yBmuWSoh97hYU0RkXlouRSf/HM1MoorP.egdw2AJLphpDDwgxtC',2);
