CREATE TABLE IF NOT EXISTS CLIENT (
    ID                                  UUID                NOT NULL,
    FIRST_NAME                          VARCHAR             NOT NULL,
    LAST_NAME                           VARCHAR             NOT NULL,
    MIDDLE_NAME                         VARCHAR             NOT NULL,
    PHONE                               VARCHAR             NOT NULL,
    CREATED_AT                          TIMESTAMP           NOT NULL,
    UPDATED_AT                          TIMESTAMP           NOT NULL,
    PRIMARY KEY (ID)
);


CREATE TABLE IF NOT EXISTS ACCOUNT (
    ID                                  UUID                NOT NULL,
    BALANCE                             NUMERIC(2)          NOT NULL,
    TYPE                                VARCHAR             NOT NULL,
    CREATED_AT                          TIMESTAMP           NOT NULL,
    UPDATED_AT                          TIMESTAMP           NOT NULL,
    PRIMARY KEY (ID)
);
