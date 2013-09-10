---
--- CREATE TABLES
---

CREATE TABLE yumak_user (
    id serial PRIMARY KEY,
    username character varying(30) NOT NULL,
    first_name character varying(30) NOT NULL,
    last_name character varying(30) NOT NULL,
    email character varying(75) NOT NULL,
    password character varying(128) NOT NULL,
    is_active boolean NOT NULL,
    date_joined timestamp with time zone NOT NULL 
);


CREATE TABLE yumak_product (
    id serial PRIMARY KEY,
    name character varying(255) NOT NULL,
    slug character varying(255) NOT NULL,
    price numeric(10,4),
    description text DEFAULT ''::text NOT NULL,
    active boolean DEFAULT true NOT NULL,
    create_date timestamp with time zone
);
