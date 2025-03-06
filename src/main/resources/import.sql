-- INSERTS PARA tb_categoria
INSERT INTO tb_categoria (nome) VALUES ('Eletricista');
INSERT INTO tb_categoria (nome) VALUES ('Encanador');
INSERT INTO tb_categoria (nome) VALUES ('Pintor');
INSERT INTO tb_categoria (nome) VALUES ('Marceneiro');
INSERT INTO tb_categoria (nome) VALUES ('Pedreiro');
INSERT INTO tb_categoria (nome) VALUES ('Jardinagem');
INSERT INTO tb_categoria (nome) VALUES ('Instalação de Ar-Condicionado');
INSERT INTO tb_categoria (nome) VALUES ('Serviços Gerais');
INSERT INTO tb_categoria (nome) VALUES ('Consultoria Técnica');
INSERT INTO tb_categoria (nome) VALUES ('Manutenção Predial');

-- INSERTS PARA tb_usuario
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('João Silva', 'joao@gmail.com', '123456789', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 0, TIMESTAMP WITH TIME ZONE '2023-01-10T10:00:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Maria Oliveira', 'maria@gmail.com', '456789123', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 1, TIMESTAMP WITH TIME ZONE '2023-02-15T14:30:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Carlos Pereira', 'carlos@gmail.com', '789456123', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 1, TIMESTAMP WITH TIME ZONE '2023-03-12T09:00:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Ana Souza', 'ana@gmail.com', '147258369', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 0, TIMESTAMP WITH TIME ZONE '2023-04-05T16:45:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Pedro Lima', 'pedro@gmail.com', '258369147', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 1, TIMESTAMP WITH TIME ZONE '2023-05-20T11:15:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Fernanda Costa', 'fernanda@gmail.com', '369258147', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 0, TIMESTAMP WITH TIME ZONE '2023-06-08T13:25:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Lucas Mendes', 'lucas@gmail.com', '258147369', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 1, TIMESTAMP WITH TIME ZONE '2023-07-30T08:50:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Juliana Rocha', 'juliana@gmail.com', '159487236', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 0, TIMESTAMP WITH TIME ZONE '2023-08-18T17:10:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Ricardo Borges', 'ricardo@gmail.com', '369147258', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 1, TIMESTAMP WITH TIME ZONE '2023-09-22T12:40:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Bianca Carvalho', 'bianca@gmail.com', '847159236', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 0, TIMESTAMP WITH TIME ZONE '2023-10-14T15:05:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Fabio', 'fabio@gmail.com', '357698214', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 2, TIMESTAMP WITH TIME ZONE '2023-10-14T15:05:00Z');
INSERT INTO tb_usuario (nome, email, cpf_ou_cnpj, nascimento, senha, tipo, data_criacao) VALUES ('Fabio Morais', 'fabio.lmorais99@gmail.com', '357214698', '1999-01-01', '$2a$10$4/7LJISAoTP72cotjVqkOOmjnS2nQedRh.SOx4A/XjGhL76gui2P2', 2, TIMESTAMP WITH TIME ZONE '2023-10-14T15:05:00Z');

INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENTE');
INSERT INTO tb_role (authority) VALUES ('ROLE_PRESTADOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (5, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (5, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (6, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (7, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (7, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (8, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (9, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (9, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (10, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (11, 3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (12, 1);

-- INSERTS PARA tb_servico
INSERT INTO tb_servico (nome, descricao, preco_minimo, preco_maximo, categoria_id, prestador_id) VALUES ('Instalação de Fiação', 'Instalação completa de fiação elétrica residencial.', 150.0, 300.0, 1, 2);
INSERT INTO tb_servico (nome, descricao, preco_minimo, preco_maximo, categoria_id, prestador_id) VALUES ('Reparo em Encanamento', 'Correção de vazamentos e instalação de tubos.', 100.0, 250.0, 2, 3);
INSERT INTO tb_servico (nome, descricao, preco_minimo, preco_maximo, categoria_id, prestador_id) VALUES ('Pintura de Parede', 'Pintura interna e externa.', 200.0, 500.0, 3, 5);
INSERT INTO tb_servico (nome, descricao, preco_minimo, preco_maximo, categoria_id, prestador_id) VALUES ('Fabricação de Móveis', 'Produção de móveis sob medida.', 1000.0, 5000.0, 4, 7);
INSERT INTO tb_servico (nome, descricao, preco_minimo, preco_maximo, categoria_id, prestador_id) VALUES ('Construção de Muros', 'Levantamento de muros em alvenaria.', 500.0, 1500.0, 5, 9);
INSERT INTO tb_servico (nome, descricao, preco_minimo, preco_maximo, categoria_id, prestador_id) VALUES ('Jardinagem Residencial', 'Manutenção de jardins e podas.', 120.0, 350.0, 6, 7);
INSERT INTO tb_servico (nome, descricao, preco_minimo, preco_maximo, categoria_id, prestador_id) VALUES ('Instalação de Ar-Condicionado', 'Instalação de aparelhos de ar-condicionado split.', 250.0, 600.0, 7, 9);
INSERT INTO tb_servico (nome, descricao, preco_minimo, preco_maximo, categoria_id, prestador_id) VALUES ('Limpeza Geral', 'Limpeza residencial e comercial.', 100.0, 300.0, 8, 3);
INSERT INTO tb_servico (nome, descricao, preco_minimo, preco_maximo, categoria_id, prestador_id) VALUES ('Consultoria Elétrica', 'Consultoria técnica para projetos elétricos.', 200.0, 800.0, 9, 2);
INSERT INTO tb_servico (nome, descricao, preco_minimo, preco_maximo, categoria_id, prestador_id) VALUES ('Manutenção Predial', 'Manutenção de infraestrutura predial.', 300.0, 1000.0, 10, 5);

-- INSERTS PARA tb_endereco
INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cidade, estado, cep, principal, usuario_id) VALUES ('Rua das Flores', '123', 'Apto 45', 'Centro', 'São Paulo', 'SP', '01001-000', TRUE, 1);
INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cidade, estado, cep, principal, usuario_id) VALUES ('Av. Brasil', '456', NULL, 'Jardim América', 'Rio de Janeiro', 'RJ', '20031-000', TRUE, 2);
INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cidade, estado, cep, principal, usuario_id) VALUES ('Rua das Palmeiras', '789', NULL, 'Boa Viagem', 'Recife', 'PE', '50050-000', TRUE, 3);
INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cidade, estado, cep, principal, usuario_id) VALUES ('Rua 7 de Setembro', '101', NULL, 'Centro', 'Curitiba', 'PR', '80010-000', TRUE, 4);
INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cidade, estado, cep, principal, usuario_id) VALUES ('Av. Paulista', '999', 'Sala 1234', 'Bela Vista', 'São Paulo', 'SP', '01311-200', TRUE, 5);
INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cidade, estado, cep, principal, usuario_id) VALUES ('Rua XV de Novembro', '1100', NULL, 'Centro', 'Florianópolis', 'SC', '88010-000', TRUE, 6);
INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cidade, estado, cep, principal, usuario_id) VALUES ('Rua das Andorinhas', '222', NULL, 'Tatuapé', 'São Paulo', 'SP', '03401-000', TRUE, 7);
INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cidade, estado, cep, principal, usuario_id) VALUES ('Av. Getúlio Vargas', '77', NULL, 'Centro', 'Porto Alegre', 'RS', '90010-000', TRUE, 8);
INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cidade, estado, cep, principal, usuario_id) VALUES ('Rua Goiás', '456', 'Bloco B', 'Setor Bueno', 'Goiânia', 'GO', '74000-000', TRUE, 9);
INSERT INTO tb_endereco (logradouro, numero, complemento, bairro, cidade, estado, cep, principal, usuario_id) VALUES ('Rua Amazonas', '321', NULL, 'Savassi', 'Belo Horizonte', 'MG', '30100-000', TRUE, 10);

