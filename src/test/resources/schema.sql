CREATE TABLE job_titles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE SEQUENCE IF NOT EXISTS employees_id_seq;

CREATE TABLE employees (
    id INT DEFAULT NEXT VALUE FOR employees_id_seq PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    salary DECIMAL(10, 2),
    job_title_id INT,
    FOREIGN KEY (job_title_id) REFERENCES job_titles(id)
);

INSERT INTO job_titles (name) VALUES ('Software Engineer');
INSERT INTO employees (first_name, last_name, salary, job_title_id) VALUES ('Jon', 'Doe', 130000.00, 1);
