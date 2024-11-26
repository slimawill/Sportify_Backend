-- Clear existing data in proper order (respecting foreign keys)
DELETE FROM SPF_CURRICULO;
DELETE FROM SPF_HABILIDADE;
DELETE FROM SPF_EXPERIENCIA;
DELETE FROM SPF_PREMIACAO_EQUIPE;
DELETE FROM SPF_PREMIACAO_INDIVIDUAL;
DELETE FROM SPF_PARTICIPACAO_EQUIPE;
DELETE FROM SPF_PARTICIPACAO_INDIVIDUAL;
DELETE FROM SPF_MODALIDADE_EVENTO;
DELETE FROM SPF_EVENTO_EQUIPE;
DELETE FROM SPF_EVENTO_INDIVIDUAL;
DELETE FROM SPF_TIPO_EVENTO;
DELETE FROM SPF_EVENTO;
DELETE FROM SPF_MIDIA;
DELETE FROM SPF_POSTAGEM;
DELETE FROM SPF_SEGUIDOR;
DELETE FROM SPF_SERVICO_OFERECIDO;
DELETE FROM SPF_PATROCINIO;
DELETE FROM SPF_PATROCINIO_OFERECIDO;
DELETE FROM SPF_PROGRAMA_INCENTIVO;
DELETE FROM SPF_FISCALIZA;
DELETE FROM SPF_GOVERNO;
DELETE FROM SPF_EMPRESA;
DELETE FROM SPF_PATROCINADOR;
DELETE FROM SPF_ATLETA;
DELETE FROM SPF_LOCAL;
DELETE FROM SPF_EQUIPE;
DELETE FROM SPF_AGENCIA;

-- 1. Insert Agencias
INSERT INTO SPF_AGENCIA VALUES
('12345678901234', 'ProSports Agency'),
('23456789012345', 'Elite Athletes Management'),
('34567890123456', 'Champions Management'),
('99999999999999', 'Super Will');

-- 2. Insert Equipes
INSERT INTO SPF_EQUIPE VALUES
('45678901234567', 'Equipe Velocidade', 5),
('56789012345678', 'Equipe Resistência', 4),
('67890123456789', 'Equipe Natação Masters', 6),
('99999999999998', 'Equipe Will', 6);

-- 3. Insert Atletas
INSERT INTO SPF_ATLETA VALUES
('joao_silva', 'João Silva', '1995-03-15', 'Natação', 'M', '12345678901234', '45678901234567','passwordpa'),
('maria_santos', 'Maria Santos', '1998-07-22', 'Atletismo', 'F', '23456789012345', '56789012345678','passwordpa'),
('pedro_costa', 'Pedro Costa', '1993-11-30', 'Natação', 'M', '34567890123456', '67890123456789','passwordpa');

-- 4. Insert Patrocinadores
INSERT INTO SPF_PATROCINADOR VALUES
('78901234567890', 'Federal'),
('89012345678901', 'Estadual');

-- 5. Insert Empresas
INSERT INTO SPF_EMPRESA VALUES
('90123456789012', 'SportTech Inc'),
('01234567890123', 'Athletic Gear Co');

-- 6. Insert Governo
INSERT INTO SPF_GOVERNO VALUES
('78901234567890', 'Esportes Olímpicos');

-- 7. Insert Local
INSERT INTO SPF_LOCAL VALUES
('Centro Aquático', '12345-678', 100),
('Pista de Atletismo', '23456-789', 200);

-- 8. Insert Eventos
INSERT INTO SPF_EVENTO (DATA_HORA, NOME, PATROCINADOR_CNPJ, CEP, NUMERO_LOCAL, CAPACIDADE) VALUES
('2024-06-15 14:00:00', 'Campeonato Nacional de Natação', '78901234567890', '12345-678', 100, 1000),
('2024-07-20 09:00:00', 'Maratona da Cidade', '89012345678901', '23456-789', 200, 5000);

-- 9. Insert Modalidade Evento
INSERT INTO SPF_MODALIDADE_EVENTO VALUES
(1, 'Natação'),
(1, 'Nado Livre'),
(2, 'Corrida');

