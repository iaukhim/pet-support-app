DROP SCHEMA IF EXISTS `pet_db`;

CREATE SCHEMA `pet_db`;

CREATE TABLE pet_db.accounts (
	`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL DEFAULT ' ',
    `surname` VARCHAR(255) NOT NULL DEFAULT ' ',
    `phone_number` VARCHAR(255) NOT NULL DEFAULT ' ',
    `date_of_birth` DATE,

    CONSTRAINT `unique_email` UNIQUE (`email`)
);

INSERT INTO pet_db.accounts (`email`, `password`) VALUES ('customer@mail.com', 'test123');

CREATE TABLE pet_db.managers_accounts(
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,

    CONSTRAINT `unique_email` UNIQUE (`email`)
);

INSERT INTO pet_db.managers_accounts (`email`, `password`) VALUES ('manager@mail.com', 'test123');

CREATE TABLE pet_db.product_types(
	`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `type` varchar(255) NOT NULL,

    CONSTRAINT unique_type UNIQUE (`type`)
);

INSERT INTO pet_db.product_types (`type`) VALUE ('laptop');
INSERT INTO pet_db.product_types (`type`) VALUE ('smartphone');
INSERT INTO pet_db.product_types (`type`) VALUE ('monitor');
INSERT INTO pet_db.product_types (`type`) VALUE ('printer');


CREATE TABLE pet_db.products(
	`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `type` varchar(255) NOT NULL,
    `model` varchar(255) NOT NULL,

    CONSTRAINT unique_model UNIQUE (`model`),
    CONSTRAINT product_type FOREIGN KEY (`type`) REFERENCES pet_db.product_types(`type`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO pet_db.products (`type`, `model`) VALUES ('laptop', 'IdeaPad 3 15ITL05');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('laptop', 'ProBook 455 G8');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('laptop', 'Pavilion 15-eh0005ur');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('laptop', 'Legion 5 15IMH6');

INSERT INTO pet_db.products (`type`, `model`) VALUES ('smartphone', 'Redmi Note 11 4GB/64GB');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('smartphone', 'Galaxy Z Flip3 5G 8GB/256GB');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('smartphone', '50 8GB/128GB');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('smartphone', 'Galaxy S21 FE 5G');

INSERT INTO pet_db.products (`type`, `model`) VALUES ('monitor', '27UL500-W');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('monitor', 'UltraGear 24GN600-B');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('monitor', 'Mi Desktop Monitor 27');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('monitor', '24G2U/BK');

INSERT INTO pet_db.products (`type`, `model`) VALUES ('printer', 'Laser 107w');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('printer', 'Mi Portable Photo Printer');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('printer', 'i-SENSYS MF3010');
INSERT INTO pet_db.products (`type`, `model`) VALUES ('printer', 'LaserJet Pro M28a');


CREATE TABLE pet_db.owned_products(
	`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `type` varchar(255) NOT NULL,
    `model` varchar(255) NOT NULL,
    `owner_id` INTEGER NOT NULL,
    `serial_number` varchar(255) NOT NULL,

    CONSTRAINT unique_serial_number UNIQUE (`serial_number`),
    CONSTRAINT product_type_fk FOREIGN KEY (`type`) REFERENCES pet_db.product_types(`type`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT model_type FOREIGN KEY (`model`) REFERENCES pet_db.products(`model`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT owner_id FOREIGN KEY (`owner_id`) REFERENCES pet_db.accounts(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE pet_db.tickets(
	`id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `status` BOOLEAN NOT NULL,
    `product_id` INTEGER NOT NULL,
    `theme` varchar(255) NOT NULL,
	`text` LONGTEXT NOT NULL,
    `starter_id` INTEGER NOT NULL,
    `manager_id` INTEGER NOT NULL DEFAULT 0,

    CONSTRAINT `starter_id` FOREIGN KEY (`starter_id`) REFERENCES pet_db.accounts(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES pet_db.owned_products(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

select * from pet_db.accounts;