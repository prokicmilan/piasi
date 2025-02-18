--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-02-07 13:15:19

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
-- TOC entry 202 (class 1259 OID 16394)
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
-- TOC entry 203 (class 1259 OID 16400)
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
-- TOC entry 204 (class 1259 OID 16402)
-- Name: ks_korisnik_ks_uloga; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ks_korisnik_ks_uloga (
    korisnik_id bigint NOT NULL,
    uloga_id bigint NOT NULL
);


ALTER TABLE public.ks_korisnik_ks_uloga OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16405)
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
-- TOC entry 206 (class 1259 OID 16411)
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
-- TOC entry 207 (class 1259 OID 16413)
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
-- TOC entry 225 (class 1259 OID 16582)
-- Name: questionaire_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionaire_answer (
    id bigint NOT NULL,
    answer character varying,
    question_id bigint NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    record_status integer NOT NULL,
    version integer NOT NULL
);


ALTER TABLE public.questionaire_answer OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16580)
-- Name: questionaire_answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.questionaire_answer ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.questionaire_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 208 (class 1259 OID 16419)
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
-- TOC entry 209 (class 1259 OID 16421)
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
-- TOC entry 210 (class 1259 OID 16427)
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
-- TOC entry 211 (class 1259 OID 16429)
-- Name: questionaire_questionaire_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionaire_questionaire_question (
    questionaire_id bigint NOT NULL,
    questionaire_question_id bigint NOT NULL
);


ALTER TABLE public.questionaire_questionaire_question OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16432)
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
-- TOC entry 213 (class 1259 OID 16435)
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
-- TOC entry 226 (class 1259 OID 16600)
-- Name: questionaire_solution_questionaire_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionaire_solution_questionaire_answer (
    questionaire_solution_id bigint NOT NULL,
    questionaire_answer_id bigint NOT NULL
);


ALTER TABLE public.questionaire_solution_questionaire_answer OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16437)
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
-- TOC entry 215 (class 1259 OID 16443)
-- Name: test_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test_answer (
    id bigint NOT NULL,
    answer character varying,
    question_id bigint NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    record_status integer NOT NULL,
    version integer NOT NULL
);


ALTER TABLE public.test_answer OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16449)
-- Name: test_answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.test_answer ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.test_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 217 (class 1259 OID 16451)
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
-- TOC entry 218 (class 1259 OID 16453)
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
-- TOC entry 219 (class 1259 OID 16459)
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
-- TOC entry 220 (class 1259 OID 16461)
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
-- TOC entry 221 (class 1259 OID 16464)
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
-- TOC entry 222 (class 1259 OID 16466)
-- Name: test_solution_test_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test_solution_test_answer (
    test_solution_id bigint NOT NULL,
    test_answer_id bigint NOT NULL
);


ALTER TABLE public.test_solution_test_answer OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16469)
-- Name: test_test_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test_test_question (
    test_id bigint NOT NULL,
    test_question_id bigint NOT NULL
);


ALTER TABLE public.test_test_question OWNER TO postgres;

--
-- TOC entry 2948 (class 0 OID 16394)
-- Dependencies: 202
-- Data for Name: ks_korisnik; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_korisnik (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, salt, aktivan) FROM stdin;
11	superadmin	46g/t9StJPm9WoHmBJX43novHxmclqR1U6nbWBT7VPHx/m3s/0QY6dW1isD7hylfyo3vyOWU+CsDDrBk6ygKpg==	Super	Admin	superadmin@test.com	2020-01-01	Superadminovo	+38112345678	11	2020-01-25 18:38:46.544401	2020-01-26 21:25:06.680761	1	1	tsaPm9J4pWkfn/Xbvwr50qZ26pegSpxRxrgG0YF/aqo=	t
27	ispitanik	l8m3KVlsY+DDMkh4/N8TkVy3qMxMG6iJGrtT8iAbcltP5GiEb5KWRxJXE+EDsl0GDygbPgSgiO9oNzDVtXwrBQ==	Ispitanik	Ispitanik	ispitanik@test.com	2020-01-01	Ispitanikovo	+381123456789	11	2020-02-06 12:01:39.180296	2020-02-06 12:01:57.038277	1	1	qy36DRiFyLAzseUlZyGH2cLLKg1d0NSNJUOEcaFo7pM=	t
26	autor	IfqO5qR7eBVGfc7OM0MH04A9D13ybqSPiFJgTVNcsYWsV4MKRSgzUtDJYvG9hLQG6p25ERiCtuRr6FzyvAxvXQ==	Autor	Autor	autor@test.com	2020-01-01	Autorovo	+381123456789	26	2020-02-06 11:59:18.169887	2020-02-06 13:49:33.213865	1	5	15kQ13znfKaKjSZxg3ZqWUSX4lHN4ZQbk3F9WEGh+CU=	t
\.


