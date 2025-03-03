CREATE TABLE tb_dados_sensivel(
    id SERIAL PRIMARY KEY,
    usuario_documento VARCHAR(255) NOT NULL,
    credito_card_token VARCHAR(255) NOT NULL,
    valor_credito NUMERIC(19,2) NOT NULL
);