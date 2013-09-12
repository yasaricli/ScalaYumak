---
--- CREATE TABLES
---

CREATE SEQUENCE yumak_user_id_seq;

CREATE TABLE yumak_user (
    id INTEGER NOT NULL default nextval('yumak_user_id_seq'),
    username character varying(30) NOT NULL,
    first_name character varying(30) NOT NULL,
    last_name character varying(30) NOT NULL,
    email character varying(75) NOT NULL,
    password character varying(128) NOT NULL,
    is_active boolean DEFAULT true NOT NULL,
    date_joined timestamp with time zone NOT NULL 
);

ALTER SEQUENCE yumak_user_id_seq owned by yumak_user.id;


CREATE TABLE yumak_product (
    id serial PRIMARY KEY,
    name character varying(255) NOT NULL,
    slug character varying(255) NOT NULL,
    price numeric(10,4),
    description text DEFAULT ''::text NOT NULL,
    active boolean DEFAULT true NOT NULL,
    create_date timestamp with time zone
);
