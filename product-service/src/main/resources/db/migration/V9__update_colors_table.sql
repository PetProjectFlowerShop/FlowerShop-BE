CREATE TABLE product_colors (
    product_id BIGINT NOT NULL,
    color_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, color_id),
    CONSTRAINT fk_products_colors_products FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT fk_products_colors_colors FOREIGN KEY (color_id) REFERENCES colors(id) ON DELETE CASCADE
);
