DROP SCHEMA squid CASCADE;
CREATE SCHEMA squid;

CREATE TABLE squid.membres(
	id_membre SERIAL PRIMARY KEY,
	pseudo VARCHAR(25) NOT NULL,
	password VARCHAR(50) NOT NULL,
	estAdmin BOOLEAN)