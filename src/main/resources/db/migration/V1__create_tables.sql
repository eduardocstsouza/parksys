create table veiculos(
    id BIGSERIAL PRIMARY KEY,
    placa VARCHAR (10) NOT NULL UNIQUE,
    modelo VARCHAR (100),
    cor VARCHAR (50),
    tipo VARCHAR (20) NOT NULL,
    criado_em TIMESTAMP DEFAULT NOW()

);

create table vagas (
    id BIGSERIAL PRIMARY KEY,
    numero INTEGER NOT NULL UNIQUE,
    ocupada BOOLEAN DEFAULT FALSE
);

CREATE TABLE movimentacoes (
    id BIGSERIAL PRIMARY KEY,
    veiculo_id BIGINT REFERENCES veiculos(id),
    vaga_id BIGINT REFERENCES vagas(id),
    data_entrada TIMESTAMP NOT NULL,
    data_saida TIMESTAMP,
    valor_pago NUMERIC(10,2),
    status VARCHAR(20) DEFAULT 'ATIVO'
);

CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'OPERADOR'
);