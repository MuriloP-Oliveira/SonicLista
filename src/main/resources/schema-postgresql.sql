CREATE TABLE IF NOT EXISTS tb_jogos(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nm_jogo VARCHAR(100) NOT NULL,
    ano_lancamento INTEGER NOT NULL,
    url_imagem VARCHAR(500)
);