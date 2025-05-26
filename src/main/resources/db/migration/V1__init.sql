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