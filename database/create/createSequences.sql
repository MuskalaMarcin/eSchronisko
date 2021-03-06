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