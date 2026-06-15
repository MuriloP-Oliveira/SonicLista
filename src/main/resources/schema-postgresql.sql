CREATE TABLE IF NOT EXISTS tb_jogos(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nm_jogo VARCHAR(100) NOT NULL,
    ano_lancamento INTEGER NOT NULL,
    url_imagem VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS video_game (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    empresa VARCHAR(100) NOT NULL,
    geracao VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS jogo_video_game (
    jogo_id UUID REFERENCES tb_jogos(id) ON DELETE CASCADE,
    video_game_id UUID REFERENCES video_game(id) ON DELETE CASCADE,
    PRIMARY KEY (jogo_id, video_game_id)
);

CREATE TABLE IF NOT EXISTS usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS perfil (
    id SERIAL PRIMARY KEY,
    usuario_id UUID NOT NULL REFERENCES usuario(id) ON DELETE CASCADE UNIQUE,
    cargo VARCHAR(50) NOT NULL
);