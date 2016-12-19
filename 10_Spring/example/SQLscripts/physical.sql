-- Sequence: public."Blog_id_seq"

-- DROP SEQUENCE public."Blog_id_seq";

CREATE SEQUENCE public."Blog_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public."Blog_id_seq"
  OWNER TO postgres;


-- Table: public."Blog"

-- DROP TABLE public."Blog";

CREATE TABLE public."Blog"
(
  id integer NOT NULL DEFAULT nextval('"Blog_id_seq"'::regclass),
  creator character varying(20) NOT NULL DEFAULT 'Anonymous'::character varying,
  title character varying(50) NOT NULL,
  text text,
  CONSTRAINT "Blog_pkey" PRIMARY KEY (id),
  CONSTRAINT "Blog_title_key" UNIQUE (title)
);

  
-- Sequence: public."Comment_id_seq"

-- DROP SEQUENCE public."Comment_id_seq";

CREATE SEQUENCE public."Comment_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public."Comment_id_seq"
  OWNER TO postgres;
  
  
-- Table: public."Comment"

-- DROP TABLE public."Comment";

CREATE TABLE public."Comment"
(
  id integer NOT NULL DEFAULT nextval('"Comment_id_seq"'::regclass),
  blog_id integer NOT NULL,
  comment text NOT NULL,
  CONSTRAINT "Comment_pkey" PRIMARY KEY (id),
  CONSTRAINT "Comment_blog_id_fkey" FOREIGN KEY (blog_id)
      REFERENCES public."Blog" (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);
