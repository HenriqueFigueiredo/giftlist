CREATE TABLE user (
    id CHAR(36) DEFAULT (UUID()) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE gift_list (
    id CHAR(36) DEFAULT (UUID()) PRIMARY KEY,
    user_id CHAR(36),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE gift (
    id CHAR(36) DEFAULT (UUID()) PRIMARY KEY,
    list_id CHAR(36),
    name VARCHAR(100) NOT NULL,
    url VARCHAR(255),
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guest_id CHAR(36),
    FOREIGN KEY (list_id) REFERENCES gift_list(id),
    FOREIGN KEY (guest_id) REFERENCES user(id)
);