--
-- TOC entry 2950 (class 0 OID 16402)
-- Dependencies: 204
-- Data for Name: ks_korisnik_ks_uloga; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ks_korisnik_ks_uloga (korisnik_id, uloga_id) FROM stdin;
11	4
11	1
26	2
26	4
27	3
27	4
\.


--
-- TOC entry 2951 (class 0 OID 16405)
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
-- TOC entry 2953 (class 0 OID 16413)
-- Dependencies: 207
-- Data for Name: questionaire; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.questionaire (id, naziv, opis, datum_od, datum_do, anonymous, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
2	anketaizm	prva anketa	2020-02-01	2020-02-05	f	11	2020-02-04 21:30:33.214565	2020-02-04 21:47:29.904562	1	3
6	naziv	opis	2020-02-03	2020-02-05	t	11	2020-02-06 00:04:17.880496	2020-02-06 00:35:41.555917	1	1
7	nova anketa	ovo je najnovija anketa	2020-10-05	2020-10-10	t	26	2020-02-06 16:15:09.061005	2020-02-06 16:15:09.061005	1	0
\.


--
-- TOC entry 2971 (class 0 OID 16582)
-- Dependencies: 225
-- Data for Name: questionaire_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.questionaire_answer (id, answer, question_id, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
3	dljgldksajg	6	27	2020-02-07 10:23:49.043091	2020-02-07 10:23:49.043091	1	0
4	odg3	7	27	2020-02-07 10:23:49.043091	2020-02-07 10:23:49.043091	1	0
\.


--
-- TOC entry 2955 (class 0 OID 16421)
-- Dependencies: 209
-- Data for Name: questionaire_question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.questionaire_question (id, question, answers, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, input_type) FROM stdin;
6	pitanje2	\N	11	2020-02-06 00:35:41.538919	2020-02-06 00:35:41.538919	1	0	2
7	pitanje1	odg1, odg2, odg3	11	2020-02-06 00:35:41.549922	2020-02-06 00:35:41.549922	1	0	3
8	pitanje1	\N	26	2020-02-06 16:15:09.070054	2020-02-06 16:15:09.070054	1	0	1
9	pitanje2	odg1, odg2, odg3	26	2020-02-06 16:15:09.070054	2020-02-06 16:15:09.070054	1	0	4
\.


--
-- TOC entry 2957 (class 0 OID 16429)
-- Dependencies: 211
-- Data for Name: questionaire_questionaire_question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.questionaire_questionaire_question (questionaire_id, questionaire_question_id) FROM stdin;
6	6
6	7
7	8
7	9
\.


--
-- TOC entry 2958 (class 0 OID 16432)
-- Dependencies: 212
-- Data for Name: questionaire_solution; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.questionaire_solution (id, korisnik_id, questionaire_id, ks_korisnik_id, record_status, insert_timestamp, last_update_timestamp, version) FROM stdin;
2	27	6	27	1	2020-02-07 10:23:49.043091	2020-02-07 10:23:49.043091	0
\.


--
-- TOC entry 2972 (class 0 OID 16600)
-- Dependencies: 226
-- Data for Name: questionaire_solution_questionaire_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.questionaire_solution_questionaire_answer (questionaire_solution_id, questionaire_answer_id) FROM stdin;
2	3
2	4
\.


--
-- TOC entry 2960 (class 0 OID 16437)
-- Dependencies: 214
-- Data for Name: test; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test (id, naziv, opis, datum_od, datum_do, trajanje, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
24	izmenjen naziv	opis	2020-02-01	2020-02-05	125	11	2020-02-05 23:58:58.886601	2020-02-06 00:34:44.459977	1	1
25	najnoviji test	ovo je najnoviji test	2020-02-01	2020-02-05	150	26	2020-02-06 16:08:28.172951	2020-02-06 16:08:28.172951	1	0
26	testredirect	ovo je test sa redirekcijom	2020-02-01	2020-02-10	150	26	2020-02-06 16:21:58.968353	2020-02-06 16:21:58.968353	1	0
27	test sa timeoutom	ovo je test sa timeoutom	2020-02-05	2020-02-10	30	26	2020-02-07 11:45:45.543347	2020-02-07 11:45:45.543347	1	0
\.


--
-- TOC entry 2961 (class 0 OID 16443)
-- Dependencies: 215
-- Data for Name: test_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test_answer (id, answer, question_id, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
4	odgovor11	33	11	2020-02-06 01:53:18.297083	2020-02-06 01:53:18.297083	1	0
5	tacan1	34	11	2020-02-06 01:53:18.306084	2020-02-06 01:53:18.306084	1	0
6	odgovor14	32	11	2020-02-06 01:53:18.309083	2020-02-06 01:53:18.309083	1	0
7	odgovor15	31	11	2020-02-06 01:53:18.313083	2020-02-06 01:53:18.313083	1	0
9	\N	33	27	2020-02-06 14:47:22.976607	2020-02-06 14:47:22.976607	1	0
10	\N	31	27	2020-02-06 15:11:11.382488	2020-02-06 15:11:11.382488	1	0
11	\N	33	27	2020-02-06 15:12:05.070523	2020-02-06 15:12:05.070523	1	0
12	\N	32	27	2020-02-06 15:15:26.317965	2020-02-06 15:15:26.317965	1	0
13	\N	34	27	2020-02-06 15:15:26.317965	2020-02-06 15:15:26.317965	1	0
14	\N	33	27	2020-02-06 15:15:26.317965	2020-02-06 15:15:26.317965	1	0
15	\N	31	27	2020-02-06 15:15:26.317965	2020-02-06 15:15:26.317965	1	0
16	\N	31	27	2020-02-06 15:19:04.006803	2020-02-06 15:19:04.006803	1	0
17	\N	32	27	2020-02-06 15:19:04.047556	2020-02-06 15:19:04.047556	1	0
18	\N	34	27	2020-02-06 15:19:04.056779	2020-02-06 15:19:04.056779	1	0
19	\N	33	27	2020-02-06 15:19:04.056779	2020-02-06 15:19:04.056779	1	0
21	\N	33	27	2020-02-06 15:20:49.085972	2020-02-06 15:20:49.085972	1	0
22	\N	31	27	2020-02-06 15:20:49.085972	2020-02-06 15:20:49.085972	1	0
23	\N	32	27	2020-02-06 15:20:49.085972	2020-02-06 15:20:49.085972	1	0
24	\N	34	27	2020-02-06 15:20:49.085972	2020-02-06 15:20:49.085972	1	0
25	tacan1	34	27	2020-02-06 15:20:56.313765	2020-02-06 15:20:56.313765	1	0
26	aslfjkl	32	27	2020-02-06 15:20:56.313765	2020-02-06 15:20:56.313765	1	0
27	kljasflkjsa	33	27	2020-02-06 15:20:56.313765	2020-02-06 15:20:56.313765	1	0
28	lksajflkj	31	27	2020-02-06 15:20:56.313765	2020-02-06 15:20:56.313765	1	0
29	\N	31	27	2020-02-06 15:22:04.295944	2020-02-06 15:22:04.295944	1	0
30	\N	32	27	2020-02-06 15:22:04.295944	2020-02-06 15:22:04.295944	1	0
31	\N	34	27	2020-02-06 15:22:04.295944	2020-02-06 15:22:04.295944	1	0
32	\N	33	27	2020-02-06 15:22:04.295944	2020-02-06 15:22:04.295944	1	0
33	tacan	38	27	2020-02-07 10:55:28.422499	2020-02-07 10:55:28.422499	1	0
34	to	37	27	2020-02-07 10:55:28.427517	2020-02-07 10:55:28.427517	1	0
39	\N	42	27	2020-02-07 11:56:07.45822	2020-02-07 11:56:07.45822	1	0
40	\N	39	27	2020-02-07 11:56:07.461737	2020-02-07 11:56:07.461737	1	0
41	\N	41	27	2020-02-07 11:56:07.461737	2020-02-07 11:56:07.461737	1	0
42	\N	40	27	2020-02-07 11:56:07.461737	2020-02-07 11:56:07.461737	1	0
43	forty two	40	27	2020-02-07 11:59:44.421721	2020-02-07 11:59:44.421721	1	0
44	 nt3,  nt2, nt1	41	27	2020-02-07 11:59:44.421721	2020-02-07 11:59:44.421721	1	0
45	tacan	42	27	2020-02-07 11:59:44.421721	2020-02-07 11:59:44.421721	1	0
46	42	39	27	2020-02-07 11:59:44.421721	2020-02-07 11:59:44.421721	1	0
47	net1	42	27	2020-02-07 12:03:34.323474	2020-02-07 12:03:34.323474	1	0
48	 nt2, t1	41	27	2020-02-07 12:03:34.331506	2020-02-07 12:03:34.331506	1	0
49	forty two	40	27	2020-02-07 12:03:34.331506	2020-02-07 12:03:34.331506	1	0
50	42	39	27	2020-02-07 12:03:34.331506	2020-02-07 12:03:34.331506	1	0
51	\N	42	27	2020-02-07 13:00:23.3274	2020-02-07 13:00:23.3274	1	0
52	\N	39	27	2020-02-07 13:00:23.343048	2020-02-07 13:00:23.343048	1	0
53	\N	40	27	2020-02-07 13:00:23.343048	2020-02-07 13:00:23.343048	1	0
54	\N	41	27	2020-02-07 13:00:23.343048	2020-02-07 13:00:23.343048	1	0
55	\N	40	27	2020-02-07 13:03:49.635495	2020-02-07 13:03:49.635495	1	0
56	\N	41	27	2020-02-07 13:03:49.635495	2020-02-07 13:03:49.635495	1	0
57	\N	39	27	2020-02-07 13:03:49.635495	2020-02-07 13:03:49.635495	1	0
58	\N	42	27	2020-02-07 13:03:49.635495	2020-02-07 13:03:49.635495	1	0
59	forty two	40	27	2020-02-07 13:07:55.073758	2020-02-07 13:07:55.073758	1	0
60	t1	41	27	2020-02-07 13:07:55.089374	2020-02-07 13:07:55.089374	1	0
61	tacan	42	27	2020-02-07 13:07:55.089374	2020-02-07 13:07:55.089374	1	0
62	42	39	27	2020-02-07 13:07:55.089374	2020-02-07 13:07:55.089374	1	0
63	tekst	40	27	2020-02-07 13:09:49.030362	2020-02-07 13:09:49.030362	1	0
64	42	39	27	2020-02-07 13:09:49.030362	2020-02-07 13:09:49.030362	1	0
65	 net2	42	27	2020-02-07 13:09:49.030362	2020-02-07 13:09:49.030362	1	0
66	t1,  nt3	41	27	2020-02-07 13:09:49.030362	2020-02-07 13:09:49.030362	1	0
67	42	39	27	2020-02-07 13:10:42.230451	2020-02-07 13:10:42.230451	1	0
68	 net3	42	27	2020-02-07 13:10:42.230451	2020-02-07 13:10:42.230451	1	0
69	forty two	40	27	2020-02-07 13:10:42.230451	2020-02-07 13:10:42.230451	1	0
70	 nt3, nt1	41	27	2020-02-07 13:10:42.230451	2020-02-07 13:10:42.230451	1	0
71	\N	41	27	2020-02-07 13:11:35.642878	2020-02-07 13:11:35.642878	1	0
72	\N	39	27	2020-02-07 13:11:35.642878	2020-02-07 13:11:35.642878	1	0
73	\N	42	27	2020-02-07 13:11:35.642878	2020-02-07 13:11:35.642878	1	0
74	\N	40	27	2020-02-07 13:11:35.642878	2020-02-07 13:11:35.642878	1	0
75	forty two	40	27	2020-02-07 13:12:32.611404	2020-02-07 13:12:32.611404	1	0
76	nt1,  nt2,  nt3	41	27	2020-02-07 13:12:32.611404	2020-02-07 13:12:32.611404	1	0
77	42	39	27	2020-02-07 13:12:32.611404	2020-02-07 13:12:32.611404	1	0
78	tacan	42	27	2020-02-07 13:12:32.611404	2020-02-07 13:12:32.611404	1	0
\.


--
-- TOC entry 2964 (class 0 OID 16453)
-- Dependencies: 218
-- Data for Name: test_question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test_question (id, question, answers, correct_answer, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, input_type) FROM stdin;
31	pitanje15	\N	tacan odgovor	11	2020-02-06 00:34:44.377974	2020-02-06 00:34:44.377974	1	0	1
32	pitanje14	\N	tacan odgovor	11	2020-02-06 00:34:44.436974	2020-02-06 00:34:44.436974	1	0	1
33	pitanje11	\N	tacan odgovor	11	2020-02-06 00:34:44.443975	2020-02-06 00:34:44.443975	1	0	1
34	pitanje22	netacan1, netacan2	tacan1	11	2020-02-06 00:34:44.448976	2020-02-06 00:34:44.448976	1	0	3
35	pitanje1	\N	ovo je tacan odgovor	26	2020-02-06 16:08:28.186475	2020-02-06 16:08:28.186475	1	0	2
36	pitanje2	nt1, nt2, nt3	t	26	2020-02-06 16:08:28.19254	2020-02-06 16:08:28.19254	1	0	4
37	pitanje1	nt1, nt3, nt5, n9	to	26	2020-02-06 16:21:58.974699	2020-02-06 16:21:58.974699	1	0	4
38	pitanje2	\N	ovo je duzi odgovor	26	2020-02-06 16:21:58.974699	2020-02-06 16:21:58.974699	1	0	2
39	pitanjeBroj?	\N	42	26	2020-02-07 11:45:45.557341	2020-02-07 11:45:45.557341	1	0	0
40	pitanje tekst	\N	forty two	26	2020-02-07 11:45:45.562338	2020-02-07 11:45:45.562338	1	0	1
41	pitanje vise	nt1, nt2, nt3	t1	26	2020-02-07 11:45:45.563338	2020-02-07 11:45:45.563338	1	0	4
42	pitanje jedan	net1, net2, net3	tacan	26	2020-02-07 11:45:45.564337	2020-02-07 11:45:45.564337	1	0	3
\.


--
-- TOC entry 2966 (class 0 OID 16461)
-- Dependencies: 220
-- Data for Name: test_solution; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test_solution (id, korisnik_id, test_id, ks_korisnik_id, record_status, insert_timestamp, last_update_timestamp, version) FROM stdin;
2	11	24	11	1	2020-02-06 01:02:16.073823	2020-02-06 01:02:16.073823	0
7	11	24	11	1	2020-02-06 01:53:18.275088	2020-02-06 01:53:18.275088	0
9	27	24	27	1	2020-02-06 14:47:22.974065	2020-02-06 14:47:22.974065	0
10	27	24	27	1	2020-02-06 15:11:11.368486	2020-02-06 15:11:11.368486	0
11	27	24	27	1	2020-02-06 15:12:05.066522	2020-02-06 15:12:05.066522	0
12	27	24	27	1	2020-02-06 15:15:26.31394	2020-02-06 15:15:26.31394	0
13	27	24	27	1	2020-02-06 15:19:03.886739	2020-02-06 15:19:03.886739	0
15	27	24	27	1	2020-02-06 15:20:49.081676	2020-02-06 15:20:49.081676	0
16	27	24	27	1	2020-02-06 15:20:56.311742	2020-02-06 15:20:56.311742	0
17	27	24	27	1	2020-02-06 15:22:04.291954	2020-02-06 15:22:04.291954	0
18	27	26	27	1	2020-02-07 10:55:28.412415	2020-02-07 10:55:28.412415	0
29	27	27	27	1	2020-02-07 13:12:32.611404	2020-02-07 13:12:32.611404	0
\.


--
-- TOC entry 2968 (class 0 OID 16466)
-- Dependencies: 222
-- Data for Name: test_solution_test_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test_solution_test_answer (test_solution_id, test_answer_id) FROM stdin;
7	4
7	5
7	6
7	7
9	9
10	10
11	11
12	12
12	13
12	14
12	15
13	16
13	17
13	18
13	19
15	21
15	22
15	23
15	24
16	25
16	26
16	27
16	28
17	29
17	30
17	31
17	32
18	33
18	34
19	35
19	36
19	37
19	38
20	39
20	40
20	41
20	42
21	43
21	44
21	45
21	46
22	47
22	48
22	49
22	50
23	51
23	52
23	53
23	54
24	55
24	56
24	57
24	58
25	59
25	60
25	61
25	62
26	63
26	64
26	65
26	66
27	67
27	68
27	69
27	70
28	71
28	72
28	73
28	74
29	75
29	76
29	77
29	78
\.


--
-- TOC entry 2969 (class 0 OID 16469)
-- Dependencies: 223
-- Data for Name: test_test_question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test_test_question (test_id, test_question_id) FROM stdin;
24	31
24	32
24	33
24	34
25	35
25	36
26	37
26	38
27	39
27	40
27	41
27	42
\.


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 203
-- Name: ks_korisnik_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ks_korisnik_id_seq', 27, true);


--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 206
-- Name: ks_uloga_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ks_uloga_id_seq', 4, true);


--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 224
-- Name: questionaire_answer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.questionaire_answer_id_seq', 4, true);


--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 208
-- Name: questionaire_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.questionaire_id_seq', 7, true);


--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 210
-- Name: questionaire_question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.questionaire_question_id_seq', 9, true);


--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 213
-- Name: questionaire_solution_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.questionaire_solution_id_seq', 2, true);


--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 216
-- Name: test_answer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_answer_id_seq', 78, true);


--
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 217
-- Name: test_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_id_seq', 27, true);


--
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 219
-- Name: test_question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_question_id_seq', 42, true);


--
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 221
-- Name: test_solution_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.test_solution_id_seq', 29, true);


--
-- TOC entry 2778 (class 2606 OID 16473)
-- Name: ks_uloga ck_oznaka_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_uloga
    ADD CONSTRAINT ck_oznaka_unique UNIQUE (oznaka);


--
-- TOC entry 2770 (class 2606 OID 16475)
-- Name: ks_korisnik ck_username_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT ck_username_unique UNIQUE (username);


--
-- TOC entry 2780 (class 2606 OID 16477)
-- Name: ks_uloga ks_uloga_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_uloga
    ADD CONSTRAINT ks_uloga_pkey PRIMARY KEY (id);


--
-- TOC entry 2774 (class 2606 OID 16479)
-- Name: ks_korisnik pk_ks_korisnik; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT pk_ks_korisnik PRIMARY KEY (id);


--
-- TOC entry 2776 (class 2606 OID 16481)
-- Name: ks_korisnik_ks_uloga pk_ks_korisnik_ks_uloga; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT pk_ks_korisnik_ks_uloga PRIMARY KEY (korisnik_id, uloga_id);


--
-- TOC entry 2802 (class 2606 OID 16589)
-- Name: questionaire_answer questionaire_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_answer
    ADD CONSTRAINT questionaire_answer_pkey PRIMARY KEY (id);


--
-- TOC entry 2782 (class 2606 OID 16483)
-- Name: questionaire questionaire_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire
    ADD CONSTRAINT questionaire_pkey PRIMARY KEY (id);


--
-- TOC entry 2784 (class 2606 OID 16485)
-- Name: questionaire_question questionaire_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_question
    ADD CONSTRAINT questionaire_question_pkey PRIMARY KEY (id);


--
-- TOC entry 2786 (class 2606 OID 16487)
-- Name: questionaire_questionaire_question questionaire_questionaire_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_questionaire_question
    ADD CONSTRAINT questionaire_questionaire_question_pkey PRIMARY KEY (questionaire_id, questionaire_question_id);


--
-- TOC entry 2788 (class 2606 OID 16489)
-- Name: questionaire_solution questionaire_solution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_pkey PRIMARY KEY (id);


--
-- TOC entry 2804 (class 2606 OID 16604)
-- Name: questionaire_solution_questionaire_answer questionaire_solution_questionaire_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_solution_questionaire_answer
    ADD CONSTRAINT questionaire_solution_questionaire_answer_pkey PRIMARY KEY (questionaire_solution_id, questionaire_answer_id);


--
-- TOC entry 2792 (class 2606 OID 16491)
-- Name: test_answer test_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_answer
    ADD CONSTRAINT test_answer_pkey PRIMARY KEY (id);


--
-- TOC entry 2790 (class 2606 OID 16493)
-- Name: test test_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_pkey PRIMARY KEY (id);


--
-- TOC entry 2794 (class 2606 OID 16495)
-- Name: test_question test_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_question
    ADD CONSTRAINT test_question_pkey PRIMARY KEY (id);


--
-- TOC entry 2796 (class 2606 OID 16497)
-- Name: test_solution test_solution_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_pkey PRIMARY KEY (id);


--
-- TOC entry 2798 (class 2606 OID 16499)
-- Name: test_solution_test_answer test_solution_test_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_solution_test_answer
    ADD CONSTRAINT test_solution_test_answer_pkey PRIMARY KEY (test_solution_id, test_answer_id);


--
-- TOC entry 2800 (class 2606 OID 16501)
-- Name: test_test_question test_test_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_test_question
    ADD CONSTRAINT test_test_question_pkey PRIMARY KEY (test_id, test_question_id);


--
-- TOC entry 2771 (class 1259 OID 16502)
-- Name: ix_ks_korisnik_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX ix_ks_korisnik_id ON public.ks_korisnik USING btree (id) INCLUDE (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version);

ALTER TABLE public.ks_korisnik CLUSTER ON ix_ks_korisnik_id;


--
-- TOC entry 2772 (class 1259 OID 16503)
-- Name: ix_ks_korisnik_username; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX ix_ks_korisnik_username ON public.ks_korisnik USING btree (username) INCLUDE (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version);


--
-- TOC entry 2805 (class 2606 OID 16504)
-- Name: ks_korisnik ks_korisnik_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT ks_korisnik_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2806 (class 2606 OID 16509)
-- Name: ks_korisnik_ks_uloga ks_korisnik_ks_uloga_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2807 (class 2606 OID 16514)
-- Name: ks_korisnik_ks_uloga ks_korisnik_ks_uloga_uloga_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_uloga_id_fkey FOREIGN KEY (uloga_id) REFERENCES public.ks_uloga(id);


--
-- TOC entry 2820 (class 2606 OID 16590)
-- Name: questionaire_answer questionaire_answer_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_answer
    ADD CONSTRAINT questionaire_answer_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2821 (class 2606 OID 16595)
-- Name: questionaire_answer questionaire_answer_questionaire_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_answer
    ADD CONSTRAINT questionaire_answer_questionaire_question_id_fkey FOREIGN KEY (question_id) REFERENCES public.questionaire_question(id);


--
-- TOC entry 2809 (class 2606 OID 16519)
-- Name: questionaire_question questionaire_question_ks_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_question
    ADD CONSTRAINT questionaire_question_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2810 (class 2606 OID 16524)
-- Name: questionaire_solution questionaire_solution_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2811 (class 2606 OID 16529)
-- Name: questionaire_solution questionaire_solution_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2812 (class 2606 OID 16534)
-- Name: questionaire_solution questionaire_solution_test_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_test_id_fkey FOREIGN KEY (questionaire_id) REFERENCES public.questionaire(id);


--
-- TOC entry 2808 (class 2606 OID 16539)
-- Name: questionaire questioniare_ks_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionaire
    ADD CONSTRAINT questioniare_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2814 (class 2606 OID 16544)
-- Name: test_answer test_answer_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_answer
    ADD CONSTRAINT test_answer_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2815 (class 2606 OID 16549)
-- Name: test_answer test_answer_test_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_answer
    ADD CONSTRAINT test_answer_test_question_id_fkey FOREIGN KEY (question_id) REFERENCES public.test_question(id);


--
-- TOC entry 2813 (class 2606 OID 16554)
-- Name: test test_ks_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2816 (class 2606 OID 16559)
-- Name: test_question test_question_ks_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_question
    ADD CONSTRAINT test_question_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2817 (class 2606 OID 16564)
-- Name: test_solution test_solution_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2818 (class 2606 OID 16569)
-- Name: test_solution test_solution_ks_korisnik_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);


--
-- TOC entry 2819 (class 2606 OID 16574)
-- Name: test_solution test_solution_test_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_test_id_fkey FOREIGN KEY (test_id) REFERENCES public.test(id);


-- Completed on 2020-02-07 13:15:19

--
-- PostgreSQL database dump complete
--

