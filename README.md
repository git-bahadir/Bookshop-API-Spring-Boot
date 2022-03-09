# Bookshop-API-Spring-Boot
A bookshop backend using Spring Boot.

## Prerequisites

- Java 11 SDK (install via [sdkman](https://sdkman.io/install)).
- Nothing running on `8080` port.
- Your favorite client software (examples here are provided for curl).
- Postman exported calls are also available as a json file.

## How to run?

- Run postgres docker container with `docker-compose up -d`.
- Run `mvn clean isntall`.
- Run `mvn spring-boot:run`.


## Endpoints

### GET /books

Returns the list of books in the database.

#### Example:

`curl 'localhost:8080/api/v1/books'`

#### Response:

```json
[
  {
    "id": "647b28bf-11e6-4812-946f-f809983e13d3",
    "isbn": "4234678965111",
    "title": "hitchhiker's guide to the galaxy",
    "author": "douglas adams",
    "price": 42.3,
    "currency": "USD",
    "publisher": "qa",
    "datePublished": "2022-01-17T01:16:23.039+00:00",
    "tags": [
      "DRAMA",
      "HORROR",
      "FANTASY"
    ],
    "description": "",
    "imageUrl": "",
    "createdAt": "2022-01-17T01:16:23.095165Z",
    "updatedAt": "2022-01-17T01:16:23.122996Z",
    "stock": 3,
    "isActive": true
  },
  {
    "id": "19793b3f-09ff-4b02-9432-78dc56dcf8e0",
    "isbn": "1234678965111",
    "title": "The Lord of the Rings",
    "author": "J. R. R. Tolkien",
    "price": 12.3,
    "currency": "USD",
    "publisher": "qa",
    "datePublished": "2022-01-17T01:17:24.394+00:00",
    "tags": [
      "SCIFI",
      "COMEDY",
      "ACTION"
    ],
    "description": "",
    "imageUrl": "",
    "createdAt": "2022-01-17T01:17:24.402657Z",
    "updatedAt": "2022-01-17T01:17:24.404906Z",
    "stock": 3,
    "isActive": true
  }
]
```

### GET /books/find?author={author_name}

Searches the database for books by author name.

#### Example:

`curl 'localhost:8080/api/v1/books/find?author=douglas adams'`

#### Response:

```json
[
  {
    "id": "647b28bf-11e6-4812-946f-f809983e13d3",
    "isbn": "4234678965111",
    "title": "hitchhiker's guide to the galaxy",
    "author": "douglas adams",
    "price": 42.3,
    "currency": "USD",
    "publisher": "qa",
    "datePublished": "2022-01-17T01:16:23.039+00:00",
    "tags": [
      "DRAMA",
      "HORROR",
      "FANTASY"
    ],
    "description": "",
    "imageUrl": "",
    "createdAt": "2022-01-17T01:16:23.095165Z",
    "updatedAt": "2022-01-17T01:16:23.122996Z",
    "stock": 3,
    "isActive": true
  }
]
```


### GET /books/find?isbn={isbn_number}

Searches the database for book by isbn number. Throws a not found error (404) if the book is not found, will also throw a bad request error (400) if isbn formatting is not correct.

#### Example:

`curl 'localhost:8080/api/v1/books/find?isbn=4234678965111'`

#### Response:

```json
{
  "id": "647b28bf-11e6-4812-946f-f809983e13d3",
  "isbn": "4234678965111",
  "title": "hitchhiker's guide to the galaxy",
  "author": "douglas adams",
  "price": 42.3,
  "currency": "USD",
  "publisher": "qa",
  "datePublished": "2022-01-17T01:16:23.039+00:00",
  "tags": [
    "DRAMA",
    "HORROR",
    "FANTASY"
  ],
  "description": "",
  "imageUrl": "",
  "createdAt": "2022-01-17T01:16:23.095165Z",
  "updatedAt": "2022-01-17T01:16:23.122996Z",
  "stock": 3,
  "isActive": true
}
```

#### Example:

`curl 'localhost:8080/api/v1/books/find?isbn=4234678965112'`

#### Response with Status Code 404:
``` json
{
"message": "Book not found."
}
```


#### Example:

`curl 'localhost:8080/api/v1/books/find?isbn=423467896511'`

#### Response with Status Code 400:
``` json
{
    "message": "Validation Error on input: getBooksByIsbn.isbn: size must be between 13 and 13"
}
```


### GET /books/find?id={uuid_hash}

Searches the database for book by id number. Throws a not found error (404) if the book is not found, will also throw a bad request error (400) if id formatting is not correct.

#### Example:

`curl 'localhost:8080/api/v1/books/find?id=19793b3f-09ff-4b02-9432-78dc56dcf8e0'`

#### Response:

```json
{
    "id": "19793b3f-09ff-4b02-9432-78dc56dcf8e0",
    "isbn": "1234678965111",
    "title": "The Lord of the Rings",
    "author": "J. R. R. Tolkien",
    "price": 12.3,
    "currency": "USD",
    "publisher": "qa",
    "datePublished": "2022-01-17T01:17:24.394+00:00",
    "tags": [
        "SCIFI",
        "COMEDY",
        "ACTION"
    ],
    "description": "",
    "imageUrl": "",
    "createdAt": "2022-01-17T01:17:24.402657Z",
    "updatedAt": "2022-01-17T01:17:24.404906Z",
    "stock": 3,
    "isActive": true
}
```

#### Example:

`localhost:8080/api/v1/books/find?id=19793b3f-09ff-4b02-9432-78dc56dcf8e1'`

#### Response with Status Code 404:
``` json
{
"message": "Book not found."
}
```


#### Example:

`curl 'localhost:8080/api/v1/books/find?id=19793b3f-09ff-4b02-9432-78dc56dcf8e'`

#### Response with Status Code 400



### POST /books

Creates a Book in the database and returns a BookResponse object. Throws a bad request error (400) if the book is in correct format for BookInput object, will also throw an error if book with same isbn already exists.

#### Example:

`curl 'localhost:8080/api/v1/books` with a correct BookInput object

#### Response:

```json
{
    "id": "90dc7908-e942-4eed-80f3-c1beaa3495aa",
    "isbn": "5334678965111",
    "title": "The Dispossessed",
    "author": "Ursula K. Le Guin",
    "price": 12.3,
    "currency": "USD",
    "publisher": "qa",
    "datePublished": "2022-01-17T01:40:08.402+00:00",
    "tags": [
        "THRILLER"
    ],
    "description": "",
    "imageUrl": "",
    "createdAt": "2022-01-17T01:40:08.413355Z",
    "updatedAt": "2022-01-17T01:40:08.413354Z",
    "stock": 3,
    "isActive": true
}
```

#### Example:

`localhost:8080/api/v1/books` with an isbn attribute of 1234678965111

#### Response with Status Code 500:
``` json
{
    "message": "Book already exists."
}
```

#### Example:

`localhost:8080/api/v1/books` with a "" title attribute

#### Response with Status Code 400:
``` json
{
    "message": "Validation Error on Book object input: Validation failed for argument [0] in public org.springframework.http.ResponseEntity<com.bahadir.bookshopAPI.model.BookResponse> com.bahadir.bookshopAPI.controller.BookController.addBook(com.bahadir.bookshopAPI.model.BookInput): [Field error in object 'bookInput' on field 'author': rejected value []; codes [Size.bookInput.author,Size.author,Size.java.lang.String,Size]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [bookInput.author,author]; arguments []; default message [author],100,1]; default message [size must be between 1 and 100]] "
}
```



### PUT /books/{uuid_hash}/update

Updates an existing Book in the database and returns a BookResponse object. Throws an error if book does not exist.

#### Example:

`curl 'localhost:8080/api/v1/books/90dc7908-e942-4eed-80f3-c1beaa3495aa/update` with a correct BookInput object

#### Response:

```json
{
  "id": "90dc7908-e942-4eed-80f3-c1beaa3495aa",
  "isbn": "3333333333333",
  "title": "cvcvcvcvcvvc",
  "author": "xa",
  "price": 2.3,
  "currency": "USD",
  "publisher": "qa",
  "datePublished": "2022-01-17T01:45:29.761+00:00",
  "tags": [
    "SCIFI",
    "ACTION",
    "FANTASY"
  ],
  "description": "",
  "imageUrl": "",
  "createdAt": "2022-01-17T01:40:08.413355Z",
  "updatedAt": "2022-01-17T01:45:29.780032Z",
  "stock": 3,
  "isActive": true
}
```

#### Example:

`localhost:8080/api/v1/books/16b0f313-23c3-4fe2-8f1d-9612a29d16d5/update`

#### Response with Status Code 404:
``` json
{
    "message": "Book not found."
}
```

### PUT /books/{uuid_hash}/update?stock={stock}

Updates an existing Books stock information in the database and returns a BookResponse object. Throws an error if book does not exist.

#### Example:

`curl 'localhost:8080/api/v1/books/647b28bf-11e6-4812-946f-f809983e13d3/update?stock=50`

#### Response:

```json
{
  "id": "647b28bf-11e6-4812-946f-f809983e13d3",
  "isbn": "4234678965111",
  "title": "hitchhiker's guide to the galaxy",
  "author": "douglas adams",
  "price": 42.3,
  "currency": "USD",
  "publisher": "qa",
  "datePublished": "2022-01-17T01:16:23.039+00:00",
  "tags": [
    "DRAMA",
    "HORROR",
    "FANTASY"
  ],
  "description": "",
  "imageUrl": "",
  "createdAt": "2022-01-17T01:16:23.095165Z",
  "updatedAt": "2022-01-17T01:49:57.290702Z",
  "stock": 50,
  "isActive": true
}
```

#### Example:

`localhost:8080/api/v1/books/123b28bf-11e6-4812-946f-f809983e10d3/update?stock=50`

#### Response with Status Code 404:
``` json
{
    "message": "Book not found."
}
```

### DEL /books/{uuid_hash}/delete

Deletes an existing Book from the database. Throws an error if book does not exist.

#### Example:

`curl 'localhost:8080/api/v1/books/647b28bf-11e6-4812-946f-f809983e13d3/delete`

#### Response 204 No Content:


#### Example:

`localhost:8080/api/v1/books/b4d9a0bd-b042-4a89-8fa2-0c3605bf101a/delete`

#### Response with Status Code 404:
``` json
{
    "message": "Book not found."
}
```


## Approach and additional information
- As the initial step, I created the database schema and used the flyway tool to create database tables and create indexes for certain table attributes.
- I followed a Functional style in general as much as I can. No side effects, immutable variables.
- I validated input data and mapped objects through Controller, Service and Repository layers with Lombok builder.
- For inputs: BookInput -> Book -> BookEntity
- For response: BookEntity -> Book -> BookResponse
- I kept most of the business-related methods in a single place, separate from the rest of the service logic, and implemented most of them as pure functions.
- Postgres username and password is available in the docker-compose file.
- I wrote custom exceptions for some of the corner cases and handled them through the @ControllerAdvice annotation.
- I wrote some tests for the controller layer.
- Though the API is working fine test cases for POST and PUT throw a null exception that is related to object mapping which I could not solve.
- I also didn't document the test cases, sorry for that.
- I have exported the postman calls if you want to try out (book.postman_collection.json)
- My approach to the bonus question though I didn't implement it revolves around creating custom wrapper services for new shops as to keep our base Book functionality agnostic. We would have our Base Book model as the ground truth and dynamically convert the tags for different shops.
