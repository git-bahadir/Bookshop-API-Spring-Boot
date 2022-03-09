create table book (
    id uuid primary key,
    iisbn varchar(13) UNIQUE not null,
    title varchar(100) not null,
    author varchar(100) not null,
    price numeric(5,2) not null,
    currency varchar(3) not null,
    publisher varchar(100) not null,
    publish_date date not null,
    genre varchar(100) not null,
    description varchar(1000) not null
);