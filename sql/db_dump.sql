PGDMP     !    .                x            piasi    12.1    12.1 Z    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16706    piasi    DATABASE     �   CREATE DATABASE piasi WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE piasi;
                postgres    false            �            1259    16707    ks_korisnik    TABLE     �  CREATE TABLE public.ks_korisnik (
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
    DROP TABLE public.ks_korisnik;
       public         heap    postgres    false            �            1259    16713    ks_korisnik_id_seq    SEQUENCE     �   ALTER TABLE public.ks_korisnik ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ks_korisnik_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    202            �            1259    16715    ks_korisnik_ks_uloga    TABLE     l   CREATE TABLE public.ks_korisnik_ks_uloga (
    korisnik_id bigint NOT NULL,
    uloga_id bigint NOT NULL
);
 (   DROP TABLE public.ks_korisnik_ks_uloga;
       public         heap    postgres    false            �            1259    16718    ks_uloga    TABLE     �   CREATE TABLE public.ks_uloga (
    id bigint NOT NULL,
    oznaka character varying NOT NULL,
    naziv character varying NOT NULL,
    uloga_tip smallint
);
    DROP TABLE public.ks_uloga;
       public         heap    postgres    false            �            1259    16724    ks_uloga_id_seq    SEQUENCE     �   ALTER TABLE public.ks_uloga ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ks_uloga_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    205            �            1259    16726    questionaire    TABLE     �  CREATE TABLE public.questionaire (
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
     DROP TABLE public.questionaire;
       public         heap    postgres    false            �            1259    16900    questionaire_answer    TABLE     d  CREATE TABLE public.questionaire_answer (
    id bigint NOT NULL,
    answer character varying,
    question_id bigint NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    record_status integer NOT NULL,
    version integer NOT NULL
);
 '   DROP TABLE public.questionaire_answer;
       public         heap    postgres    false            �            1259    16898    questionaire_answer_id_seq    SEQUENCE     �   ALTER TABLE public.questionaire_answer ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.questionaire_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    226            �            1259    16732    questionaire_id_seq    SEQUENCE     �   ALTER TABLE public.questionaire ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.questionaire_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    207            �            1259    16734    questionaire_question    TABLE     �  CREATE TABLE public.questionaire_question (
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
 )   DROP TABLE public.questionaire_question;
       public         heap    postgres    false            �            1259    16740    questionaire_question_id_seq    SEQUENCE     �   ALTER TABLE public.questionaire_question ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.questionaire_question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    209            �            1259    16742 "   questionaire_questionaire_question    TABLE     �   CREATE TABLE public.questionaire_questionaire_question (
    questionaire_id bigint NOT NULL,
    questionaire_question_id bigint NOT NULL
);
 6   DROP TABLE public.questionaire_questionaire_question;
       public         heap    postgres    false            �            1259    16745    questionaire_solution    TABLE     n  CREATE TABLE public.questionaire_solution (
    id bigint NOT NULL,
    korisnik_id bigint NOT NULL,
    questionaire_id bigint NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    record_status smallint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    version integer NOT NULL
);
 )   DROP TABLE public.questionaire_solution;
       public         heap    postgres    false            �            1259    16748    questionaire_solution_id_seq    SEQUENCE     �   ALTER TABLE public.questionaire_solution ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.questionaire_solution_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    212            �            1259    16893 )   questionaire_solution_questionaire_answer    TABLE     �   CREATE TABLE public.questionaire_solution_questionaire_answer (
    questionaire_solution_id bigint NOT NULL,
    questionaire_answer_id bigint NOT NULL
);
 =   DROP TABLE public.questionaire_solution_questionaire_answer;
       public         heap    postgres    false            �            1259    16750    test    TABLE     �  CREATE TABLE public.test (
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
    DROP TABLE public.test;
       public         heap    postgres    false            �            1259    16756    test_answer    TABLE     \  CREATE TABLE public.test_answer (
    id bigint NOT NULL,
    answer character varying,
    question_id bigint NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    record_status integer NOT NULL,
    version integer NOT NULL
);
    DROP TABLE public.test_answer;
       public         heap    postgres    false            �            1259    16762    test_answer_id_seq    SEQUENCE     �   ALTER TABLE public.test_answer ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.test_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    16764    test_id_seq    SEQUENCE     �   ALTER TABLE public.test ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.test_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    214            �            1259    16766    test_question    TABLE     �  CREATE TABLE public.test_question (
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
 !   DROP TABLE public.test_question;
       public         heap    postgres    false            �            1259    16772    test_question_id_seq    SEQUENCE     �   ALTER TABLE public.test_question ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.test_question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    16774    test_solution    TABLE     ^  CREATE TABLE public.test_solution (
    id bigint NOT NULL,
    korisnik_id bigint NOT NULL,
    test_id bigint NOT NULL,
    ks_korisnik_id bigint NOT NULL,
    record_status smallint NOT NULL,
    insert_timestamp timestamp without time zone NOT NULL,
    last_update_timestamp timestamp without time zone NOT NULL,
    version integer NOT NULL
);
 !   DROP TABLE public.test_solution;
       public         heap    postgres    false            �            1259    16777    test_solution_id_seq    SEQUENCE     �   ALTER TABLE public.test_solution ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.test_solution_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    16779    test_solution_test_answer    TABLE     |   CREATE TABLE public.test_solution_test_answer (
    test_solution_id bigint NOT NULL,
    test_answer_id bigint NOT NULL
);
 -   DROP TABLE public.test_solution_test_answer;
       public         heap    postgres    false            �            1259    16782    test_test_question    TABLE     n   CREATE TABLE public.test_test_question (
    test_id bigint NOT NULL,
    test_question_id bigint NOT NULL
);
 &   DROP TABLE public.test_test_question;
       public         heap    postgres    false            �          0    16707    ks_korisnik 
   TABLE DATA           �   COPY public.ks_korisnik (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, salt, aktivan) FROM stdin;
    public          postgres    false    202   ��       �          0    16715    ks_korisnik_ks_uloga 
   TABLE DATA           E   COPY public.ks_korisnik_ks_uloga (korisnik_id, uloga_id) FROM stdin;
    public          postgres    false    204   �       �          0    16718    ks_uloga 
   TABLE DATA           @   COPY public.ks_uloga (id, oznaka, naziv, uloga_tip) FROM stdin;
    public          postgres    false    205   )�       �          0    16726    questionaire 
   TABLE DATA           �   COPY public.questionaire (id, naziv, opis, datum_od, datum_do, anonymous, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
    public          postgres    false    207   ą       �          0    16900    questionaire_answer 
   TABLE DATA           �   COPY public.questionaire_answer (id, answer, question_id, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
    public          postgres    false    226   �       �          0    16734    questionaire_question 
   TABLE DATA           �   COPY public.questionaire_question (id, question, answers, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, input_type) FROM stdin;
    public          postgres    false    209   &�       �          0    16742 "   questionaire_questionaire_question 
   TABLE DATA           g   COPY public.questionaire_questionaire_question (questionaire_id, questionaire_question_id) FROM stdin;
    public          postgres    false    211   ��       �          0    16745    questionaire_solution 
   TABLE DATA           �   COPY public.questionaire_solution (id, korisnik_id, questionaire_id, ks_korisnik_id, record_status, insert_timestamp, last_update_timestamp, version) FROM stdin;
    public          postgres    false    212   �       �          0    16893 )   questionaire_solution_questionaire_answer 
   TABLE DATA           u   COPY public.questionaire_solution_questionaire_answer (questionaire_solution_id, questionaire_answer_id) FROM stdin;
    public          postgres    false    224   b�       �          0    16750    test 
   TABLE DATA           �   COPY public.test (id, naziv, opis, datum_od, datum_do, trajanje, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
    public          postgres    false    214   ��       �          0    16756    test_answer 
   TABLE DATA           �   COPY public.test_answer (id, answer, question_id, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version) FROM stdin;
    public          postgres    false    215   )�       �          0    16766    test_question 
   TABLE DATA           �   COPY public.test_question (id, question, answers, correct_answer, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version, input_type) FROM stdin;
    public          postgres    false    218   ^�       �          0    16774    test_solution 
   TABLE DATA           �   COPY public.test_solution (id, korisnik_id, test_id, ks_korisnik_id, record_status, insert_timestamp, last_update_timestamp, version) FROM stdin;
    public          postgres    false    220   (�       �          0    16779    test_solution_test_answer 
   TABLE DATA           U   COPY public.test_solution_test_answer (test_solution_id, test_answer_id) FROM stdin;
    public          postgres    false    222   Ӑ       �          0    16782    test_test_question 
   TABLE DATA           G   COPY public.test_test_question (test_id, test_question_id) FROM stdin;
    public          postgres    false    223   ��       �           0    0    ks_korisnik_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.ks_korisnik_id_seq', 27, true);
          public          postgres    false    203            �           0    0    ks_uloga_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.ks_uloga_id_seq', 4, true);
          public          postgres    false    206            �           0    0    questionaire_answer_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.questionaire_answer_id_seq', 7, true);
          public          postgres    false    225            �           0    0    questionaire_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.questionaire_id_seq', 7, true);
          public          postgres    false    208            �           0    0    questionaire_question_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.questionaire_question_id_seq', 9, true);
          public          postgres    false    210            �           0    0    questionaire_solution_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.questionaire_solution_id_seq', 8, true);
          public          postgres    false    213            �           0    0    test_answer_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.test_answer_id_seq', 104, true);
          public          postgres    false    216            �           0    0    test_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.test_id_seq', 25, true);
          public          postgres    false    217            �           0    0    test_question_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.test_question_id_seq', 36, true);
          public          postgres    false    219            �           0    0    test_solution_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.test_solution_id_seq', 35, true);
          public          postgres    false    221            �
           2606    16786    ks_uloga ck_oznaka_unique 
   CONSTRAINT     V   ALTER TABLE ONLY public.ks_uloga
    ADD CONSTRAINT ck_oznaka_unique UNIQUE (oznaka);
 C   ALTER TABLE ONLY public.ks_uloga DROP CONSTRAINT ck_oznaka_unique;
       public            postgres    false    205            �
           2606    16788    ks_korisnik ck_username_unique 
   CONSTRAINT     ]   ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT ck_username_unique UNIQUE (username);
 H   ALTER TABLE ONLY public.ks_korisnik DROP CONSTRAINT ck_username_unique;
       public            postgres    false    202            �
           2606    16790    ks_uloga ks_uloga_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.ks_uloga
    ADD CONSTRAINT ks_uloga_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.ks_uloga DROP CONSTRAINT ks_uloga_pkey;
       public            postgres    false    205            �
           2606    16792    ks_korisnik pk_ks_korisnik 
   CONSTRAINT     X   ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT pk_ks_korisnik PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.ks_korisnik DROP CONSTRAINT pk_ks_korisnik;
       public            postgres    false    202            �
           2606    16794 ,   ks_korisnik_ks_uloga pk_ks_korisnik_ks_uloga 
   CONSTRAINT     }   ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT pk_ks_korisnik_ks_uloga PRIMARY KEY (korisnik_id, uloga_id);
 V   ALTER TABLE ONLY public.ks_korisnik_ks_uloga DROP CONSTRAINT pk_ks_korisnik_ks_uloga;
       public            postgres    false    204    204            �
           2606    16907 ,   questionaire_answer questionaire_answer_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.questionaire_answer
    ADD CONSTRAINT questionaire_answer_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.questionaire_answer DROP CONSTRAINT questionaire_answer_pkey;
       public            postgres    false    226            �
           2606    16796    questionaire questionaire_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.questionaire
    ADD CONSTRAINT questionaire_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.questionaire DROP CONSTRAINT questionaire_pkey;
       public            postgres    false    207            �
           2606    16798 0   questionaire_question questionaire_question_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.questionaire_question
    ADD CONSTRAINT questionaire_question_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.questionaire_question DROP CONSTRAINT questionaire_question_pkey;
       public            postgres    false    209            �
           2606    16800 J   questionaire_questionaire_question questionaire_questionaire_question_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.questionaire_questionaire_question
    ADD CONSTRAINT questionaire_questionaire_question_pkey PRIMARY KEY (questionaire_id, questionaire_question_id);
 t   ALTER TABLE ONLY public.questionaire_questionaire_question DROP CONSTRAINT questionaire_questionaire_question_pkey;
       public            postgres    false    211    211            �
           2606    16802 0   questionaire_solution questionaire_solution_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.questionaire_solution DROP CONSTRAINT questionaire_solution_pkey;
       public            postgres    false    212            �
           2606    16897 X   questionaire_solution_questionaire_answer questionaire_solution_questionaire_answer_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.questionaire_solution_questionaire_answer
    ADD CONSTRAINT questionaire_solution_questionaire_answer_pkey PRIMARY KEY (questionaire_solution_id, questionaire_answer_id);
 �   ALTER TABLE ONLY public.questionaire_solution_questionaire_answer DROP CONSTRAINT questionaire_solution_questionaire_answer_pkey;
       public            postgres    false    224    224            �
           2606    16804    test_answer test_answer_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.test_answer
    ADD CONSTRAINT test_answer_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.test_answer DROP CONSTRAINT test_answer_pkey;
       public            postgres    false    215            �
           2606    16806    test test_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.test DROP CONSTRAINT test_pkey;
       public            postgres    false    214            �
           2606    16808     test_question test_question_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.test_question
    ADD CONSTRAINT test_question_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.test_question DROP CONSTRAINT test_question_pkey;
       public            postgres    false    218            �
           2606    16810     test_solution test_solution_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.test_solution DROP CONSTRAINT test_solution_pkey;
       public            postgres    false    220            �
           2606    16812 8   test_solution_test_answer test_solution_test_answer_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.test_solution_test_answer
    ADD CONSTRAINT test_solution_test_answer_pkey PRIMARY KEY (test_solution_id, test_answer_id);
 b   ALTER TABLE ONLY public.test_solution_test_answer DROP CONSTRAINT test_solution_test_answer_pkey;
       public            postgres    false    222    222            �
           2606    16814 *   test_test_question test_test_question_pkey 
   CONSTRAINT        ALTER TABLE ONLY public.test_test_question
    ADD CONSTRAINT test_test_question_pkey PRIMARY KEY (test_id, test_question_id);
 T   ALTER TABLE ONLY public.test_test_question DROP CONSTRAINT test_test_question_pkey;
       public            postgres    false    223    223            �
           1259    16815    ix_ks_korisnik_id    INDEX     <  CREATE UNIQUE INDEX ix_ks_korisnik_id ON public.ks_korisnik USING btree (id) INCLUDE (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version);

ALTER TABLE public.ks_korisnik CLUSTER ON ix_ks_korisnik_id;
 %   DROP INDEX public.ix_ks_korisnik_id;
       public            postgres    false    202    202    202    202    202    202    202    202    202    202    202    202    202    202    202            �
           1259    16816    ix_ks_korisnik_username    INDEX       CREATE INDEX ix_ks_korisnik_username ON public.ks_korisnik USING btree (username) INCLUDE (id, username, password, ime, prezime, email, datum_rodjenja, mesto_rodjenja, telefon, ks_korisnik_id, insert_timestamp, last_update_timestamp, record_status, version);
 +   DROP INDEX public.ix_ks_korisnik_username;
       public            postgres    false    202    202    202    202    202    202    202    202    202    202    202    202    202    202    202            �
           2606    16817 +   ks_korisnik ks_korisnik_ks_korisnik_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.ks_korisnik
    ADD CONSTRAINT ks_korisnik_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);
 U   ALTER TABLE ONLY public.ks_korisnik DROP CONSTRAINT ks_korisnik_ks_korisnik_id_fkey;
       public          postgres    false    202    2774    202            �
           2606    16822 :   ks_korisnik_ks_uloga ks_korisnik_ks_uloga_korisnik_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);
 d   ALTER TABLE ONLY public.ks_korisnik_ks_uloga DROP CONSTRAINT ks_korisnik_ks_uloga_korisnik_id_fkey;
       public          postgres    false    202    2774    204            �
           2606    16827 7   ks_korisnik_ks_uloga ks_korisnik_ks_uloga_uloga_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.ks_korisnik_ks_uloga
    ADD CONSTRAINT ks_korisnik_ks_uloga_uloga_id_fkey FOREIGN KEY (uloga_id) REFERENCES public.ks_uloga(id);
 a   ALTER TABLE ONLY public.ks_korisnik_ks_uloga DROP CONSTRAINT ks_korisnik_ks_uloga_uloga_id_fkey;
       public          postgres    false    205    204    2780                       2606    16908 ;   questionaire_answer questionaire_answer_ks_korisnik_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.questionaire_answer
    ADD CONSTRAINT questionaire_answer_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);
 e   ALTER TABLE ONLY public.questionaire_answer DROP CONSTRAINT questionaire_answer_ks_korisnik_id_fkey;
       public          postgres    false    226    2774    202                       2606    16913 E   questionaire_answer questionaire_answer_questionaire_question_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.questionaire_answer
    ADD CONSTRAINT questionaire_answer_questionaire_question_id_fkey FOREIGN KEY (question_id) REFERENCES public.questionaire_question(id);
 o   ALTER TABLE ONLY public.questionaire_answer DROP CONSTRAINT questionaire_answer_questionaire_question_id_fkey;
       public          postgres    false    2784    226    209            �
           2606    16832 <   questionaire_question questionaire_question_ks_korisnik_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.questionaire_question
    ADD CONSTRAINT questionaire_question_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);
 f   ALTER TABLE ONLY public.questionaire_question DROP CONSTRAINT questionaire_question_ks_korisnik_fkey;
       public          postgres    false    202    2774    209            �
           2606    16837 <   questionaire_solution questionaire_solution_korisnik_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);
 f   ALTER TABLE ONLY public.questionaire_solution DROP CONSTRAINT questionaire_solution_korisnik_id_fkey;
       public          postgres    false    212    202    2774            �
           2606    16842 ?   questionaire_solution questionaire_solution_ks_korisnik_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);
 i   ALTER TABLE ONLY public.questionaire_solution DROP CONSTRAINT questionaire_solution_ks_korisnik_id_fkey;
       public          postgres    false    212    202    2774            �
           2606    16847 8   questionaire_solution questionaire_solution_test_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.questionaire_solution
    ADD CONSTRAINT questionaire_solution_test_id_fkey FOREIGN KEY (questionaire_id) REFERENCES public.questionaire(id);
 b   ALTER TABLE ONLY public.questionaire_solution DROP CONSTRAINT questionaire_solution_test_id_fkey;
       public          postgres    false    212    207    2782            �
           2606    16852 *   questionaire questioniare_ks_korisnik_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.questionaire
    ADD CONSTRAINT questioniare_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);
 T   ALTER TABLE ONLY public.questionaire DROP CONSTRAINT questioniare_ks_korisnik_fkey;
       public          postgres    false    207    202    2774            �
           2606    16857 +   test_answer test_answer_ks_korisnik_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.test_answer
    ADD CONSTRAINT test_answer_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);
 U   ALTER TABLE ONLY public.test_answer DROP CONSTRAINT test_answer_ks_korisnik_id_fkey;
       public          postgres    false    215    202    2774            �
           2606    16862 -   test_answer test_answer_test_question_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.test_answer
    ADD CONSTRAINT test_answer_test_question_id_fkey FOREIGN KEY (question_id) REFERENCES public.test_question(id);
 W   ALTER TABLE ONLY public.test_answer DROP CONSTRAINT test_answer_test_question_id_fkey;
       public          postgres    false    215    218    2794            �
           2606    16867    test test_ks_korisnik_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);
 D   ALTER TABLE ONLY public.test DROP CONSTRAINT test_ks_korisnik_fkey;
       public          postgres    false    214    202    2774                        2606    16872 ,   test_question test_question_ks_korisnik_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.test_question
    ADD CONSTRAINT test_question_ks_korisnik_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);
 V   ALTER TABLE ONLY public.test_question DROP CONSTRAINT test_question_ks_korisnik_fkey;
       public          postgres    false    218    202    2774                       2606    16877 ,   test_solution test_solution_korisnik_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_korisnik_id_fkey FOREIGN KEY (korisnik_id) REFERENCES public.ks_korisnik(id);
 V   ALTER TABLE ONLY public.test_solution DROP CONSTRAINT test_solution_korisnik_id_fkey;
       public          postgres    false    202    220    2774                       2606    16882 /   test_solution test_solution_ks_korisnik_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_ks_korisnik_id_fkey FOREIGN KEY (ks_korisnik_id) REFERENCES public.ks_korisnik(id);
 Y   ALTER TABLE ONLY public.test_solution DROP CONSTRAINT test_solution_ks_korisnik_id_fkey;
       public          postgres    false    220    2774    202                       2606    16887 (   test_solution test_solution_test_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.test_solution
    ADD CONSTRAINT test_solution_test_id_fkey FOREIGN KEY (test_id) REFERENCES public.test(id);
 R   ALTER TABLE ONLY public.test_solution DROP CONSTRAINT test_solution_test_id_fkey;
       public          postgres    false    214    220    2790            �   &  x�m�[o�@���W����0�4Y-�E� j��"ȱ���4��m3�L�~�'�!W�e��}M�3Gh0�$��V���d�m	>���I�87�EϞ3ڈ�j�\�zG_��xj�c��]:V��T�b�6�e��g�ܰ�|�~U����4�@������N�>f"L*2��  �1�	�B�'��H��)"��mU�{m��̉���֫��{D�C`f���
v����O\�C"�YX��0�b��������щl�-��FWi��E�a����j%���f��h�q��1P�6�V��K)]\�ھ��������c�s��ߎ��=� ���%2�$�=D`�D�ӓ��*F8i��Ky��}�N�?���
�B��cߝ�b�wz(�Ui����ad�G_\�@�2��[/7W�D6��/wNi}f��ŪmW��i�Vi���>WoFA'��6���f�}�qw�ut�w�k�kYI���TbL�a�H2�<��Q�*�z�h��|���?�	�>w,sK����^�'�3VO�g��7������`      �   &   x�34�4�24�4�22�4&\F�� +F��� V��      �   �   x�=�A
�0��W�J��9���"�Ү���k���	�"$�-������j����6��ŭ�6ڦ2b�
V�b5u��n���"j�G*j�pH^9�ܢGǉ�����'-��S3)���z�]�Q���1t.���^��:?�      �   �   x�}�K�0D��)r����t���*��b��KUU|�y�,?�Pn��t˝��\�7�@PA*�6*]�w9X�쑽w�A��P��$��"!&o"e�f������u0Dd�̵k�O�k�T5q���4�[�qm�ڡ�����od�j9��Y3�Cd@�!&��3ƼK6H�      �   �   x�}�;
!��:�b�#y�x�m�r���4� 6�|$2|j#H�� �|b<�2k&��2a�%t��^[��R�+�N�
s y�f���t � Yɳ���Ҵ�Hm����Zܥ	ű_�C*�T��<*�mʄ�ﻟ��_���T��9��"VM      �   �   x���=
�P�z�9�y���I����B-�?&� ��f�a?f�<.���z2��B����z��1�Đ���VE��o�r?����5�O:������:n�����Ke���ȩU-շ��v�E'���� �F�      �      x�3�4�2�4�2� bK�=... ��      �   o   x�}��1Dѳ��hT,6�X:�8F��f��婾�1�92�zc]*����7LwG�y��шR���L_�V�a��vG�8��ٰ���1�:����'�=��w=LD/g<�      �   "   x�3�4�2�4�2�4bS.N3 6����� 7 �      �   �   x�}M9
�0�W��X�u�%�D
ب��."��s0b����jn؎��r���F�����Aٓ:�ֲC���d�66F�ܷ��>J-x��>:֌_�{K n�`�($	��D��"R��zf6      �   %  x���]��6��=��\C������M'@�)����-;�%�
����#��m������� :�;�E����}*� ��
�%A�>l�{�s�E #��r*���@n)�teV���Rb��e;"�Ț�Ċ��?�K�ב`��>z�TSJPL�=� ���&�����B�8(�+�,��d
V(v@Gk��ٖ���?)F\�;+��ŉ�8(�+�*�]KL�S��ֺ�Ĕ �;�Z�}lILz�=
�]�j0�W�F�-�)(��EK�E1��oQ֣�F����/:o#1�u���u�?���G�����ϯ����];����o	'm�yX[ S�a�ј��(Z�-��x����x����x�z��o{��T�cHVz��ڴ$�����_ܙ�_�&��0��lǍ��]�w}�d\Xpnj����q �Һ)1�'p
�zd�(l-1)t��.���}y֗�S<��Ĵ8-�:�V[��ț��3*�U��_o��7INC������_��Ѻ�QQ��$f���pCwƖ�$���؃W��Z�L7^��U��J]Kb�=v���"]@[�ܑ[)��$����[)�EՔ���J	A�+,u�c�BV7%~�PGn�pmulIL�c�R������,<v+%x�kn-�������-�Y��-����ne�I��[�vؒ��θ��k-�Y��[:Fo��±[F��[���L��7%~Vg�2�y[��,1�bAO�ڥ���=�^�nhi-1	Rm}9܁i(��tӝ8����A䄙���)�����,Ut!���r���hMq���^�NȺ�㕦1ޒ�Ww<[��,1���9�[Ӕ��ݫ��p}�a1���Q�Μ�#��Ib4W��i�x-1)��Un�o��RT�������\�pY)���1uϧ��sx-1ɮ&Km�9AK5�¤�&G�J��%&5n����`M�L^KL�o0��i�M1[��h�����N���
����|Ŭ�Ĭf��В#4��fm$&M�4��U�	�~��Y��j���R���='8˚$f�3��Kp��7���q���n����f7�X�/g�{��Q����sx�KKi�Liԟ���G�Z�O��u9�6����g67gy� 6?��2��L�X.�e��*�b�m$��Nu�9 d#1H�d�	���wt#1i{�2'���ZaZ�1�a�*��T�:���ZV�h�����t��YN�Jͥ��!�{y�~^#(���m���kP�/n�	�ݕ/)ŗ�u�F�5IKO��2'(�7��j^��x���V�g�������_      �   �   x���K
�0E�/��ly���E��N��A+\�Fk�BC'���ss��~M�x����?���Η�9=�+�
�ElE[�Z��^K� ���"��r�%��,�EN��*�7%4��'g�q���`狿c���w%�W�H�$����m��^yȵZ5���'؈[B`L9!q~Ҧ:r���j���7s��      �   �  x����m�0D��U��<EJ���:2�2,�o�$�#ʋ�%�?�OB�$�Կ�'���(4E+D[<K\'g�pʬm�%�K�<Iئ�i#��W�6�'�Of��������L�F��H�`���SzS�a�Cǘ�-���
����i�Qr�멜,����k���	��hM��	��KK|V�}�@;M+˺s�����ؖ;�M֝���BS�y��V�u�>�h�4(+˺s�h6f�8*˺u��xj��������},����ph��,G˺��h��w�������;�H̤.�㱹H�^��^8{���۲#y��ѦEo��x���ޞ,Eo��$��Z�#��h-F{sI��,Ek1�߀0U�B���j�F�tB��5,8��q�~����m���Z�      �   �   x�%л�d1Q��%~B���c���X�В�R{w�udԎ�Q31Ss1���R����-qC����x��ƭZ|>��O"0�pۑh|8�yԏ��c`��d��Ƨ��Y�����mS���|�mK��c�Wn���m�����}ҁ��ێt���|G��3tL�Wx��i������ۆL`b�)��p>�l\;��M�&��UCWA      �   %   x�32�46�2�F`�L�p�r��I3�=... ��     