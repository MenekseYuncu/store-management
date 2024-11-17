create table if not exists sm_customer
(
    id         bigserial
    constraint pk__sm_customer__id primary key,
    name       varchar(250)                     not null,
    email      varchar(200)
    constraint u__sm_customer__email unique not null,
    address    varchar(300)                     not null,
    created_at timestamp(0)                     not null,
    updated_at timestamp(0),
    deleted_at timestamp(0)
    );

create table if not exists sm_product
(
    id             bigserial
    constraint pk__sm_product__id primary key,
    name           varchar(300)   not null,
    price          numeric(50, 3) not null,
    stock_quantity bigserial      not null,
    created_at     timestamp(0)   not null,
    updated_at     timestamp(0),
    deleted_at     timestamp(0)
    );

create table if not exists sm_cart
(
    id          bigserial
    constraint pk__sm_cart__id primary key,
    customer_id bigserial
    constraint fk__sm_cart__customer__id references sm_customer,
    total_price numeric(50, 3) not null,
    created_at  timestamp(0)   not null,
    updated_at  timestamp(0),
    deleted_at  timestamp(0)

    );

create table if not exists sm_cart_item
(
    id         bigserial
    constraint pk__sm_cart_item__id primary key,
    cart_id    bigint
    constraint fk__sm_cart_item__cart__id references sm_cart,
    product_id bigint
    constraint fk__sm_cart_item__product__id references sm_product,
    quantity   bigserial    not null,
    created_at timestamp(0) not null,
    updated_at timestamp(0),
    deleted_at timestamp(0)
    );

create table if not exists sm_order
(
    id          bigserial
    constraint pk__sm_order__id primary key,
    customer_id bigint
    constraint fk__sm_order__customer__id references sm_customer,
    total_price numeric(50, 3) not null,
    created_at  timestamp(0)   not null,
    updated_at  timestamp(0),
    deleted_at  timestamp(0)
    );

create table if not exists sm_order_item
(
    id         bigserial
    constraint pk__sm_order_item__id primary key,
    order_id   bigint
    constraint fk__sm_order_item__order__id references sm_order,
    product_id bigint
    constraint fk__sm_order_item__product__id references sm_product,
    quantity   bigserial      not null,
    unit_price numeric(50, 3) not null,
    created_at timestamp(0)   not null,
    updated_at timestamp(0),
    deleted_at timestamp(0)

    );

create table if not exists sm_product_price_history
(
    id         bigserial
    constraint pk__sm_product_price_history__id primary key,
    product_id bigint
    constraint fk__sm_product_price_history__product__id references sm_product,
    old_price  numeric(50, 3) not null,
    new_price  numeric(50, 3) not null,
    updated_at timestamp(0)
    );

insert into sm_customer (id, name, email, address, created_at, updated_at)
values
    (default, 'Alice Smith', 'alice.smith@example.com', '123 Maple Street', CURRENT_TIMESTAMP, null),
    (default, 'Bob Johnson', 'bob.johnson@example.com', '456 Oak Avenue', CURRENT_TIMESTAMP, null),
    (default, 'Charlie Brown', 'charlie.brown@example.com', '789 Pine Road', CURRENT_TIMESTAMP, null);

insert into sm_product (id, name, price, stock_quantity, created_at, updated_at)
values
    (default, 'Wireless Mouse', 25.499, 150, CURRENT_TIMESTAMP, null),
    (default, 'Mechanical Keyboard', 75.999, 100, CURRENT_TIMESTAMP, null),
    (default, 'Gaming Headset', 49.999, 200, CURRENT_TIMESTAMP, null);

insert into sm_cart (id, customer_id, total_price, created_at, updated_at)
values
    (default, 1, 0.0, CURRENT_TIMESTAMP, null),
    (default, 2, 0.0, CURRENT_TIMESTAMP, null),
    (default, 3, 0.0, CURRENT_TIMESTAMP, null);

insert into sm_cart_item (id, cart_id, product_id, quantity, created_at, updated_at)
values
    (default, 1, 1, 2, CURRENT_TIMESTAMP, null),
    (default, 1, 2, 1, CURRENT_TIMESTAMP, null),
    (default, 2, 3, 1, CURRENT_TIMESTAMP, null),
    (default, 3, 1, 3, CURRENT_TIMESTAMP, null);

insert into sm_order (id, customer_id, total_price, created_at, updated_at)
values
    (default, 1, 126.997, CURRENT_TIMESTAMP, null),
    (default, 2, 49.999, CURRENT_TIMESTAMP, null);

insert into sm_order_item (id, order_id, product_id, quantity, unit_price, created_at, updated_at)
values
    (default, 1, 1, 2, 25.499, CURRENT_TIMESTAMP, null),
    (default, 1, 2, 1, 75.999, CURRENT_TIMESTAMP, null),
    (default, 2, 3, 1, 49.999, CURRENT_TIMESTAMP, null);

insert into sm_product_price_history (id, product_id, old_price, new_price, updated_at)
values
    (default, 1, 20.499, 25.499, CURRENT_TIMESTAMP),
    (default, 2, 70.999, 75.999, CURRENT_TIMESTAMP),
    (default, 3, 45.999, 49.999, CURRENT_TIMESTAMP);
