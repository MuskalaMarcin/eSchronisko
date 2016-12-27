CREATE SEQUENCE administrator_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE animal_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE animal_keeper_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE application_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE client_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE donation_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE food_ration_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE medical_card_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE medical_treatment_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE vet_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE warehouse_id_seq START WITH 1 INCREMENT BY 1;

GRANT ALL ON SEQUENCE public.administrator_id_seq TO GROUP eschronisko_group;
GRANT ALL ON SEQUENCE public.animal_id_seq TO GROUP eschronisko_group;
GRANT ALL ON SEQUENCE public.animal_keeper_id_seq TO GROUP eschronisko_group;
GRANT ALL ON SEQUENCE public.application_id_seq TO GROUP eschronisko_group;
GRANT ALL ON SEQUENCE public.client_id_seq TO GROUP eschronisko_group;
GRANT ALL ON SEQUENCE public.donation_id_seq TO GROUP eschronisko_group;
GRANT ALL ON SEQUENCE public.food_ration_id_seq TO GROUP eschronisko_group;
GRANT ALL ON SEQUENCE public.medical_card_id_seq TO GROUP eschronisko_group;
GRANT ALL ON SEQUENCE public.medical_treatment_id_seq TO GROUP eschronisko_group;
GRANT ALL ON SEQUENCE public.vet_id_seq TO GROUP eschronisko_group;
GRANT ALL ON SEQUENCE public.warehouse_id_seq TO GROUP eschronisko_group;

CREATE TABLE administrator
  (
    id      INTEGER NOT NULL ,
    name    VARCHAR (256) NOT NULL ,
    surname VARCHAR (256) NOT NULL ,
    address VARCHAR (512) NOT NULL
  ) ;
ALTER TABLE administrator ADD CONSTRAINT administrator_PK PRIMARY KEY ( id ) ;

CREATE TABLE article
  (
    id      VARCHAR (64) NOT NULL ,
    title    VARCHAR (256) NOT NULL ,
    content  TEXT NOT NULL
  ) ;
ALTER TABLE article ADD CONSTRAINT article_PK PRIMARY KEY ( id ) ;

CREATE TABLE animal
  (
    registration_number INTEGER NOT NULL ,
    name                VARCHAR (128) NOT NULL ,
    age                 INTEGER NOT NULL ,
    sex                 VARCHAR (32) NOT NULL ,
    species             VARCHAR (64) NOT NULL ,
    link_to_image       VARCHAR (256) ,
    room_number         INTEGER NOT NULL ,
    adoption_possible   INTEGER NOT NULL ,
    acceptance_date     TIMESTAMP NOT NULL ,
    adoption_date       TIMESTAMP
  ) ;
ALTER TABLE animal ADD CONSTRAINT animal_PK PRIMARY KEY ( registration_number ) ;


CREATE TABLE animal_keeper
  (
    id      INTEGER NOT NULL ,
    name    VARCHAR (256) NOT NULL ,
    surname VARCHAR (256) NOT NULL ,
    address VARCHAR (512) NOT NULL
  ) ;
ALTER TABLE animal_keeper ADD CONSTRAINT animal_keeper_PK PRIMARY KEY ( id ) ;


CREATE TABLE app_user
  (
    login            VARCHAR (128) NOT NULL ,
    password         VARCHAR (256) NOT NULL ,
    e_mail           VARCHAR (128) NOT NULL ,
    is_active        INTEGER NOT NULL ,
    user_role        VARCHAR (64) NOT NULL ,
    client_id        INTEGER ,
    vet_id           INTEGER ,
    animal_keeper_id INTEGER ,
    administrator_id INTEGER
  ) ;
ALTER TABLE app_user ADD CONSTRAINT App_user_PK PRIMARY KEY ( login ) ;


CREATE TABLE application
  (
    id            INTEGER NOT NULL ,
    dispatch_date TIMESTAMP NOT NULL ,
    status        VARCHAR (32) NOT NULL ,
    client_id     INTEGER NOT NULL ,
    animal_id    INTEGER NOT NULL
  ) ;
ALTER TABLE application ADD CONSTRAINT application_PK PRIMARY KEY ( id ) ;


CREATE TABLE client
  (
    id      INTEGER NOT NULL ,
    name    VARCHAR (256) NOT NULL ,
    surname VARCHAR (256) NOT NULL ,
    address VARCHAR (512) NOT NULL
  ) ;
