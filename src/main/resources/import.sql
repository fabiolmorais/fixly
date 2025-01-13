INSERT INTO tb_usuario(id, nome, email, senha, tipo) VALUES (1, 'fabio', 'fabio@email.com', '12345678', 1);
INSERT INTO tb_usuario(id, nome, email, senha, tipo) VALUES (2, 'cintia', 'cintia@email.com', '12345678', 0);
INSERT INTO tb_usuario(id, nome, email, senha, tipo) VALUES (3, 'joao', 'joao@email.com', '12345678', 0);
INSERT INTO tb_usuario(id, nome, email, senha, tipo) VALUES (4, 'ana', 'ana@email.com', '12345678', 1);
INSERT INTO tb_usuario(id, nome, email, senha, tipo) VALUES (5, 'maria', 'maria@email.com', '12345678', 1);
INSERT INTO tb_usuario(id, nome, email, senha, tipo) VALUES (6, 'pedro', 'pedro@email.com', '12345678', 0);
INSERT INTO tb_usuario(id, nome, email, senha, tipo) VALUES (7, 'lucas', 'lucas@email.com', '12345678', 0);
INSERT INTO tb_usuario(id, nome, email, senha, tipo) VALUES (8, 'beatriz', 'beatriz@email.com', '12345678', 1);
INSERT INTO tb_usuario(id, nome, email, senha, tipo) VALUES (9, 'julia', 'julia@email.com', '12345678', 0);
INSERT INTO tb_usuario(id, nome, email, senha, tipo) VALUES (10, 'marcos', 'marcos@email.com', '12345678', 1);


INSERT INTO tb_endereco (id, usuario_id, bairro, cep, cidade, complemento, estado, logradouro, numero) VALUES (1, 1, 'Centro', '55000000', 'Caruaru', 'Apto 101', 'PE', 'Rua A', 12);
INSERT INTO tb_endereco (id, usuario_id, bairro, cep, cidade, complemento, estado, logradouro, numero) VALUES (2, 2, 'Bairro Novo', '66000000', 'Recife', 'Casa', 'PE', 'Rua B', 257);
INSERT INTO tb_endereco (id, usuario_id, bairro, cep, cidade, complemento, estado, logradouro, numero) VALUES (3, 3, 'Boa Vista', '55000001', 'Caruaru', 'Apto 302', 'PE', 'Rua C', 13);
INSERT INTO tb_endereco (id, usuario_id, bairro, cep, cidade, complemento, estado, logradouro, numero) VALUES (4, 4, 'Jardim', '55000002', 'Olinda', 'Casa', 'PE', 'Rua D', 64);
INSERT INTO tb_endereco (id, usuario_id, bairro, cep, cidade, complemento, estado, logradouro, numero) VALUES (5, 5, 'Centro', '55000003', 'Recife', 'Apto 202', 'PE', 'Rua E', 71);
INSERT INTO tb_endereco (id, usuario_id, bairro, cep, cidade, complemento, estado, logradouro, numero) VALUES (6, 6, 'São José', '55000004', 'Caruaru', 'Casa', 'PE', 'Rua F', 49);
INSERT INTO tb_endereco (id, usuario_id, bairro, cep, cidade, complemento, estado, logradouro, numero) VALUES (7, 7, 'Boa Viagem', '66000001', 'Recife', 'Apto 401', 'PE', 'Rua G', 234);
INSERT INTO tb_endereco (id, usuario_id, bairro, cep, cidade, complemento, estado, logradouro, numero) VALUES (8, 8, 'Boa Vista', '55000005', 'Caruaru', 'Casa', 'PE', 'Rua H', 205);
INSERT INTO tb_endereco (id, usuario_id, bairro, cep, cidade, complemento, estado, logradouro, numero) VALUES (9, 9, 'Centro', '55000006', 'Olinda', 'Apto 501', 'PE', 'Rua I', 99);
INSERT INTO tb_endereco (id, usuario_id, bairro, cep, cidade, complemento, estado, logradouro, numero) VALUES (10, 10, 'Jardim', '55000007', 'Recife', 'Casa', 'PE', 'Rua J', 77);


