CREATE TABLE packaging_types
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(100)   NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE bouquet_packaging
(
    bouquet_type_id   BIGINT NOT NULL REFERENCES bouquet_types (id) ON DELETE CASCADE,
    packaging_type_id BIGINT NOT NULL REFERENCES packaging_types (id) ON DELETE CASCADE,
    PRIMARY KEY (bouquet_type_id, packaging_type_id)
);

INSERT INTO packaging_types (name, price)
VALUES ('Craft Paper Wrap', 0.00),
       ('Luxury Tissue Wrap', 1.00),
       ('Eco Linen Wrap', 1.00),
       ('Gold Foil Wrap', 2.00),
       ('Silk Ribbon Bundle', 2.00),
       ('Round Box', 0.00),
       ('Basket', 0.00);

INSERT INTO bouquet_packaging (packaging_type_id, bouquet_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Monobouquet') AND p.name = ('Craft Paper Wrap');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Mixed Bouquet') AND p.name = ('Craft Paper Wrap');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Monobouquet') AND p.name = ('Luxury Tissue Wrap');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Mixed Bouquet') AND p.name = ('Luxury Tissue Wrap');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Monobouquet') AND p.name = ('Eco Linen Wrap');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Mixed Bouquet') AND p.name = ('Eco Linen Wrap');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Monobouquet') AND p.name = ('Gold Foil Wrap');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Mixed Bouquet') AND p.name = ('Gold Foil Wrap');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Monobouquet') AND p.name = ('Silk Ribbon Bundle');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Mixed Bouquet') AND p.name = ('Silk Ribbon Bundle');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Flowers in a Box') AND p.name = ('Round Box');

INSERT INTO bouquet_packaging (bouquet_type_id, packaging_type_id)
SELECT b.id, p.id FROM bouquet_types b, packaging_types p
WHERE b.name = ('Flowers in a Basket') AND p.name = ('Basket');
