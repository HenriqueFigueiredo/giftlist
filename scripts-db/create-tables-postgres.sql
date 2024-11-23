CREATE TABLE user_app (
    id char(36) DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE gift_list (
    id char(36) DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id char(36),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user_app (id)
);

CREATE TABLE gift (
    id char(36) DEFAULT gen_random_uuid() PRIMARY KEY,
    list_id char(36),
    name VARCHAR(100) NOT NULL,
    url VARCHAR(255),
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guest_id char(36),
    FOREIGN KEY (list_id) REFERENCES gift_list(id),
    FOREIGN KEY (guest_id) REFERENCES user_app (id)
);