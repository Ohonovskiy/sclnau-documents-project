CREATE TABLE document_table (
                                id BIGINT NOT NULL AUTO_INCREMENT,
                                creation_date DATETIME(6),
                                name VARCHAR(255),
                                path VARCHAR(255),
                                group_id BIGINT,
                                user_id BIGINT,
                                PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE group_table (
                             id BIGINT NOT NULL AUTO_INCREMENT,
                             description VARCHAR(255),
                             image VARCHAR(255),
                             name VARCHAR(255),
                             PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE user_table (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            email VARCHAR(255),
                            password VARCHAR(255),
                            role TINYINT CHECK (role BETWEEN 0 AND 0),
                            PRIMARY KEY (id)
) ENGINE=InnoDB;

ALTER TABLE document_table
    ADD CONSTRAINT FK_document_group FOREIGN KEY (group_id) REFERENCES group_table (id);

ALTER TABLE document_table
    ADD CONSTRAINT FK_document_user FOREIGN KEY (user_id) REFERENCES user_table (id);
