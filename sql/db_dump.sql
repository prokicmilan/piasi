--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-02-06 00:04:56

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
-- TOC entry 202 (class 1259 OID 16512)
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
-- TOC entry 203 (class 1259 OID 16518)
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
-- TOC entry 204 (class 1259 OID 16520)
-- Name: ks_korisnik_ks_uloga; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ks_korisnik_ks_uloga (
    korisnik_id bigint NOT NULL,
    uloga_id bigint NOT NULL
);


ALTER TABLE public.ks_korisnik_ks_uloga OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16523)
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
-- TOC entry 206 (class 1259 OID 16529)
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
-- TOC entry 210 (class 1259 OID 16583)
-- Name: questionaire; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionaire (
    id bigint NOT NULL,
    naziv character varying NOT NULL,
    opis character varying NOT NULL,
    datum_od date NOT NULL,
    datum_do date NOT NULL,
    anonymous boolean NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    record_status smallint NOT NULL,
    version smallint NOT NULL
);


ALTER TABLE public.questionaire OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16581)
-- Name: questionaire_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.questionaire ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.questionaire_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 16657)
-- Name: questionaire_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionaire_question (
    id bigint NOT NULL,
    question character varying NOT NULL,
    answers character varying,
    ks_korisnik_id bigint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    record_status smallint NOT NULL,
    version smallint NOT NULL,
    input_type integer
);


ALTER TABLE public.questionaire_question OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16655)
-- Name: questionaire_question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.questionaire_question ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.questionaire_question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 16675)
-- Name: questionaire_questionaire_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionaire_questionaire_question (
    questionaire_id bigint NOT NULL,
    questionaire_question_id bigint NOT NULL
);


ALTER TABLE public.questionaire_questionaire_question OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16620)
-- Name: questionaire_solution; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionaire_solution (
    id bigint NOT NULL,
    korisnik_id bigint NOT NULL,
    questionaire_id bigint NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    record_status smallint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    version integer NOT NULL
);


ALTER TABLE public.questionaire_solution OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16618)
-- Name: questionaire_solution_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.questionaire_solution ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.questionaire_solution_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 207 (class 1259 OID 16531)
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
    version smallint NOT NULL
);


ALTER TABLE public.test OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16537)
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
-- TOC entry 216 (class 1259 OID 16642)
-- Name: test_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test_question (
    id bigint NOT NULL,
    question character varying NOT NULL,
    answers character varying,
    correct_answer character varying NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    record_status smallint NOT NULL,
    version smallint NOT NULL,
    input_type integer
);


ALTER TABLE public.test_question OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16640)
-- Name: test_question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.test_question ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.test_question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 212 (class 1259 OID 16598)
-- Name: test_solution; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test_solution (
    id bigint NOT NULL,
    korisnik_id bigint NOT NULL,
    test_id bigint NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    record_status smallint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    version integer NOT NULL
);


ALTER TABLE public.test_solution OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16596)
-- Name: test_solution_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.test_solution ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.test_solution_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 219 (class 1259 OID 16670)
-- Name: test_test_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test_test_question (
    test_id bigint NOT NULL,
    test_question_id bigint NOT NULL
);


ALTER TABLE public.test_test_question OWNER TO postgres;

--
-- TOC entry 2914 (class 0 OID 16512)
-- Dependencies: 202
-- Data for Name: ks_korisnik; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_korisnik (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, salt, aktivan) FROM stdin;
11	superadmin	46g/t9StJPm9WoHmBJX43novHxmclqR1U6nbWBT7VPHx/m3s/0QY6dW1isD7hylfyo3vyOWU+CsDDrBk6ygKpg==	Super	Admin	superadmin@test.com	2020-01-01	Superadminovo	+38112345678	11	2020-01-25 18:38:46.544401	2020-01-26 21:25:06.680761	1	1	tsaPm9J4pWkfn/Xbvwr50qZ26pegSpxRxrgG0YF/aqo=	t
16	test	wsL8d6bskx5+vPrsoQWbyzXfGLuwhoYC6dYjAOJas545cZMKCRQ/gycF1EAgzOl1lMZKXVwX84VGUDSxLEDcIw==	Test	Test	test@test.com	2020-01-01	Testovo	+38112345678	\N	2020-02-02 14:48:55.727459	2020-02-02 14:48:55.727459	1	0	NaPLHSuyZ5jz10NF/o2MY1/xW4cyaG7W95EWUzYO+lI=	t
17	test1	9lYRp4Q5b98yuDYCbXG87sHFDXaTYK1q6P2wuEdo7mweINXwJ9oA2cbevAWsnU6lSnErKuPoNqY/xQxPexVS6g==	Test	Test	test1@test.com	2020-01-01	Testovo	+381123456789	\N	2020-02-02 22:50:24.047259	2020-02-02 22:50:24.047259	1	0	Za3HMWXAyN9ddwZN80JxZt8oeTHqoSYYEM7QMwJ8WuM=	t
\.


