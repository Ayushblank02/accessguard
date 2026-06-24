CREATE TABLE access_requests (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    requested_at TIMESTAMP NOT NULL
);