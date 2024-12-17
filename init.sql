CREATE TABLE test(
    test_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50),
    description TEXT
);

INSERT INTO test(name, description)
VALUES ('test', 'test');