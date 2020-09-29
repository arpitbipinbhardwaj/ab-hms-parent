CREATE USER 'hmsdbuser'@'%' IDENTIFIED BY 'hmsdbpassword';
REVOKE ALL PRIVILEGES,grant option FROM 'hmsdbuser';
GRANT ALL PRIVILEGES ON hmsimpl.* TO 'hmsdbuser'@'%';
SHOW grants for 'hmsdbuser'@'%';

INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (1,'admin','admin:create');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (2,'admin','admin:update');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (3,'admin','admin:delete');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (4,'admin','admin:read');

INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (5,'doctor','doctor:create');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (6,'doctor','doctor:update');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (7,'doctor','doctor:delete');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (8,'doctor','doctor:read');

INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (9,'receptionist','receptionist:create');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (10,'receptionist','receptionist:update');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (11,'receptionist','receptionist:delete');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (12,'receptionist','receptionist:read');

INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (13,'patient','patient:create');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (14,'patient','patient:update');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (15,'patient','patient:delete');
INSERT INTO USER_AUTHORITY(id, module, authority) VALUE (16,'patient','patient:read');

INSERT INTO USER_ROLE(id, authorities, rolename) VALUE (1,'admin:create,admin:update,admin:delete,admin:read,doctor:create,doctor:update,doctor:delete,doctor:read,receptionist:create,receptionist:update,receptionist:delete,receptionist:read,patient:create,patient:update,patient:delete,patient:read','ROLE_SUPERADMIN');
INSERT INTO USER_ROLE(id, authorities, rolename) VALUE ((2,'doctor:create,doctor:update,doctor:delete,doctor:read,receptionist:create,receptionist:update,receptionist:delete,receptionist:read,patient:create,patient:update,patient:delete,patient:read,ROLE_ADMIN');
INSERT INTO USER_ROLE(id, authorities, rolename) VALUE ((3,'patient:read','ROLE_DOCTOR');
INSERT INTO USER_ROLE(id, authorities, rolename) VALUE ((4,'patient:create,patient:update,patient:delete,patient:read','ROLE_RECEPTIONIST');