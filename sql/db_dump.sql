--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-02-02 19:04:35

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16399)
-- Name: ks_korisnik; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ks_korisnik (
    id bigint NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    ime character varying NOT NULL,
    prezime character varying NOT NULL,
    email character varying NOT NULL,
    datum_rodjenja date NOT NULL,
    mesto_rodjenja character varying NOT NULL,
    telefon character varying NOT NULL,
    ks_korisnik_id bigint,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    record_status smallint NOT NULL,
    version integer NOT NULL,
    salt character varying NOT NULL,
    aktivan boolean NOT NULL
);


ALTER TABLE public.ks_korisnik OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16448)
-- Name: ks_korisnik_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ks_korisnik ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ks_korisnik_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 204 (class 1259 OID 16423)
-- Name: ks_korisnik_ks_uloga; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ks_korisnik_ks_uloga (
    korisnik_id bigint NOT NULL,
    uloga_id bigint NOT NULL
);


ALTER TABLE public.ks_korisnik_ks_uloga OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16411)
-- Name: ks_uloga; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ks_uloga (
    id bigint NOT NULL,
    oznaka character varying NOT NULL,
    naziv character varying NOT NULL,
    uloga_tip smallint
);


ALTER TABLE public.ks_uloga OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16452)
-- Name: ks_uloga_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ks_uloga ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ks_uloga_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 207 (class 1259 OID 16456)
-- Name: test; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test (
    id bigint NOT NULL,
    naziv character varying NOT NULL,
    opis character varying NOT NULL,
    datum_od date NOT NULL,
    datum_do date NOT NULL,
    trajanje integer NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    record_status smallint NOT NULL,
    version smallint NOT NULL,
    questions_data character varying NOT NULL
);


ALTER TABLE public.test OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16469)
-- Name: test_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.test ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.test_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2850 (class 0 OID 16399)
-- Dependencies: 202
-- Data for Name: ks_korisnik; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_korisnik (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, salt, aktivan) FROM stdin;
11	superadmin	46g/t9StJPm9WoHmBJX43novHxmclqR1U6nbWBT7VPHx/m3s/0QY6dW1isD7hylfyo3vyOWU+CsDDrBk6ygKpg==	Super	Admin	superadmin@test.com	2020-01-01	Superadminovo	+38112345678	11	2020-01-25 18:38:46.544401	2020-01-26 21:25:06.680761	1	1	tsaPm9J4pWkfn/Xbvwr50qZ26pegSpxRxrgG0YF/aqo=	f
16	test	wsL8d6bskx5+vPrsoQWbyzXfGLuwhoYC6dYjAOJas545cZMKCRQ/gycF1EAgzOl1lMZKXVwX84VGUDSxLEDcIw==	Test	Test	test@test.com	2020-01-01	Testovo	+38112345678	\N	2020-02-02 14:48:55.727459	2020-02-02 14:48:55.727459	1	0	NaPLHSuyZ5jz10NF/o2MY1/xW4cyaG7W95EWUzYO+lI=	f
\.


--
-- TOC entry 2852 (class 0 OID 16423)
-- Dependencies: 204
-- Data for Name: ks_korisnik_ks_uloga; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_korisnik_ks_uloga (korisnik_id, uloga_id) FROM stdin;
11	4
11	1
16	4
\.


--
-- TOC entry 2851 (class 0 OID 16411)
-- Dependencies: 203
-- Data for Name: ks_uloga; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_uloga (id, oznaka, naziv, uloga_tip) FROM stdin;
1	admin	Administrator sistema	1
2	autor	Autor anketa i testova	2
3	ispitanik	Korisnik koji popunjava ankete i resava testove	2
4	user	Osnovna privilegija svih korisnika sistema	0
\.


--
-- TOC entry 2855 (class 0 OID 16456)
-- Dependencies: 207
-- Data for Name: test; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test (id, naziv, opis, datum_od, datum_do, trajanje, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, questions_data) FROM stdin;
1	prvi test	ovo je prvi kreirani test	2020-02-01	2020-02-10	120	11	2020-02-02 15:20:28.744733	2020-02-02 15:20:28.744733	1	0	[{"question":"prvo pitanje","type":3,"correctAnswer":"ovo je tacan odgovor za pitanje sa duzim tekstom"},{"question":"drugo pitanje","type":4,"correctAnswer":"tacan","answers":["netacan1","netacan2","netacan3","netacan4"]},{"question":"trece pitanje","type":1,"correctAnswer":"45"}]
2	drugi test	test nakon refaktorizacije	2020-02-01	2020-02-02	130	11	2020-02-02 17:47:04.227806	2020-02-02 17:47:04.227806	1	0	[{"question":"pitanje1","type":4,"correctAnswer":"tacan","answers":["netacan1","netacan2","netacan3","netacan4"]}]
\.


--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 205
-- Name: ks_korisnik_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ks_korisnik_id_seq', 16, true);


--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 206
-- Name: ks_uloga_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ks_uloga_id_seq', 4, true);


--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 208
-- Name: test_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_id_seq', 2, true);


--
-- TOC entry 2713 (class 2606 OID 16420)
-- Name: ks_uloga ck_oznaka_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_uloga
    ADD CONSTRAINT ck_oznaka_unique UNIQUE (oznaka);


--
-- TOC entry 2707 (class 2606 OID 16408)
-- Name: ks_korisnik ck_username_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT ck_username_unique UNIQUE (username);


--
-- TOC entry 2715 (class 2606 OID 16418)
-- Name: ks_uloga ks_uloga_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_uloga
    ADD CONSTRAINT ks_uloga_pkey PRIMARY KEY (id);


--
-- TOC entry 2711 (class 2606 OID 16406)
-- Name: ks_korisnik pk_ks_korisnik; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT pk_ks_korisnik PRIMARY KEY (id);


--
-- TOC entry 2717 (class 2606 OID 16455)
-- Name: ks_korisnik_ks_uloga pk_ks_korisnik_ks_uloga; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT pk_ks_korisnik_ks_uloga PRIMARY KEY (korisnik_id, uloga_id);


--
-- TOC entry 2719 (class 2606 OID 16463)
-- Name: test test_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_pkey PRIMARY KEY (id);


--
-- TOC entry 2708 (class 1259 OID 16409)
-- Name: ix_ks_korisnik_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX ix_ks_korisnik_id ON public.ks_korisnik USING btree (id) INCLUDE (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version);

ALTER TABLE public.ks_korisnik CLUSTER ON ix_ks_korisnik_id;


--
-- TOC entry 2709 (class 1259 OID 16410)
-- Name: ix_ks_korisnik_username; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ix_ks_korisnik_username ON public.ks_korisnik USING btree (username) INCLUDE (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version);


--
-- TOC entry 2720 (class 2606 OID 16443)
-- Name: ks_korisnik ks_korisnik_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT ks_korisnik_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2721 (class 2606 OID 16428)
-- Name: ks_korisnik_ks_uloga ks_korisnik_ks_uloga_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2722 (class 2606 OID 16433)
-- Name: ks_korisnik_ks_uloga ks_korisnik_ks_uloga_uloga_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_uloga_id_fkey FOREIGN KEY (uloga_id) REFERENCES public.ks_uloga(id);


--
-- TOC entry 2723 (class 2606 OID 16464)
-- Name: test test_ks_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


-- Completed on 2020-02-02 19:04:35

--
-- PostgreSQL database dump complete
--