INSERT INTO tb_servico (id, nome, descricao, categoria, preco, usuario_id) VALUES (1, 'Instalar Lâmpada', 'Serviço elétrico', 'Elétrica', 50, 1);
INSERT INTO tb_servico (id, nome, descricao, categoria, preco, usuario_id) VALUES (2, 'Instalar Tomada', 'Serviço elétrico', 'Elétrica', 60, 1);
INSERT INTO tb_servico (id, nome, descricao, categoria, preco, usuario_id) VALUES (3, 'Trocar Torneira', 'Serviço hidráulico', 'Hidráulica', 80, 10);
INSERT INTO tb_servico (id, nome, descricao, categoria, preco, usuario_id) VALUES (4, 'Reparar Encanamento', 'Serviço hidráulico', 'Hidráulica', 90, 10);
INSERT INTO tb_servico (id, nome, descricao, categoria, preco, usuario_id) VALUES (5, 'Instalar Ventilador', 'Serviço elétrico', 'Elétrica', 70, 1);
INSERT INTO tb_servico (id, nome, descricao, categoria, preco, usuario_id) VALUES (6, 'Pintura de Parede', 'Serviço geral', 'Pintura', 150, 5);
INSERT INTO tb_servico (id, nome, descricao, categoria, preco, usuario_id) VALUES (7, 'Troca de Fiação', 'Serviço elétrico', 'Elétrica', 200, 1);
INSERT INTO tb_servico (id, nome, descricao, categoria, preco, usuario_id) VALUES (8, 'Limpeza de Caixa d’Água', 'Serviço geral', 'Limpeza', 100, 4);
INSERT INTO tb_servico (id, nome, descricao, categoria, preco, usuario_id) VALUES (9, 'Instalar Chuveiro', 'Serviço elétrico', 'Elétrica', 80, 1);
INSERT INTO tb_servico (id, nome, descricao, categoria, preco, usuario_id) VALUES (10, 'Reparar Janela', 'Serviço geral', 'Carpintaria', 120, 8);


INSERT INTO tb_solicitacao (id, status, data_criacao, data_servico, usuario_id) VALUES (1, 0, '2025-01-01', '2025-01-02', 2);
INSERT INTO tb_solicitacao (id, status, data_criacao, data_servico, usuario_id) VALUES (2, 2, '2025-01-03', '2025-01-04', 3);
INSERT INTO tb_solicitacao (id, status, data_criacao, data_servico, usuario_id) VALUES (3, 1, '2025-01-05', '2025-01-06', 6);
INSERT INTO tb_solicitacao (id, status, data_criacao, data_servico, usuario_id) VALUES (4, 0, '2025-01-07', '2025-01-08', 7);
INSERT INTO tb_solicitacao (id, status, data_criacao, data_servico, usuario_id) VALUES (5, 2, '2025-01-09', '2025-01-10', 9);
INSERT INTO tb_solicitacao (id, status, data_criacao, data_servico, usuario_id) VALUES (6, 0, '2025-01-11', '2025-01-12', 7);
INSERT INTO tb_solicitacao (id, status, data_criacao, data_servico, usuario_id) VALUES (7, 2, '2025-01-13', '2025-01-14', 2);
INSERT INTO tb_solicitacao (id, status, data_criacao, data_servico, usuario_id) VALUES (8, 1, '2025-01-15', '2025-01-16', 9);
INSERT INTO tb_solicitacao (id, status, data_criacao, data_servico, usuario_id) VALUES (9, 0, '2025-01-17', '2025-01-18', 3);
INSERT INTO tb_solicitacao (id, status, data_criacao, data_servico, usuario_id) VALUES (10, 2, '2025-01-19', '2025-01-20', 6);


INSERT INTO tb_solicitacao_servico (servico_id, solicitacao_id) VALUES (1, 1);
INSERT INTO tb_solicitacao_servico (servico_id, solicitacao_id) VALUES (2, 2);
INSERT INTO tb_solicitacao_servico (servico_id, solicitacao_id) VALUES (3, 3);
INSERT INTO tb_solicitacao_servico (servico_id, solicitacao_id) VALUES (4, 4);
INSERT INTO tb_solicitacao_servico (servico_id, solicitacao_id) VALUES (5, 5);
INSERT INTO tb_solicitacao_servico (servico_id, solicitacao_id) VALUES (6, 6);
INSERT INTO tb_solicitacao_servico (servico_id, solicitacao_id) VALUES (7, 7);
INSERT INTO tb_solicitacao_servico (servico_id, solicitacao_id) VALUES (8, 8);
INSERT INTO tb_solicitacao_servico (servico_id, solicitacao_id) VALUES (9, 9);
INSERT INTO tb_solicitacao_servico (servico_id, solicitacao_id) VALUES (10, 10);
