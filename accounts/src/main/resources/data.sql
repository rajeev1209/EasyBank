DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS accounts;

CREATE TABLE `customer`
(
`customer_id` int AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(100) not null,
`email` VARCHAR(100) not null,
`mobile_number` VARCHAR(20) not null,
`create_dt` date default NULL
);


CREATE TABLE `accounts`
(
`customer_id` int not null,
`account_number` int AUTO_INCREMENT PRIMARY KEY,
`account_type` VARCHAR(100) not null,
`branch_address` VARCHAR(200) not null,
`create_dt` date default NULL
);

INSERT INTO `customer` (`name`,`email`,`mobile_number`,`create_dt`)
VALUES ('Rakesh','rakesh@gmail.com','1234567890',CURRENT_DATE);

INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`)
VALUES (1, 186576453, 'Savings', '123 Main Street Ontario', CURRENT_DATE);

