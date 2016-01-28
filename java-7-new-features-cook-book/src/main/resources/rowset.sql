CREATE TABLE COLLEAGUES (
  ID INTEGER NOT NULL PRIAMY KEY,
  FIRSTNAME VARCHAR(30),
  LASTNAME VARCHAR(30),
  TITLE VARCHAR(10),
  DEPARTMENT VARCHAR(20),
  EMAIL VARCHAR(60)
);

INSERT INTO COLLEAGUES VALUES(1, 'Mike', 'Johnson','Manager', 'Engineering', 'mike.johnson@foot.com');
INSERT INTO COLLEAGUES VALUES(2, 'James', 'Still', 'Engineer', 'Engineering', 'james.still@foo.com');
INSERT INTO COLLEAGUES VALUES(3, 'Jerily', 'Stall', 'Manager', 'Marketing', 'jerilyn.stall@foo.com');
INSERT INTO COLLEAGUES VALUES(4, 'Jonathan', 'SMith', 'Manager', 'Marketing', 'jonathan.smith@foo.com');
