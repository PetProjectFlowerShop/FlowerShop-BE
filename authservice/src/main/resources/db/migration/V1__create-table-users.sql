CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,

    firstname VARCHAR(100) NOT NULL,

    lastname VARCHAR(100),

    email VARCHAR(255) NOT NULL,

    password VARCHAR(128) NOT NULL,

    auth_provider VARCHAR(20) NOT NULL,

    role VARCHAR(20) NOT NULL,

    is_marketing_allowed BOOLEAN NOT NULL,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT uk_users_email UNIQUE (email)
);