ALTER TABLE client ADD CONSTRAINT client_PK PRIMARY KEY ( id ) ;


CREATE TABLE donation
  (
    id            INTEGER NOT NULL ,
    amount        FLOAT NOT NULL ,
    donation_date TIMESTAMP NOT NULL ,
    client_id     INTEGER NOT NULL
  ) ;
ALTER TABLE donation ADD CONSTRAINT payment_PK PRIMARY KEY ( id ) ;


CREATE TABLE food_ration
  (
    id           INTEGER NOT NULL ,
    amount       INTEGER NOT NULL ,
    warehouse_id INTEGER NOT NULL ,
    animal_id    INTEGER NOT NULL
  ) ;
ALTER TABLE food_ration ADD CONSTRAINT food_ration_PK PRIMARY KEY ( id ) ;


CREATE TABLE medical_card
  (
    id           INTEGER NOT NULL ,
    health_state VARCHAR (32) NOT NULL ,
    notes        VARCHAR (512) ,
    animal_id    INTEGER NOT NULL
  ) ;
ALTER TABLE medical_card ADD CONSTRAINT medical_card_PK PRIMARY KEY ( id ) ;


CREATE TABLE medical_treatment
  (
    id              INTEGER NOT NULL ,
    name            VARCHAR (64) NOT NULL ,
    description     VARCHAR (256) ,
    importance      INTEGER NOT NULL ,
    start_date      TIMESTAMP NOT NULL ,
    end_date        TIMESTAMP ,
    medical_card_id INTEGER NOT NULL
  ) ;
ALTER TABLE medical_treatment ADD CONSTRAINT medical_treatment_PK PRIMARY KEY ( id ) ;


CREATE TABLE vet
  (
    id      INTEGER NOT NULL ,
    name    VARCHAR (256) NOT NULL ,
    surname VARCHAR (256) NOT NULL ,
    address VARCHAR (512) NOT NULL
  ) ;
ALTER TABLE vet ADD CONSTRAINT vet_PK PRIMARY KEY ( id ) ;


CREATE TABLE warehouse
  (
    id         INTEGER NOT NULL ,
    amount_left INTEGER NOT NULL ,
    capacity   INTEGER NOT NULL
  ) ;
ALTER TABLE warehouse ADD CONSTRAINT Warehouse_PK PRIMARY KEY ( id ) ;

ALTER TABLE app_user ADD CONSTRAINT app_user_administrator_FK FOREIGN KEY ( administrator_id ) REFERENCES administrator ( id ) ;
ALTER TABLE app_user ADD CONSTRAINT app_user_animal_keeper_FK FOREIGN KEY ( animal_keeper_id ) REFERENCES animal_keeper ( id ) ;
ALTER TABLE app_user ADD CONSTRAINT app_user_client_FK FOREIGN KEY ( client_id ) REFERENCES client ( id ) ;
ALTER TABLE app_user ADD CONSTRAINT app_user_vet_FK FOREIGN KEY ( vet_id ) REFERENCES vet ( id ) ;
ALTER TABLE application ADD CONSTRAINT application_client_FK FOREIGN KEY ( client_id ) REFERENCES client ( id ) ;
ALTER TABLE application ADD CONSTRAINT application_animal_FK FOREIGN KEY ( animal_id ) REFERENCES animal ( registration_number ) ;
ALTER TABLE donation ADD CONSTRAINT donation_client_FK FOREIGN KEY ( client_id ) REFERENCES client ( id ) ;
ALTER TABLE food_ration ADD CONSTRAINT food_ration_animal_FK FOREIGN KEY ( animal_id ) REFERENCES animal ( registration_number ) ;
ALTER TABLE medical_card ADD CONSTRAINT medical_card_animal_FK FOREIGN KEY ( animal_id ) REFERENCES animal ( registration_number ) ;
ALTER TABLE medical_treatment ADD CONSTRAINT treatment_medical_card_FK FOREIGN KEY ( medical_card_id ) REFERENCES medical_card ( id ) ;
ALTER TABLE food_ration ADD CONSTRAINT warehouse_FK FOREIGN KEY ( warehouse_id ) REFERENCES warehouse ( id ) ;

GRANT ALL ON TABLE public.administrator TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.animal TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.animal_keeper TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.app_user TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.application TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.article TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.client TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.donation TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.food_ration TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.medical_card TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.medical_treatment TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.vet TO GROUP eschronisko_group;
GRANT ALL ON TABLE public.warehouse TO GROUP eschronisko_group;