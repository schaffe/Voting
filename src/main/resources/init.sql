CREATE USER '**********' IDENTIFIED BY '**********';
GRANT ALL PRIVILEGES ON *.* TO '**********';
FLUSH PRIVILEGES;
DROP SCHEMA voting;
DROP SCHEMA security;
CREATE DATABASE voting CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE DATABASE security CHARACTER SET utf8 COLLATE utf8_unicode_ci;