-- INSERTS PARA tb_orcamento
INSERT INTO tb_orcamento (descricao, preco, data_inicio, data_fim, data_criacao, status, cliente_id, prestador_id, endereco_id) VALUES ('Instalação de chuveiro elétrico', 250.0, '2023-01-10', '2023-01-15', TIMESTAMP WITH TIME ZONE '2023-01-05T14:00:00Z', 0, 1, 2, 1);
INSERT INTO tb_orcamento (descricao, preco, data_inicio, data_fim, data_criacao, status, cliente_id, prestador_id, endereco_id) VALUES ('Reparo hidráulico no banheiro', 150.0, '2023-02-20', '2023-02-22', TIMESTAMP WITH TIME ZONE '2023-02-15T10:30:00Z', 1, 4, 3, 2);
INSERT INTO tb_orcamento (descricao, preco, data_inicio, data_fim, data_criacao, status, cliente_id, prestador_id, endereco_id) VALUES ('Pintura de sala', 400.0, '2023-03-01', '2023-03-05', TIMESTAMP WITH TIME ZONE '2023-02-25T13:00:00Z', 2, 5, 3, 3);
INSERT INTO tb_orcamento (descricao, preco, data_inicio, data_fim, data_criacao, status, cliente_id, prestador_id, endereco_id) VALUES ('Instalação de ar-condicionado', 600.0, '2023-03-15', '2023-03-20', TIMESTAMP WITH TIME ZONE '2023-03-10T10:00:00Z', 1, 9, 5, 4);
INSERT INTO tb_orcamento (descricao, preco, data_inicio, data_fim, data_criacao, status, cliente_id, prestador_id, endereco_id) VALUES ('Montagem de móveis planejados', 2000.0, '2023-04-10', '2023-04-20', TIMESTAMP WITH TIME ZONE '2023-04-05T15:00:00Z', 0, 7, 10, 5);
INSERT INTO tb_orcamento (descricao, preco, data_inicio, data_fim, data_criacao, status, cliente_id, prestador_id, endereco_id) VALUES ('Jardinagem mensal', 300.0, '2023-05-01', '2023-05-02', TIMESTAMP WITH TIME ZONE '2023-04-25T11:00:00Z', 1, 7, 9, 6);
INSERT INTO tb_orcamento (descricao, preco, data_inicio, data_fim, data_criacao, status, cliente_id, prestador_id, endereco_id) VALUES ('Revisão elétrica geral', 800.0, '2023-06-15', '2023-06-20', TIMESTAMP WITH TIME ZONE '2023-06-01T08:00:00Z', 0, 2, 10, 7);
INSERT INTO tb_orcamento (descricao, preco, data_inicio, data_fim, data_criacao, status, cliente_id, prestador_id, endereco_id) VALUES ('Manutenção de telhado', 1200.0, '2023-07-10', '2023-07-15', TIMESTAMP WITH TIME ZONE '2023-06-30T14:00:00Z', 1, 5, 8, 8);
INSERT INTO tb_orcamento (descricao, preco, data_inicio, data_fim, data_criacao, status, cliente_id, prestador_id, endereco_id) VALUES ('Instalação de sistema solar', 15000.0, '2023-08-01', '2023-08-30', TIMESTAMP WITH TIME ZONE '2023-07-15T09:30:00Z', 0, 2, 6, 9);
INSERT INTO tb_orcamento (descricao, preco, data_inicio, data_fim, data_criacao, status, cliente_id, prestador_id, endereco_id) VALUES ('Limpeza de fachada', 450.0, '2023-09-05', '2023-09-10', TIMESTAMP WITH TIME ZONE '2023-08-25T16:00:00Z', 1, 3, 2, 10);

