## 

## SD Conecta Desafio

### Tecnologias Utilizadas

- Java
- Spring Boot 
- Spring Data JPA
- Hibernate
- Banco de Dados H2

### Entidades

Esse projeto consiste em duas entidades que serão usadas: User e CRM.

#### User

Atributos:

- ID (INT) --> Chave Primaria
- Email (STRING)
- Password (STRING)
- Name (STRING)
- Surname (STRING)
- Mobile Phone (STRING)

#### CRM

Atributos:

- ID (INT) --> Chave Primaria
- CRM (STRING)
- UF (STRING)
- Specialty (DATE)
- User ID --> Chave Estrangeira para Entidade User

### Requisitos de API

#### Requisitos de User

##### GetAll

URL do Requisito: http://localhost:8080/api/users

##### GetById

URL do Requisito: http://localhost:8080/api/users/{id}

##### Create

URL do Requisito: http://localhost:8080/api/users

##### Update

URL do Requisito: http://localhost:8080/api/users/{id}

##### Delete

URL do Requisito: http://localhost:8080/api/users/{id}

##### DeleteAll

URL do Requisito: http://localhost:8080/api/users

#### Requisitos de CRM

##### GetAll

URL do Requisito: http://localhost:8080/api/users/{userId}/crms

##### GetById

URL do Requisito: http://localhost:8080/api/users/{userId}/crms/{id}

##### Create

URL do Requisito: http://localhost:8080/api/users/{userId}/crms

##### Update

URL do Requisito: http://localhost:8080/api/users/{userId}/crms/{id}

##### Delete

URL do Requisito: http://localhost:8080/api/users/{userId}/crms/{id}

##### DeleteAll

URL do Requisito: http://localhost:8080/api/users/{userId}/crms

##### GetBySpecialty

URL do Requisito: http://localhost:8080/api/users/{userId}/crms/{specialty}

##### 