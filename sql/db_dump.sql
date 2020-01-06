--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-01-06 19:53:45

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
    salt character varying
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
    id bigint NOT NULL,
    korisnik_id bigint NOT NULL,
    uloga_id bigint NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    record_status smallint NOT NULL,
    version integer NOT NULL
);


ALTER TABLE public.ks_korisnik_ks_uloga OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16450)
-- Name: ks_korisnik_ks_uloga_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ks_korisnik_ks_uloga ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ks_korisnik_ks_uloga_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 203 (class 1259 OID 16411)
-- Name: ks_uloga; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ks_uloga (
    id bigint NOT NULL,
    oznaka character varying NOT NULL,
    naziv character varying NOT NULL,
    privileged boolean NOT NULL
);


ALTER TABLE public.ks_uloga OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16452)
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
-- TOC entry 2845 (class 0 OID 16399)
-- Dependencies: 202
-- Data for Name: ks_korisnik; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_korisnik (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, salt) FROM stdin;
1	superadmin	etf123	Super	Admin	superadmin@test.com	1900-01-01	Beograd	+3811234567	1	2020-01-04 15:53:04.882757	2020-01-04 15:53:04.882757	1	1	\N
4	superadmin1	etf.123	Novi	Admin	noviadmin@test.com	2020-01-01	Novoadminovo	+381123456789	\N	2020-01-06 18:16:23.663212	2020-01-06 18:16:23.663212	1	0	\N
5	superadmin2	etf.123	Super	Admin	superadmin2@test.com	2020-01-01	Superadminovo	+381123456789	\N	2020-01-06 18:19:17.861175	2020-01-06 18:19:17.861175	1	0	\N
6	sa	328ZX6NhtO6pBAynpfDFnbHF3Z8BteDrz/LA/3+5Y/PsHWZ/lz6sjX7n9c7oxkgXMvaNUvueKbQS9CGS5BdvQA==	Super	Admin	sa@test.com	2020-01-01	Superadminovo	+381123456789	\N	2020-01-06 19:19:21.612298	2020-01-06 19:19:21.612298	1	0	ZhI3OG1wqfyXwUJiSaByQn1p77Z5y2PKrniqIH+TrOA=
7	sa1	n0b8UyDglDSOMJR/3oQyxEa8A47vJ9+8bX2HyImKs05BqCp8iqxv39DUB2qVoj/0Nd6FXgzefEgR74YAbegUFQ==	Super	Admin	sa1@test.com	2020-01-01	Superadminovo	+381123456789	\N	2020-01-06 19:43:07.068829	2020-01-06 19:43:07.068829	1	0	c/38d4PcRilP/vXzeNGgw5+XlgGY2WiLI3bsciYiCJ0=
\.


--
-- TOC entry 2847 (class 0 OID 16423)
-- Dependencies: 204
-- Data for Name: ks_korisnik_ks_uloga; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_korisnik_ks_uloga (id, korisnik_id, uloga_id, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
\.


--
-- TOC entry 2846 (class 0 OID 16411)
-- Dependencies: 203
-- Data for Name: ks_uloga; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_uloga (id, oznaka, naziv, privileged) FROM stdin;
1	admin	Administrator sistema	t
2	autor	Autor anketa i testova	f
3	ispitanik	Korisnik koji popunjava ankete i resava testove	f
\.


--
-- TOC entry 2856 (class 0 OID 0)
-- Dependencies: 205
-- Name: ks_korisnik_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ks_korisnik_id_seq', 7, true);


--
-- TOC entry 2857 (class 0 OID 0)
-- Dependencies: 206
-- Name: ks_korisnik_ks_uloga_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ks_korisnik_ks_uloga_id_seq', 1, false);


--
-- TOC entry 2858 (class 0 OID 0)
-- Dependencies: 207
-- Name: ks_uloga_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ks_uloga_id_seq', 3, true);


--
-- TOC entry 2708 (class 2606 OID 16420)
-- Name: ks_uloga ck_oznaka_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_uloga
    ADD CONSTRAINT ck_oznaka_unique UNIQUE (oznaka);


--
-- TOC entry 2702 (class 2606 OID 16408)
-- Name: ks_korisnik ck_username_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT ck_username_unique UNIQUE (username);


--
-- TOC entry 2714 (class 2606 OID 16427)
-- Name: ks_korisnik_ks_uloga ks_korisnik_ks_uloga_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_pkey PRIMARY KEY (id);


--
-- TOC entry 2712 (class 2606 OID 16418)
-- Name: ks_uloga ks_uloga_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_uloga
    ADD CONSTRAINT ks_uloga_pkey PRIMARY KEY (id);


--
-- TOC entry 2706 (class 2606 OID 16406)
-- Name: ks_korisnik pk_ks_korisnik; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT pk_ks_korisnik PRIMARY KEY (id);


--
-- TOC entry 2703 (class 1259 OID 16409)
-- Name: ix_ks_korisnik_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX ix_ks_korisnik_id ON public.ks_korisnik USING btree (id) INCLUDE (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version);

ALTER TABLE public.ks_korisnik CLUSTER ON ix_ks_korisnik_id;


--
-- TOC entry 2704 (class 1259 OID 16410)
-- Name: ix_ks_korisnik_username; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ix_ks_korisnik_username ON public.ks_korisnik USING btree (username) INCLUDE (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version);


--
-- TOC entry 2709 (class 1259 OID 16421)
-- Name: ix_ks_uloga_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ix_ks_uloga_id ON public.ks_uloga USING btree (id) INCLUDE (id, oznaka, naziv, privileged);

ALTER TABLE public.ks_uloga CLUSTER ON ix_ks_uloga_id;


--
-- TOC entry 2710 (class 1259 OID 16422)
-- Name: ix_ks_uloga_oznaka; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ix_ks_uloga_oznaka ON public.ks_uloga USING btree (oznaka) INCLUDE (id, oznaka, naziv, privileged);


--
-- TOC entry 2715 (class 2606 OID 16443)
-- Name: ks_korisnik ks_korisnik_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT ks_korisnik_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2716 (class 2606 OID 16428)
-- Name: ks_korisnik_ks_uloga ks_korisnik_ks_uloga_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2718 (class 2606 OID 16438)
-- Name: ks_korisnik_ks_uloga ks_korisnik_ks_uloga_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2717 (class 2606 OID 16433)
-- Name: ks_korisnik_ks_uloga ks_korisnik_ks_uloga_uloga_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_uloga_id_fkey FOREIGN KEY (uloga_id) REFERENCES public.ks_uloga(id);


-- Completed on 2020-01-06 19:53:45

--
-- PostgreSQL database dump complete
--

