--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: department; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department (
    department_id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    department_name character varying(255) NOT NULL,
    parent_id integer
);


ALTER TABLE public.department OWNER TO postgres;

--
-- Name: department_aud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department_aud (
    department_id integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    created_at timestamp without time zone,
    department_name character varying(255),
    parent_id integer
);


ALTER TABLE public.department_aud OWNER TO postgres;

--
-- Name: department_audit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department_audit (
    id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    department_id integer NOT NULL,
    department_name character varying(255) NOT NULL,
    parent_department_id integer
);


ALTER TABLE public.department_audit OWNER TO postgres;

--
-- Name: department_balance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.department_balance (
    id integer NOT NULL,
    balance integer,
    department_id integer NOT NULL
);


ALTER TABLE public.department_balance OWNER TO postgres;

--
-- Name: department_balance_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.department_balance_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.department_balance_id_seq OWNER TO postgres;

--
-- Name: department_balance_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.department_balance_id_seq OWNED BY public.department_balance.id;


--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    employee_id integer NOT NULL,
    birthday timestamp without time zone NOT NULL,
    chief boolean,
    dismissal_date timestamp without time zone,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    patronymic character varying(255),
    phone character varying(255) NOT NULL,
    recruitment_date timestamp without time zone NOT NULL,
    salary integer NOT NULL,
    sex character varying(255) NOT NULL,
    surname character varying(255) NOT NULL,
    id integer NOT NULL,
    department_id integer NOT NULL,
    "position" integer NOT NULL
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_id_seq OWNER TO postgres;

--
-- Name: employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;


--
-- Name: employee_position; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee_position (
    position_id integer NOT NULL,
    position_name character varying(255) NOT NULL
);


ALTER TABLE public.employee_position OWNER TO postgres;

--
-- Name: employee_position_position_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employee_position_position_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_position_position_id_seq OWNER TO postgres;

--
-- Name: employee_position_position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employee_position_position_id_seq OWNED BY public.employee_position.position_id;


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: revinfo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.revinfo (
    rev integer NOT NULL,
    revtstmp bigint
);


ALTER TABLE public.revinfo OWNER TO postgres;

--
-- Name: department_balance id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department_balance ALTER COLUMN id SET DEFAULT nextval('public.department_balance_id_seq'::regclass);


--
-- Name: employee id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);


--
-- Name: employee_position position_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_position ALTER COLUMN position_id SET DEFAULT nextval('public.employee_position_position_id_seq'::regclass);

--
-- Name: department_balance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.department_balance_id_seq', 5, true);


--
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employee_id_seq', 24, true);


--
-- Name: employee_position_position_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employee_position_position_id_seq', 3, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 48, true);


--
-- Name: department_aud department_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department_aud
    ADD CONSTRAINT department_aud_pkey PRIMARY KEY (department_id, rev);


--
-- Name: department_balance department_balance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department_balance
    ADD CONSTRAINT department_balance_pkey PRIMARY KEY (id);


--
-- Name: department department_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (department_id);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: employee_position employee_position_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_position
    ADD CONSTRAINT employee_position_pkey PRIMARY KEY (position_id);


--
-- Name: revinfo revinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.revinfo
    ADD CONSTRAINT revinfo_pkey PRIMARY KEY (rev);


--
-- Name: department_balance uk_31conarqof9nle5xw3vpxu2pb; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department_balance
    ADD CONSTRAINT uk_31conarqof9nle5xw3vpxu2pb UNIQUE (department_id);


--
-- Name: department uk_f5np34wnxt905fwmrs6133l28; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT uk_f5np34wnxt905fwmrs6133l28 UNIQUE (department_name);


--
-- Name: employee_position uk_jr8uac52byuwst4dj48mo9n6v; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_position
    ADD CONSTRAINT uk_jr8uac52byuwst4dj48mo9n6v UNIQUE (position_name);


--
-- Name: department_balance fk8u2rgtt4mokic252d2mjw0mbo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department_balance
    ADD CONSTRAINT fk8u2rgtt4mokic252d2mjw0mbo FOREIGN KEY (department_id) REFERENCES public.department(department_id) ON DELETE CASCADE;


--
-- Name: employee fkbejtwvg9bxus2mffsm3swj3u9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkbejtwvg9bxus2mffsm3swj3u9 FOREIGN KEY (department_id) REFERENCES public.department(department_id) ON DELETE CASCADE;


--
-- Name: department_aud fkdrxjxvx2qlyxtsq8teb2fgqy8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department_aud
    ADD CONSTRAINT fkdrxjxvx2qlyxtsq8teb2fgqy8 FOREIGN KEY (rev) REFERENCES public.revinfo(rev);


--
-- Name: employee fkipv5oqgtdu39a369wr3vbfnqr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkipv5oqgtdu39a369wr3vbfnqr FOREIGN KEY ("position") REFERENCES public.employee_position(position_id);


--
-- Name: department fkmgsnnmudxrwqidn4f64q8rp4o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.department
    ADD CONSTRAINT fkmgsnnmudxrwqidn4f64q8rp4o FOREIGN KEY (parent_id) REFERENCES public.department(department_id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