--
-- TOC entry 2916 (class 0 OID 16520)
-- Dependencies: 204
-- Data for Name: ks_korisnik_ks_uloga; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_korisnik_ks_uloga (korisnik_id, uloga_id) FROM stdin;
11	4
11	1
16	4
17	4
\.


--
-- TOC entry 2917 (class 0 OID 16523)
-- Dependencies: 205
-- Data for Name: ks_uloga; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_uloga (id, oznaka, naziv, uloga_tip) FROM stdin;
1	admin	Administrator sistema	1
2	autor	Autor anketa i testova	2
3	ispitanik	Korisnik koji popunjava ankete i resava testove	2
4	user	Osnovna privilegija svih korisnika sistema	0
\.


--
-- TOC entry 2922 (class 0 OID 16583)
-- Dependencies: 210
-- Data for Name: questionaire; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.questionaire (id, naziv, opis, datum_od, datum_do, anonymous, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
2	anketaizm	prva anketa	2020-02-01	2020-02-05	f	11	2020-02-04 21:30:33.214565	2020-02-04 21:47:29.904562	1	3
6	naziv	opis	2020-02-01	2020-02-05	t	11	2020-02-06 00:04:17.880496	2020-02-06 00:04:17.880496	1	0
\.


--
-- TOC entry 2930 (class 0 OID 16657)
-- Dependencies: 218
-- Data for Name: questionaire_question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.questionaire_question (id, question, answers, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, input_type) FROM stdin;
2	pitanje1	odg1, odg2, odg3	11	2020-02-06 00:04:17.885503	2020-02-06 00:04:17.885503	1	0	3
3	pitanje2	\N	11	2020-02-06 00:04:17.893513	2020-02-06 00:04:17.893513	1	0	1
\.


--
-- TOC entry 2932 (class 0 OID 16675)
-- Dependencies: 220
-- Data for Name: questionaire_questionaire_question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.questionaire_questionaire_question (questionaire_id, questionaire_question_id) FROM stdin;
6	2
6	3
\.


--
-- TOC entry 2926 (class 0 OID 16620)
-- Dependencies: 214
-- Data for Name: questionaire_solution; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.questionaire_solution (id, korisnik_id, questionaire_id, ks_korisnik_id, record_status, insert_timestamp, last_update_timestamp, version) FROM stdin;
\.


--
-- TOC entry 2919 (class 0 OID 16531)
-- Dependencies: 207
-- Data for Name: test; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test (id, naziv, opis, datum_od, datum_do, trajanje, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
24	naziv	opis	2020-02-01	2020-02-05	125	11	2020-02-05 23:58:58.886601	2020-02-05 23:58:58.886601	1	0
\.


--
-- TOC entry 2928 (class 0 OID 16642)
-- Dependencies: 216
-- Data for Name: test_question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test_question (id, question, answers, correct_answer, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, input_type) FROM stdin;
12	pitanje1	\N	tacan odgovor	11	2020-02-05 23:58:58.897599	2020-02-05 23:58:58.897599	1	0	1
13	pitanje2	netacan1, netacan2	tacan	11	2020-02-05 23:58:58.903601	2020-02-05 23:58:58.903601	1	0	3
\.


--
-- TOC entry 2924 (class 0 OID 16598)
-- Dependencies: 212
-- Data for Name: test_solution; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test_solution (id, korisnik_id, test_id, ks_korisnik_id, record_status, insert_timestamp, last_update_timestamp, version) FROM stdin;
\.


--
-- TOC entry 2931 (class 0 OID 16670)
-- Dependencies: 219
-- Data for Name: test_test_question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test_test_question (test_id, test_question_id) FROM stdin;
24	12
24	13
\.


--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 203
-- Name: ks_korisnik_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ks_korisnik_id_seq', 17, true);


--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 206
-- Name: ks_uloga_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ks_uloga_id_seq', 4, true);


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 209
-- Name: questionaire_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.questionaire_id_seq', 6, true);


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 217
-- Name: questionaire_question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.questionaire_question_id_seq', 3, true);


--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 213
-- Name: questionaire_solution_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.questionaire_solution_id_seq', 1, false);


--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 208
-- Name: test_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_id_seq', 24, true);


--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 215
-- Name: test_question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_question_id_seq', 13, true);


--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 211
-- Name: test_solution_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_solution_id_seq', 1, false);


--
-- TOC entry 2756 (class 2606 OID 16540)
-- Name: ks_uloga ck_oznaka_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_uloga
    ADD CONSTRAINT ck_oznaka_unique UNIQUE (oznaka);


--
-- TOC entry 2748 (class 2606 OID 16542)
-- Name: ks_korisnik ck_username_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT ck_username_unique UNIQUE (username);


--
-- TOC entry 2758 (class 2606 OID 16544)
-- Name: ks_uloga ks_uloga_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_uloga
    ADD CONSTRAINT ks_uloga_pkey PRIMARY KEY (id);


--
-- TOC entry 2752 (class 2606 OID 16546)
-- Name: ks_korisnik pk_ks_korisnik; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT pk_ks_korisnik PRIMARY KEY (id);


--
-- TOC entry 2754 (class 2606 OID 16548)
-- Name: ks_korisnik_ks_uloga pk_ks_korisnik_ks_uloga; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT pk_ks_korisnik_ks_uloga PRIMARY KEY (korisnik_id, uloga_id);


--
-- TOC entry 2762 (class 2606 OID 16590)
-- Name: questionaire questionaire_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire
    ADD CONSTRAINT questionaire_pkey PRIMARY KEY (id);


--
-- TOC entry 2770 (class 2606 OID 16664)
-- Name: questionaire_question questionaire_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_question
    ADD CONSTRAINT questionaire_question_pkey PRIMARY KEY (id);


--
-- TOC entry 2774 (class 2606 OID 16679)
-- Name: questionaire_questionaire_question questionaire_questionaire_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_questionaire_question
    ADD CONSTRAINT questionaire_questionaire_question_pkey PRIMARY KEY (questionaire_id, questionaire_question_id);


--
-- TOC entry 2766 (class 2606 OID 16624)
-- Name: questionaire_solution questionaire_solution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_pkey PRIMARY KEY (id);


--
-- TOC entry 2760 (class 2606 OID 16550)
-- Name: test test_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_pkey PRIMARY KEY (id);


--
-- TOC entry 2768 (class 2606 OID 16649)
-- Name: test_question test_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_question
    ADD CONSTRAINT test_question_pkey PRIMARY KEY (id);


--
-- TOC entry 2764 (class 2606 OID 16602)
-- Name: test_solution test_solution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_pkey PRIMARY KEY (id);


--
-- TOC entry 2772 (class 2606 OID 16674)
-- Name: test_test_question test_test_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_test_question
    ADD CONSTRAINT test_test_question_pkey PRIMARY KEY (test_id, test_question_id);


--
-- TOC entry 2749 (class 1259 OID 16551)
-- Name: ix_ks_korisnik_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX ix_ks_korisnik_id ON public.ks_korisnik USING btree (id) INCLUDE (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version);

ALTER TABLE public.ks_korisnik CLUSTER ON ix_ks_korisnik_id;


--
-- TOC entry 2750 (class 1259 OID 16552)
-- Name: ix_ks_korisnik_username; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ix_ks_korisnik_username ON public.ks_korisnik USING btree (username) INCLUDE (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version);


--
-- TOC entry 2775 (class 2606 OID 16553)
-- Name: ks_korisnik ks_korisnik_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT ks_korisnik_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2776 (class 2606 OID 16558)
-- Name: ks_korisnik_ks_uloga ks_korisnik_ks_uloga_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2777 (class 2606 OID 16563)
-- Name: ks_korisnik_ks_uloga ks_korisnik_ks_uloga_uloga_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_uloga_id_fkey FOREIGN KEY (uloga_id) REFERENCES public.ks_uloga(id);


--
-- TOC entry 2787 (class 2606 OID 16665)
-- Name: questionaire_question questionaire_question_ks_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_question
    ADD CONSTRAINT questionaire_question_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2783 (class 2606 OID 16625)
-- Name: questionaire_solution questionaire_solution_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2784 (class 2606 OID 16630)
-- Name: questionaire_solution questionaire_solution_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2785 (class 2606 OID 16635)
-- Name: questionaire_solution questionaire_solution_test_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_test_id_fkey FOREIGN KEY (questionaire_id) REFERENCES public.questionaire(id);


--
-- TOC entry 2779 (class 2606 OID 16591)
-- Name: questionaire questioniare_ks_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire
    ADD CONSTRAINT questioniare_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2778 (class 2606 OID 16568)
-- Name: test test_ks_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2786 (class 2606 OID 16650)
-- Name: test_question test_question_ks_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_question
    ADD CONSTRAINT test_question_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2780 (class 2606 OID 16603)
-- Name: test_solution test_solution_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2782 (class 2606 OID 16613)
-- Name: test_solution test_solution_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2781 (class 2606 OID 16608)
-- Name: test_solution test_solution_test_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_test_id_fkey FOREIGN KEY (test_id) REFERENCES public.test(id);


-- Completed on 2020-02-06 00:04:56

--
-- PostgreSQL database dump complete
--

