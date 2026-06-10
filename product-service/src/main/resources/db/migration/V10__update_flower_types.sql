CREATE TABLE product_flower_types
(
    product_id     BIGINT NOT NULL,
    flower_type_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, flower_type_id),
    CONSTRAINT fk_product_flower_types_product FOREIGN KEY (product_id)
        REFERENCES products (id) ON DELETE CASCADE,
    CONSTRAINT fk_product_flower_types_flower_type FOREIGN KEY (flower_type_id)
        REFERENCES flower_types (id) ON DELETE CASCADE
);

INSERT INTO flower_types (name, care_instructions)
VALUES ('Baby''s Breath', 'Standard care instructions'),
       ('Eucalyptus Cinerea', 'Standard care instructions');
