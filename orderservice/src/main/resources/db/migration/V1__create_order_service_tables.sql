CREATE TYPE order_status AS ENUM (
    'PENDING',
    'PAID',
    'PROCESSING',
    'DELIVERING',
    'COMPLETED',
    'CANCELLED'
);

CREATE TYPE delivery_type AS ENUM (
    'COURIER',
    'PICKUP'
);

CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        user_id BIGINT,
                        order_status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
                        customer_name VARCHAR(255) NOT NULL,
                        customer_phone VARCHAR(50) NOT NULL,
                        dont_call BOOLEAN DEFAULT FALSE,
                        is_another_recipient BOOLEAN DEFAULT FALSE,
                        recipient_name VARCHAR(255),
                        recipient_phone VARCHAR(50),
                        has_greeting_card BOOLEAN DEFAULT FALSE,
                        greeting_card_text TEXT,
                        promo_code VARCHAR(50),
                        subtotal_amount DECIMAL(10, 2) NOT NULL,
                        discount_amount DECIMAL(10, 2) DEFAULT 0.00,
                        shipping_amount DECIMAL(10, 2) DEFAULT 0.00,
                        total_amount DECIMAL(10, 2) NOT NULL,
                        created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE deliveries (
                            id BIGSERIAL PRIMARY KEY,
                            order_id BIGINT NOT NULL UNIQUE,
                            delivery_type VARCHAR(50) NOT NULL,
                            city VARCHAR(255),
                            street VARCHAR(255),
                            building_number VARCHAR(50),
                            entrance_number VARCHAR(50),
                            apartment_number VARCHAR(50),
                            pickup_store_address VARCHAR(255),
                            delivery_date DATE NOT NULL,
                            delivery_time_slot VARCHAR(50) NOT NULL,
                            delivery_comment TEXT,

                            CONSTRAINT fk_deliveries_orders
                                FOREIGN KEY (order_id)
                                    REFERENCES orders (id)
                                    ON DELETE CASCADE
);

CREATE TABLE order_items (
                             id BIGSERIAL PRIMARY KEY,
                             order_id BIGINT NOT NULL,
                             product_id BIGINT NOT NULL,
                             wrapping_id BIGINT,
                             unit_price DECIMAL(10, 2) NOT NULL,
                             quantity INT NOT NULL,
                             total_price DECIMAL(10, 2) NOT NULL,

                             CONSTRAINT fk_order_items_orders
                                 FOREIGN KEY (order_id)
                                     REFERENCES orders (id)
                                     ON DELETE CASCADE
);

CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_orders_status ON orders(order_status);
CREATE INDEX idx_order_items_order_id ON order_items(order_id);
