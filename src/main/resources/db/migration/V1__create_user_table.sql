CREATE TABLE api_user (
    id BIGSERIAL PRIMARY KEY,
    public_id UUID NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    roles VARCHAR(255) NOT NULL,
    CONSTRAINT unique_username UNIQUE (username)
);