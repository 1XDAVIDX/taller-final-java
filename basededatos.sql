create database tiendamascotasjava;
use tiendamascotasjava;
CREATE TABLE modelo (
    objeto VARCHAR(255) NOT NULL,
    IDs INT NOT NULL,
    precio INT NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (IDs)
);
select * from modelo;