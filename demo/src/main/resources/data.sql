-- Inserindo usuários
INSERT INTO usuario (nome) VALUES ('João Silva');
INSERT INTO usuario (nome) VALUES ('Maria Santos');
INSERT INTO usuario (nome) VALUES ('Gabriel Dantas');


-- Inserindo perfis
INSERT INTO perfil (NOME) VALUES ('ADMIN');
INSERT INTO perfil (NOME) VALUES ('USER');



-- Inserindo associações entre usuários e perfis na tabela usuario_perfil
INSERT INTO usuario_perfil (usuario_id, perfil_id, data_criacao) VALUES (1, 1, '2025-06-05');
INSERT INTO usuario_perfil (usuario_id, perfil_id, data_criacao) VALUES (1, 2, '2025-06-05');
INSERT INTO usuario_perfil (usuario_id, perfil_id, data_criacao) VALUES (2, 2, '2025-06-05');



-- Inserindo endereços próximos ao CEP 25635-400 na tabela endereco
INSERT INTO endereco (cep, logradouro, bairro, localidade, uf, cliente_id) 
VALUES ('25635-210', 'Rua Aldo Tamancoldi', 'Chácara Flora', 'Petrópolis', 'RJ', 1);

INSERT INTO endereco (cep, logradouro, bairro, localidade, uf, cliente_id) 
VALUES ('25635-200', 'Rua Alfredo Batista', 'Chácara Flora', 'Petrópolis', 'RJ', 2);

INSERT INTO endereco (cep, logradouro, bairro, localidade, uf, cliente_id) 
VALUES ('25635-201', 'Rua Alfredo Schilick', 'Chácara Flora', 'Petrópolis', 'RJ', 3);



-- Inserindo categorias na tabela categoria
INSERT INTO categoria (tipo) VALUES ('Eletrônicos');
INSERT INTO categoria (tipo) VALUES ('Eletrodomésticos');
INSERT INTO categoria (tipo) VALUES ('Móveis');
INSERT INTO categoria (tipo) VALUES ('Vestuário');


-- Inserindo produtos na tabela produto
INSERT INTO produto (nome, preco, id_categoria) VALUES ('Smartphone', 2999.99, 1);
INSERT INTO produto (nome, preco, id_categoria) VALUES ('Geladeira', 2499.99, 2);
INSERT INTO produto (nome, preco, id_categoria) VALUES ('Armário', 1999.99, 1);
INSERT INTO produto (nome, preco, id_categoria) VALUES ('Tênis Nike Air', 1299.99, 2);


-- Inserindo pedidos na tabela pedido
INSERT INTO pedido (quantidade, preco, cliente_id, endereco_id, status) VALUES (2, 599.99, 1, 1, 'PENDENTE');
INSERT INTO pedido (quantidade, preco, cliente_id, endereco_id, status) VALUES (1, 1299.99, 2, 2, 'CONCLUIDO');



-- Inserindo relação entre pedidos e produtos na tabela pedido_produto
INSERT INTO pedido_produto (id_pedido, id_produto) VALUES (1, 1);
INSERT INTO pedido_produto (id_pedido, id_produto) VALUES (1, 3);
INSERT INTO pedido_produto (id_pedido, id_produto) VALUES (8, 2);
INSERT INTO pedido_produto (id_pedido, id_produto) VALUES (12, 4);



-- Inserindo favoritos na tabela favorito
INSERT INTO favorito (cliente_id, produto_id) VALUES (1, 1);
INSERT INTO favorito (cliente_id, produto_id) VALUES (1, 2);
INSERT INTO favorito (cliente_id, produto_id) VALUES (2, 3);
INSERT INTO favorito (cliente_id, produto_id) VALUES (2, 4);



-- Inserindo wishlists na tabela wishlist
INSERT INTO wishlist (cliente_id, data_criacao) VALUES (1, '2025-06-05 13:37:00');
INSERT INTO wishlist (cliente_id, data_criacao) VALUES (2, '2025-06-05 13:37:00');



-- Inserindo relação entre wishlists e produtos na tabela wishlist_produto
INSERT INTO wishlist_produto (wishlist_id, produto_id) VALUES (1, 1);
INSERT INTO wishlist_produto (wishlist_id, produto_id) VALUES (1, 3);
INSERT INTO wishlist_produto (wishlist_id, produto_id) VALUES (2, 2);
INSERT INTO wishlist_produto (wishlist_id, produto_id) VALUES (2, 4);