-- INSERTS PARA tb_chat
INSERT INTO tb_chat (data_criacao, cliente_id, prestador_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-01-06T12:00:00Z', 1, 2);
INSERT INTO tb_chat (data_criacao, cliente_id, prestador_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-02-16T15:00:00Z', 4, 3);
INSERT INTO tb_chat (data_criacao, cliente_id, prestador_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-03-01T09:00:00Z', 2, 5);
INSERT INTO tb_chat (data_criacao, cliente_id, prestador_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-04-10T14:30:00Z', 1, 9);
INSERT INTO tb_chat (data_criacao, cliente_id, prestador_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-05-05T08:15:00Z', 7, 7);
INSERT INTO tb_chat (data_criacao, cliente_id, prestador_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-06-18T11:45:00Z', 6, 2);
INSERT INTO tb_chat (data_criacao, cliente_id, prestador_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-07-12T10:00:00Z', 3, 5);
INSERT INTO tb_chat (data_criacao, cliente_id, prestador_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-08-20T13:20:00Z', 9, 5);
INSERT INTO tb_chat (data_criacao, cliente_id, prestador_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-09-01T18:00:00Z', 10, 3);
INSERT INTO tb_chat (data_criacao, cliente_id, prestador_id) VALUES (TIMESTAMP WITH TIME ZONE '2023-09-15T15:45:00Z', 8, 2);

-- INSERTS PARA tb_avaliacao
INSERT INTO tb_avaliacao (nota, comentario, criado_em, atualizado_em, avaliador_id, avaliado_id) VALUES (5, 'Excelente trabalho, recomendo muito!', TIMESTAMP WITH TIME ZONE '2023-01-20T14:00:00Z', TIMESTAMP WITH TIME ZONE '2023-01-22T10:00:00Z', 1, 2);
INSERT INTO tb_avaliacao (nota, comentario, criado_em, atualizado_em, avaliador_id, avaliado_id) VALUES (4, 'Serviço bom, mas pode melhorar.', TIMESTAMP WITH TIME ZONE '2023-02-15T09:00:00Z', TIMESTAMP WITH TIME ZONE '2023-02-16T12:00:00Z', 3, 5);
INSERT INTO tb_avaliacao (nota, comentario, criado_em, atualizado_em, avaliador_id, avaliado_id) VALUES (3, 'Atendeu, mas faltou capricho.', TIMESTAMP WITH TIME ZONE '2023-03-05T08:30:00Z', TIMESTAMP WITH TIME ZONE '2023-03-05T11:00:00Z', 4, 3);
INSERT INTO tb_avaliacao (nota, comentario, criado_em, atualizado_em, avaliador_id, avaliado_id) VALUES (5, 'Ótimo profissional, serviço perfeito!', TIMESTAMP WITH TIME ZONE '2023-04-10T14:20:00Z', TIMESTAMP WITH TIME ZONE '2023-04-11T10:00:00Z', 2, 6);
INSERT INTO tb_avaliacao (nota, comentario, criado_em, atualizado_em, avaliador_id, avaliado_id) VALUES (2, 'Não gostei, serviço incompleto.', TIMESTAMP WITH TIME ZONE '2023-05-01T10:00:00Z', TIMESTAMP WITH TIME ZONE '2023-05-03T12:00:00Z', 7, 8);
INSERT INTO tb_avaliacao (nota, comentario, criado_em, atualizado_em, avaliador_id, avaliado_id) VALUES (4, 'Trabalho bom, mas preço elevado.', TIMESTAMP WITH TIME ZONE '2023-06-12T15:00:00Z', TIMESTAMP WITH TIME ZONE '2023-06-12T15:30:00Z', 9, 4);
INSERT INTO tb_avaliacao (nota, comentario, criado_em, atualizado_em, avaliador_id, avaliado_id) VALUES (1, 'Péssimo atendimento, não recomendo.', TIMESTAMP WITH TIME ZONE '2023-07-05T08:00:00Z', TIMESTAMP WITH TIME ZONE '2023-07-06T11:00:00Z', 6, 10);
INSERT INTO tb_avaliacao (nota, comentario, criado_em, atualizado_em, avaliador_id, avaliado_id) VALUES (5, 'Tudo perfeito, acima das expectativas.', TIMESTAMP WITH TIME ZONE '2023-08-01T09:30:00Z', TIMESTAMP WITH TIME ZONE '2023-08-02T13:00:00Z', 10, 3);
INSERT INTO tb_avaliacao (nota, comentario, criado_em, atualizado_em, avaliador_id, avaliado_id) VALUES (3, 'Cumpriu o básico, mas esperava mais.', TIMESTAMP WITH TIME ZONE '2023-09-20T08:00:00Z', TIMESTAMP WITH TIME ZONE '2023-09-21T09:00:00Z', 8, 7);
INSERT INTO tb_avaliacao (nota, comentario, criado_em, atualizado_em, avaliador_id, avaliado_id) VALUES (4, 'Gostei do serviço, porém atrasou.', TIMESTAMP WITH TIME ZONE '2023-10-05T10:00:00Z', TIMESTAMP WITH TIME ZONE '2023-10-06T11:30:00Z', 5, 9);

-- INSERTS PARA tb_mensagem
INSERT INTO tb_mensagem (conteudo, data_envio, chat_id, remetente_id) VALUES ('Olá, poderia enviar mais detalhes sobre o serviço?', TIMESTAMP WITH TIME ZONE '2023-01-06T12:00:00Z', 1, 1);
INSERT INTO tb_mensagem (conteudo, data_envio, chat_id, remetente_id) VALUES ('Claro, segue a descrição completa no arquivo.', TIMESTAMP WITH TIME ZONE '2023-01-06T12:10:00Z', 1, 2);
INSERT INTO tb_mensagem (conteudo, data_envio, chat_id, remetente_id) VALUES ('Bom dia, você atende no próximo final de semana?', TIMESTAMP WITH TIME ZONE '2023-02-16T15:00:00Z', 2, 4);
INSERT INTO tb_mensagem (conteudo, data_envio, chat_id, remetente_id) VALUES ('Sim, estou disponível no sábado pela manhã.', TIMESTAMP WITH TIME ZONE '2023-02-16T15:10:00Z', 2, 3);
INSERT INTO tb_mensagem (conteudo, data_envio, chat_id, remetente_id) VALUES ('Segue o orçamento para sua análise.', TIMESTAMP WITH TIME ZONE '2023-03-01T09:00:00Z', 3, 5);
INSERT INTO tb_mensagem (conteudo, data_envio, chat_id, remetente_id) VALUES ('Recebido, irei revisar e te retorno.', TIMESTAMP WITH TIME ZONE '2023-03-01T09:15:00Z', 3, 2);
INSERT INTO tb_mensagem (conteudo, data_envio, chat_id, remetente_id) VALUES ('Quando podemos marcar uma visita técnica?', TIMESTAMP WITH TIME ZONE '2023-04-10T14:00:00Z', 4, 1);
INSERT INTO tb_mensagem (conteudo, data_envio, chat_id, remetente_id) VALUES ('Pode ser amanhã às 10h?', TIMESTAMP WITH TIME ZONE '2023-04-10T14:05:00Z', 4, 9);
INSERT INTO tb_mensagem (conteudo, data_envio, chat_id, remetente_id) VALUES ('Enviei as fotos do local para análise.', TIMESTAMP WITH TIME ZONE '2023-05-05T08:30:00Z', 5, 7);
INSERT INTO tb_mensagem (conteudo, data_envio, chat_id, remetente_id) VALUES ('Recebi as fotos, farei o orçamento.', TIMESTAMP WITH TIME ZONE '2023-05-05T08:40:00Z', 5, 6);
