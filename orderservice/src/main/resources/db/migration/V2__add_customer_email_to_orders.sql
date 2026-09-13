ALTER TABLE orders ADD COLUMN customer_email VARCHAR(255) NOT NULL;
CREATE INDEX customer_email_idx ON orders (customer_email);
