
<h1 align="center"> Moview App </h1> <br>

<h3 align="center">
 Moview - Aplicação de Avaliação de Filmes
</h3>


## Sumário

- [Sumário](#sumário)
- [Introdução](#introdução)
- [Features](#features)
- [API:](#api)
- [Requisitos](#requisitos)
    - [Local](#local)

## Introdução

![Java](https://img.shields.io/badge/-java-E34A86?style=flat&logo=java)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=flat&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-sql)

Microserviço desenvolvido para a aplicação Moview. 

## Features


## API:

Endpoints disponíveis na aplicação:

###User


| **Verbo** | **Descrição** | **URL**                                                                    | 
| ----------|---------------------------------------------------------------------------------------------|------------------|
| **/POST**  | ``/usuarios/cadastrar``                 | Cadastrar Usuario
| **/POST**  | ``/usuarios/logar` |        | Login
| **/PUT**  | ``/usuarios/alterar``                                  | Alterar dados do usuario
| **/DELETE**  | ``/usuarios/excluir/{id}``                          |    Excluir Usuario |


###Movies


| **Verbo** | **Descrição** | **URL**                                                                    | 
| ----------|---------------------------------------------------------------------------------------------|------------------|
| **/GET**  | ``/`                 |
| **/GET**  | ``/` |        | 
| **/POST**  | ``/u``                                  | 
| **/POST*  | ````                          |   |
| **/PATCH**  | ````                                  | 
| **/PATCH*  | ````                          |   |
| **/PATCH*  | ```                                  | 


## Requisitos
Essa aplicação pode ser rodada localmente:
### Local
* Java 17 SDK
* Maven
* Postgres SQL

```bash
$ mvn clean install
$ mvn spring-boot:run -Dspring-boot.run.profiles=LOCAL
```

## Teste
*Esse serviço possui API's em HTTP, para testar localmente as API's REST utilizar o client **Insomnia** .

## Infraestrutura
*Essa aplicacao funciona em conjunto com a API SecurityMoview. Para o correto funcionamento sera necessario rodar as duas aplicacoes.
