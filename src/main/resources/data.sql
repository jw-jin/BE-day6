DROP TABLE IF EXISTS version;

CREATE TABLE VERSION
(
    id      BIGINT      NOT NULL AUTO_INCREMENT,
    os_info    VARCHAR(20) NOT NULL,
    service_version VARCHAR(255) NOT NULL,
    service_name VARCHAR(255) NOT NULL,
    update_type VARCHAR(255) NOT NULL,
    message VARCHAR(255) NOT NULL,
    package_info VARCHAR(255) NOT NULL,
    service_version VARCHAR(255) NOT NULL,
    service_version VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO VERSION
