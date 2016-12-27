CREATE ROLE eschronisko_group
   VALID UNTIL 'infinity';

CREATE ROLE eschronisko_user LOGIN ENCRYPTED PASSWORD 'md5213adee6b7615b22fef83821c803448e'
   VALID UNTIL 'infinity';
GRANT eschronisko_group TO eschronisko_user;

CREATE DATABASE eschronisko
  WITH ENCODING='UTF8'
       OWNER=eschronisko_user
       CONNECTION LIMIT=-1;

GRANT ALL ON DATABASE eschronisko TO GROUP eschronisko_group;
