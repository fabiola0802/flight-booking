
CREATE TYPE public.role as ENUM (
	'USER', 'SUPERVISOR'
);

ALTER TYPE public.role OWNER TO postgres;

CREATE TYPE public.status as ENUM (
	'RESERVED', 'APPROVED', 'REJECTED'
);


ALTER TYPE public.status OWNER TO postgres;

CREATE TYPE public.type as ENUM (
	'APPROVED', 'REJECTED'
);


ALTER TYPE public.type OWNER TO postgres;

CREATE TABLE public.customer (
                               id SERIAL PRIMARY KEY ,
                               first_name character varying (20) NOT NULL,
                               last_name character varying (20) NOT NUll,
                               email character varying (20) UNIQUE NOT NULL,
                               password character varying (20) NOT NULL,
                               role role ,
                               validity boolean default true
);

ALTER TABLE public.customer OWNER TO postgres;

CREATE TABLE public.flight (
                                 id SERIAL PRIMARY KEY ,
                                 departure character varying (20) NOT NULL,
                                 destination character varying (20) NOT NUll,
                                 departure_time timestamp without time zone NOT NULL,
                                 arrival_time timestamp without time zone NOT NULL,
                                 capacity integer NOT NULL,
                                 validity boolean default true
);

ALTER TABLE public.flight OWNER TO postgres;

CREATE TABLE public.class (
                                id SERIAL PRIMARY KEY ,
                                name character varying (20) NOT NULL

);
ALTER TABLE public.class OWNER TO postgres;

CREATE TABLE public.flight_class (
                                     id SERIAL PRIMARY KEY ,
                                     price  double precision NOT NULL,
                                     capacity integer NOT NULL,
                                     flight_id integer NOT NULL,
                                     class_id integer NOT NULL
);
ALTER TABLE public.flight_class OWNER TO postgres;
ALTER TABLE ONLY public.flight_class ADD CONSTRAINT "flight_id_FK" FOREIGN KEY (flight_id) REFERENCES public.flight(id);
ALTER TABLE ONLY public.flight_class ADD CONSTRAINT "class_id_FK" FOREIGN KEY (class_id) REFERENCES public.class(id);

CREATE TABLE public.booking (
                                id SERIAL PRIMARY KEY ,
                                booking_date timestamp without time zone NOT NULL,
                                status status NOT NULL,
                                note character varying,
                                flight_class_id integer NOT NULL,
                                customer_id integer NOT NULL
);

ALTER TABLE public.booking OWNER TO postgres;
ALTER TABLE ONLY public.booking ADD CONSTRAINT "flight_class_id_FK" FOREIGN KEY (flight_class_id) REFERENCES public.flight_class(id);
ALTER TABLE ONLY public.booking ADD CONSTRAINT "customer_id_FK" FOREIGN KEY (customer_id) REFERENCES public.customer(id);

CREATE TABLE public.notification (
                                     id SERIAL PRIMARY KEY ,
                                     created_on timestamp without time zone NOT NULL,
                                     message character varying NOT NULL,
                                     type type NOT NULL,
                                     booking_id integer NOT NULL
);
ALTER TABLE public.notification OWNER TO postgres;
ALTER TABLE ONLY public.notification ADD CONSTRAINT "booking_id_FK" FOREIGN KEY (booking_id) REFERENCES public.booking(id);




