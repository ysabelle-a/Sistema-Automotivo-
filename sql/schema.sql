CREATE DATABASE IF NOT EXISTS veiculo_db;
USE veiculo_db;

CREATE TABLE IF NOT EXISTS marca (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS modelo (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  marca_id BIGINT NOT NULL,
  CONSTRAINT fk_modelo_marca FOREIGN KEY (marca_id) REFERENCES marca(id)
);

CREATE TABLE IF NOT EXISTS veiculo (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  modelo_id BIGINT NOT NULL,
  ano INT NOT NULL,
  cor VARCHAR(100) NOT NULL,
  preco DECIMAL(12,2) NOT NULL,
  quilometragem BIGINT NOT NULL,
  status VARCHAR(50) NOT NULL,
  CONSTRAINT fk_veiculo_modelo FOREIGN KEY (modelo_id) REFERENCES modelo(id)
);

INSERT INTO marca (nome) VALUES ('Toyota'), ('Honda') ON DUPLICATE KEY UPDATE nome=nome;
INSERT INTO modelo (nome, marca_id) VALUES ('Corolla', (SELECT id FROM marca WHERE nome='Toyota')), ('Civic', (SELECT id FROM marca WHERE nome='Honda')) ON DUPLICATE KEY UPDATE nome=nome;

INSERT INTO veiculo (modelo_id, ano, cor, preco, quilometragem, status)
VALUES
((SELECT id FROM modelo WHERE nome='Corolla'), 2018, 'Prata', 75000.00, 55000, 'DISPONIVEL'),
((SELECT id FROM modelo WHERE nome='Civic'), 2019, 'Preto', 90000.00, 40000, 'DISPONIVEL');
