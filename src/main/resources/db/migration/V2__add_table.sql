create TABLE apartment_application
(
    id                               BIGINT       NOT NULL,
    house_type                       VARCHAR(255) NOT NULL,
    rent_or_sale                     VARCHAR(255) NOT NULL,
    constructor_company              VARCHAR(255) NOT NULL,
    special_supply_date              datetime     NULL,
    first_priority_application_date  datetime     NOT NULL,
    second_priority_application_date datetime     NOT NULL,
    CONSTRAINT pk_apartmentapplication PRIMARY KEY (id)
);



create TABLE comments
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    user_id    BIGINT                NULL,
    post_id    BIGINT                NULL,
    contents   VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_comments PRIMARY KEY (id)
);

create TABLE housing_application
(
    id                       BIGINT AUTO_INCREMENT NOT NULL,
    htype                    VARCHAR(31)           NULL,
    created_at               datetime              NULL,
    updated_at               datetime              NULL,
    region                   VARCHAR(255)          NOT NULL,
    name                     VARCHAR(255)          NOT NULL,
    road_address             VARCHAR(255)          NOT NULL,
    house_latitude           DOUBLE                NOT NULL,
    house_longitude          DOUBLE                NOT NULL,
    announcement_date        datetime              NOT NULL,
    start_date               datetime              NOT NULL,
    end_date                 datetime              NOT NULL,
    winner_announcement_date datetime              NOT NULL,
    contact                  VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_housingapplication PRIMARY KEY (id)
);

create TABLE housing_transaction
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    created_at        datetime              NULL,
    updated_at        datetime              NULL,
    city_district     VARCHAR(255)          NOT NULL,
    name              VARCHAR(255)          NOT NULL,
    road_address      VARCHAR(255)          NOT NULL,
    house_latitude    DOUBLE                NOT NULL,
    house_longitude   DOUBLE                NOT NULL,
    price             VARCHAR(255)          NOT NULL,
    area              DOUBLE                NOT NULL,
    construction_year INT                   NOT NULL,
    transaction_date  VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_housing_transaction PRIMARY KEY (id)
);

create TABLE non_apartment_application
(
    id                 BIGINT       NOT NULL,
    non_apartment_type VARCHAR(255) NOT NULL,
    executor_company   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_nonapartmentapplication PRIMARY KEY (id)
);

create TABLE other_apt_random_application
(
    id                    BIGINT       NOT NULL,
    executor_company      VARCHAR(255) NOT NULL,
    other_apt_random_type VARCHAR(255) NOT NULL,
    special_supply_date   datetime     NULL,
    general_supply_date   datetime     NULL,
    CONSTRAINT pk_otheraptrandomapplication PRIMARY KEY (id)
);

create TABLE posts
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    user_id       BIGINT                NULL,
    contents      VARCHAR(255)          NOT NULL,
    comment_count INT                   NOT NULL,
    CONSTRAINT pk_posts PRIMARY KEY (id)
);



alter table apartment_application
    add CONSTRAINT FK_APARTMENTAPPLICATION_ON_ID FOREIGN KEY (id) REFERENCES housing_application (id);

alter table comments
    add CONSTRAINT FK_COMMENTS_ON_POST FOREIGN KEY (post_id) REFERENCES posts (id);

alter table comments
    add CONSTRAINT FK_COMMENTS_ON_USER FOREIGN KEY (user_id) REFERENCES app_user (id);

alter table non_apartment_application
    add CONSTRAINT FK_NONAPARTMENTAPPLICATION_ON_ID FOREIGN KEY (id) REFERENCES housing_application (id);

alter table other_apt_random_application
    add CONSTRAINT FK_OTHERAPTRANDOMAPPLICATION_ON_ID FOREIGN KEY (id) REFERENCES housing_application (id);

alter table posts
    add CONSTRAINT FK_POSTS_ON_USER FOREIGN KEY (user_id) REFERENCES app_user (id);