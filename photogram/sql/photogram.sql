--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

-- Started on 2016-11-19 21:24:12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE photogram;
--
-- TOC entry 2156 (class 1262 OID 24652)
-- Name: photogram; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE photogram WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';


ALTER DATABASE photogram OWNER TO postgres;

\connect photogram

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 184 (class 1259 OID 24817)
-- Name: comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE comment (
    author_id integer NOT NULL,
    post_id integer NOT NULL,
    text text NOT NULL
);


ALTER TABLE comment OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 24814)
-- Name: like; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "like" (
    author_id integer NOT NULL,
    post_id integer NOT NULL
);


ALTER TABLE "like" OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 24823)
-- Name: photo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE photo (
    author_id integer NOT NULL,
    file character varying(255) NOT NULL
);


ALTER TABLE photo OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 24803)
-- Name: post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE post (
    author_id integer NOT NULL,
    post_id integer NOT NULL,
    describtion text NOT NULL
);


ALTER TABLE post OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 24801)
-- Name: post_post_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE post_post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE post_post_id_seq OWNER TO postgres;

--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 181
-- Name: post_post_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE post_post_id_seq OWNED BY post.post_id;


--
-- TOC entry 188 (class 1259 OID 24839)
-- Name: subscriber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE subscriber (
    subscirbe_user_id integer NOT NULL
);


ALTER TABLE subscriber OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 24842)
-- Name: subscribtion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE subscribtion (
    subscribtion_user_id integer NOT NULL
);


ALTER TABLE subscribtion OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 24828)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id integer NOT NULL,
    name character varying(8) NOT NULL,
    password character varying(50) NOT NULL,
    age integer NOT NULL,
    email character varying NOT NULL,
    uuid character(50) DEFAULT NULL::bpchar,
    deletedate date
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 24826)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_id_seq OWNER TO postgres;

--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 186
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_id_seq OWNED BY users.id;


--
-- TOC entry 2010 (class 2604 OID 24806)
-- Name: post_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY post ALTER COLUMN post_id SET DEFAULT nextval('post_post_id_seq'::regclass);


--
-- TOC entry 2011 (class 2604 OID 24831)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 2146 (class 0 OID 24817)
-- Dependencies: 184
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2145 (class 0 OID 24814)
-- Dependencies: 183
-- Data for Name: like; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2147 (class 0 OID 24823)
-- Dependencies: 185
-- Data for Name: photo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2144 (class 0 OID 24803)
-- Dependencies: 182
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 181
-- Name: post_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('post_post_id_seq', 1, false);


--
-- TOC entry 2150 (class 0 OID 24839)
-- Dependencies: 188
-- Data for Name: subscriber; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2151 (class 0 OID 24842)
-- Dependencies: 189
-- Data for Name: subscribtion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 186
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 6, true);


--
-- TOC entry 2149 (class 0 OID 24828)
-- Dependencies: 187
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users (id, name, password, age, email, uuid, deletedate) VALUES (5, 'login', '1a1dc91c907325c69271ddf0c944bc72', 19, 'mail@mail.ru', 'e035a0d4-c283-494e-94a1-ab354797ab3a              ', '2016-11-23');
INSERT INTO users (id, name, password, age, email, uuid, deletedate) VALUES (6, 'lo2', 'c1572d05424d0ecb2a65ec6a82aeacbf', 23, 'pochta@mail.ru', '23ab04c0-2160-490b-9b10-80dc64013ced              ', '2016-11-23');


--
-- TOC entry 2014 (class 2606 OID 24813)
-- Name: post_author_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY post
    ADD CONSTRAINT post_author_id_key UNIQUE (author_id);


--
-- TOC entry 2016 (class 2606 OID 24811)
-- Name: post_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY post
    ADD CONSTRAINT post_pk PRIMARY KEY (post_id);


--
-- TOC entry 2018 (class 2606 OID 24901)
-- Name: user_password_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_password_key UNIQUE (password);


--
-- TOC entry 2020 (class 2606 OID 24836)
-- Name: user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- TOC entry 2024 (class 2606 OID 24860)
-- Name: comment_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comment
    ADD CONSTRAINT comment_fk0 FOREIGN KEY (author_id) REFERENCES users(id);


--
-- TOC entry 2025 (class 2606 OID 24865)
-- Name: comment_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comment
    ADD CONSTRAINT comment_fk1 FOREIGN KEY (post_id) REFERENCES post(post_id);


--
-- TOC entry 2022 (class 2606 OID 24850)
-- Name: like_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "like"
    ADD CONSTRAINT like_fk0 FOREIGN KEY (author_id) REFERENCES users(id);


--
-- TOC entry 2023 (class 2606 OID 24855)
-- Name: like_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "like"
    ADD CONSTRAINT like_fk1 FOREIGN KEY (post_id) REFERENCES post(post_id);


--
-- TOC entry 2026 (class 2606 OID 24870)
-- Name: photo_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photo
    ADD CONSTRAINT photo_fk0 FOREIGN KEY (author_id) REFERENCES post(author_id);


--
-- TOC entry 2021 (class 2606 OID 24845)
-- Name: post_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY post
    ADD CONSTRAINT post_fk0 FOREIGN KEY (author_id) REFERENCES users(id);


--
-- TOC entry 2027 (class 2606 OID 24875)
-- Name: subscriber_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subscriber
    ADD CONSTRAINT subscriber_fk0 FOREIGN KEY (subscirbe_user_id) REFERENCES users(id);


--
-- TOC entry 2028 (class 2606 OID 24880)
-- Name: subscribtion_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subscribtion
    ADD CONSTRAINT subscribtion_fk0 FOREIGN KEY (subscribtion_user_id) REFERENCES users(id);


--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-11-19 21:24:14

--
-- PostgreSQL database dump complete
--

