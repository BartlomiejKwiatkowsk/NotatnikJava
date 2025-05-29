CREATE TABLE IF NOT EXISTS role (
                                    id BIGSERIAL PRIMARY KEY,
                                    name VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS app_user (
                                        id BIGSERIAL PRIMARY KEY,
                                        username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT REFERENCES role(id)
    );

CREATE TABLE IF NOT EXISTS note (
                                    id BIGSERIAL PRIMARY KEY,
                                    content TEXT NOT NULL,
                                    created_at TIMESTAMP DEFAULT now(),
    user_id BIGINT REFERENCES app_user(id) ON DELETE CASCADE
    );
INSERT INTO role (name) VALUES ('USER') ON CONFLICT (name) DO NOTHING;
INSERT INTO role (name) VALUES ('ADMIN') ON CONFLICT (name) DO NOTHING;

INSERT INTO app_user (username, password, role_id)
VALUES ('user', 'userpass', (SELECT id FROM role WHERE name = 'USER')) ON CONFLICT (username) DO NOTHING;

INSERT INTO app_user (username, password, role_id)
VALUES ('admin', 'adminpass', (SELECT id FROM role WHERE name = 'ADMIN')) ON CONFLICT (username) DO NOTHING;
