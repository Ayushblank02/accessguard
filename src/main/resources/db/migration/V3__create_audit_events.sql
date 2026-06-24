CREATE TABLE audit_events (
    id BIGSERIAL PRIMARY KEY,
    action VARCHAR(255) NOT NULL,
    actor VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);