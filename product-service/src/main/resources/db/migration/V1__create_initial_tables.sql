CREATE TABLE flower_types
(
    id                BIGSERIAL PRIMARY KEY,
    name              VARCHAR(100) NOT NULL UNIQUE,
    care_instructions TEXT
);

CREATE TABLE colors
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(50) NOT NULL UNIQUE,
    hex_code VARCHAR(7)
);

CREATE TABLE bouquet_types
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE products
(
    id               BIGSERIAL PRIMARY KEY,
    name             VARCHAR(255)   NOT NULL,
    description      TEXT,
    price            DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    quantity         INTEGER        NOT NULL DEFAULT 0,
    stems_count      INTEGER                 DEFAULT 1,
    is_available     BOOLEAN                 DEFAULT true,
    flower_type_id   BIGINT REFERENCES flower_types (id),
    color_id         BIGINT REFERENCES colors (id),
    bouquet_type_id  BIGINT REFERENCES bouquet_types (id),
    height           INTEGER,
    discount_percent INTEGER                 DEFAULT 0,
    is_new           BOOLEAN                 DEFAULT false,
    is_popular       BOOLEAN                 DEFAULT false,
    is_season_offer  BOOLEAN                 DEFAULT false,
    is_recommended   BOOLEAN                 DEFAULT false,
    created_at       TIMESTAMP               DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product_images
(
    id         BIGSERIAL PRIMARY KEY,
    product_id BIGINT REFERENCES products (id) ON DELETE CASCADE,
    image_url  VARCHAR(500) NOT NULL,
    is_main    BOOLEAN DEFAULT false
);

CREATE TABLE occasions
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE product_occasions
(
    product_id  BIGINT REFERENCES products (id) ON DELETE CASCADE,
    occasion_id BIGINT REFERENCES occasions (id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, occasion_id)
);

CREATE TABLE accessories
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(255)   NOT NULL,
    price     DECIMAL(10, 2) NOT NULL,
    image_url VARCHAR(500)
);

CREATE TABLE wrapping_options
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    extra_price DECIMAL(10, 2) DEFAULT 0.00

);