-- 10. Insert Tipos de Evento
INSERT INTO SPF_TIPO_EVENTO VALUES
(1, 'Competição'),
(2, 'Maratona');

-- 11. Insert Evento Individual e Equipe
INSERT INTO SPF_EVENTO_INDIVIDUAL VALUES (1);
INSERT INTO SPF_EVENTO_EQUIPE VALUES (2);

-- 12. Insert Participações Individuais
INSERT INTO SPF_PARTICIPACAO_INDIVIDUAL (NOME_USUARIO, EVENTO_ID, MODALIDADE) VALUES
('joao_silva', 1, 'Natação'),
('maria_santos', 2, 'Corrida');

-- 13. Insert Participações em Equipe
INSERT INTO SPF_PARTICIPACAO_EQUIPE (NOME_USUARIO, EVENTO_ID, MODALIDADE) VALUES
('pedro_costa', 2, 'Revezamento');

-- 14. Insert Premiações
INSERT INTO SPF_PREMIACAO_INDIVIDUAL (DATA_HORA, CATEGORIA, TIPO, ID_PARTICIPACAO) VALUES
('2024-06-15 17:00:00', 'Ouro', 'Individual', 1);

INSERT INTO SPF_PREMIACAO_EQUIPE (DATA_HORA, CATEGORIA, TIPO, ID_PARTICIPACAO) VALUES
('2024-07-20 12:00:00', 'Prata', 'Equipe', 1);

-- 15. Insert Habilidades
INSERT INTO SPF_HABILIDADE VALUES
('joao_silva', 'Nado Crawl'),
('joao_silva', 'Nado Borboleta'),
('maria_santos', 'Corrida de Longa Distância'),
('pedro_costa', 'Nado Peito');

-- 16. Insert Experiências
INSERT INTO SPF_EXPERIENCIA VALUES
('joao_silva', 'Campeão Regional 2023'),
('maria_santos', 'Maratona de São Paulo 2023'),
('pedro_costa', 'Mundial de Natação 2022');

-- 17. Insert Currículos
INSERT INTO SPF_CURRICULO VALUES
('joao_silva', CURRENT_DATE),
('maria_santos', CURRENT_DATE),
('pedro_costa', CURRENT_DATE);

-- 18. Insert Seguidores
INSERT INTO SPF_SEGUIDOR VALUES
('joao_silva', 'maria_santos'),
('maria_santos', 'joao_silva'),
('pedro_costa', 'joao_silva');

-- 19. Insert Postagens
INSERT INTO SPF_POSTAGEM (NOME_USUARIO, DATA_HORA, CURTIDAS, COMENTARIOS) VALUES
('joao_silva', '2024-03-15 10:00:00', 150, 10),
('maria_santos', '2024-03-15 11:30:00', 200, 15),
('pedro_costa', '2024-03-16 09:00:00', 175, 12);

-- 20. Insert Mídias
INSERT INTO SPF_MIDIA VALUES
(1, 'treino_natacao.jpg'),
(2, 'corrida_matinal.jpg'),
(3, 'competicao.jpg');

-- 21. Insert Programas de Incentivo
INSERT INTO SPF_PROGRAMA_INCENTIVO VALUES
('78901234567890', 'Bolsa Atleta');

-- 22. Insert Patrocínios
INSERT INTO SPF_PATROCINIO VALUES
('CONT001', 50000, '2024-01-01', '2024-12-31'),
('CONT002', 75000, '2024-01-01', '2024-12-31');

-- 23. Insert Serviços Oferecidos
INSERT INTO SPF_SERVICO_OFERECIDO VALUES
('CONT001', 'Equipamentos'),
('CONT001', 'Treinamento'),
('CONT002', 'Nutrição');

-- 24. Insert Patrocínios Oferecidos
INSERT INTO SPF_PATROCINIO_OFERECIDO VALUES
('90123456789012', 'Gold'),
('01234567890123', 'Silver');

-- 25. Insert Fiscalização
INSERT INTO SPF_FISCALIZA VALUES
('90123456789012', '78901234567890');