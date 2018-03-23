INSERT INTO ADMIN (ID, NAME,ID_AUTH) VALUES
    (1,'Shon',2),
    (2,'Derek',4),
    (3,'Petr',5);

INSERT INTO USER (ID, NAME, ADDRESS, ID_AUTH) VALUES
    (1,'Greg','N104',1),
    (2,'Rick','N104',6),
    (3,'Jon','N104',7);

INSERT INTO QUALITY (ID, NAME) VALUES
    (1,'BAD'),
    (2,'NORMAL'),
    (3,'GOOD');

INSERT INTO STATUS (ID, NAME) VALUES
    (1,'WORK'),
    (2,'WAIT'),
    (3,'DONE');

INSERT INTO EVENT(ID_USER, ID_ADMIN, DESCRIPTION, DATE, COMMENT, ID_QUALITY, ID_STATUS) VALUES
    (1, 1, 'error 0x00000007b', '2018-03-20', 'change mode AHCI on IDE', 1, 1),
    (1, 1, 'dont work mouse', '2018-03-22', 'plug in', 2, 2),
    (2, 2, 'no picture on the screen', '2018-03-23', 'Connected power to the monitor', 3, 3),
    (2, 2, 'does not send to print', '2018-03-24', 'Reset drivers', 1, 1),
    (3, 3, 'Hung the computer', '2018-03-25', 'Reset', 2, 2);

INSERT INTO ROLE (ID, NAME , AUTHORITY) VALUES
  (1, 'user', 'ROLE_USER'),
  (2, 'admin', 'ROLE_ADMIN'),
  (3, 'superadmin', 'ROLE_SUPERADMIN');

INSERT INTO USERS (ID, USERNAME, PASSWORD) VALUES
    (1,'user','$2a$10$nYOEzE1HJzi4E2vYpwuNF.kZTS38amb/FDCsvvY2y0byqRIA0lhmi'),
    (2,'admin','$2a$10$9eOIaFtzFIHJ69WslgrjieXUyPQz6F6T6SEp5vR0ZdkUHhw0mFoHK'),
    (3,'superadmin','$2a$10$VuIxPvtKjopR6U2uYgud7OvhHCdo8/K0xQC8J8OifRA.wGPvp8SQ.'),
    (4,'admin1','$2a$10$9eOIaFtzFIHJ69WslgrjieXUyPQz6F6T6SEp5vR0ZdkUHhw0mFoHK'),
    (5,'admin2','$2a$10$9eOIaFtzFIHJ69WslgrjieXUyPQz6F6T6SEp5vR0ZdkUHhw0mFoHK'),
    (6,'user1','$2a$10$nYOEzE1HJzi4E2vYpwuNF.kZTS38amb/FDCsvvY2y0byqRIA0lhmi'),
    (7,'user2','$2a$10$nYOEzE1HJzi4E2vYpwuNF.kZTS38amb/FDCsvvY2y0byqRIA0lhmi');

INSERT INTO ROLE_ASSIGNMENTS (ID_USER, ID_ROLE) VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 2),
  (5, 2),
  (6, 1),
  (7, 1);
