# Desafio Técnico Backend Effecti

Esse é o back end desenvolvido para o desafio técnico da empresa Effecti.

# Contextualização do Problema

A Effecti é uma plataforma para licitantes, então o desafio era construir um software para capturar as licitações disponíveis em https://www.agrolandia.sc.gov.br/licitacoes e disponibilizá-las através de uma api, com a possibiidade de marcar uma licitação como lida.

## Descrição da API

- GET - /bids
  - Descrição: Retorna a lista de licitações
  - Headers: 
    - "jwt": "string"
  - Query Params: 
    - "modality": "string" (Opcional, usado para filtrar a busca das licitações)
  - Respostas: 
      - 200 OK - Content: 
            [
              {
                "id": 0,
                "modalidade": "string",
                "nome": "string",
                "link": "string",
                "status": "string",
                "dataDeAbertura": "YYYY-MM-DD",
                "descricao": "string",
                "entidade": "string",
                "setor": "string",
                "local": "string",
                "valorGlobal": double,
                "editais": [
                  {
                    "name": "string",
                    "link": "string"
                  }
                ],
                "lida": boolean
              }
            ]
      - 400 BAD REQUEST - Content: { "type": "BadRequestException", "message": "Wrong format of JWT"}
      - 401 UNAUTHORIZED - Content: { "type": "UnauthorizedException", "message": "JWT error"}
  
- POST - /bids/{id}/read
  - Descrição: Marca uma licitação como lida pelo usuário
  - Headers: ["jwt": "string"]
  - Respostas: 
      - 200 OK
      - 400 BAD REQUEST - Content: { "type": "BadRequestException", "message": "Wrong format of JWT"}
      - 401 UNAUTHORIZED - Content: { "type": "UnauthorizedException", "message": "JWT error"}
      - 404 NOT FOUND - Content: { "type": "NotFoundCpfException", "message": "Bid not found"}
               
- POST - /users
  - Descrição: Criar um usuário
  - Parâmetros: { "name": "username" }
  - Respostas: 
      - 201 CREATED 
      - 409 CONFLICT - Content: { "type": "ConflictException", "message": "User already exist"}
               
- POST - /users/{name}/authenticate
  - Descrição: Autenticar um usuário
  - Respostas: 
      - 204 OK - Content: { "token": "jwt"}
      - 404 NOT FOUND - Content: { "type": "NotFoundException", "message": "User does not exist"}

## Tecnologias

As seguintes ferramentas e frameworks foram usados na construção do projeto:<br>

  ![JAVA 17](https://img.shields.io/badge/JAVA-17-%23E34F26.svg?style=for-the-badge)
  ![SPRINGBOOT 3](https://img.shields.io/badge/SPRINGBOOT-3-%231572B6.svg?style=for-the-badge)
  ![MAVEN](https://img.shields.io/badge/MAVEN-%23323330.svg?style=for-the-badge)
  ![LOMBOK](https://img.shields.io/badge/LOMBOK-%2320232a.svg?style=for-the-badge)
  ![JUNIT](https://img.shields.io/badge/JUNIT-CA4245?style=for-the-badge)
  ![MOCKITO](https://img.shields.io/badge/MOCKITO-DB7093?style=for-the-badge)
  ![ASSERTJ](https://img.shields.io/badge/ASSERTJ-6DA55F?style=for-the-badge)
  ![MAPSCTRUCT](https://img.shields.io/badge/MAPSCTRUCT-%23404d59.svg?style=for-the-badge)
  ![OPENAPI](https://img.shields.io/badge/OPENAPI-%23316192.svg?style=for-the-badge)
  ![POSTGRES](https://img.shields.io/badge/POSTGRES-3982CE?style=for-the-badge)
  ![JSOUP](https://img.shields.io/badge/JSOUP-%23007ACC.svg?style=for-the-badge)
  ![SLF4J](https://img.shields.io/badge/SLF4J-%23E5E5E5?style=for-the-badge)
  ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
  ![JWT](https://img.shields.io/badge/JWT-%23C21325?style=for-the-badge)
  
## Por quê?

A API foi desenvolvida em Java por ser a linguagem da vaga aplicada, as versões 17 do Java e 3 do Spring Boot foram escolhidas por serem as mais recentes, enquanto o maven foi escolhido por ser a tecnologia mais popular para gerenciamento de pacotes do Java. <br/>
As ferramentas de testes como JUnit, Mockito e AssertJ também foram escolhidas pela popularidade na comunidade, e possibilitam juntas uma gama muito grande de métodos para realizar as asserções nos testes e mocks para realizar testes unitários com qualidade.<br/>
As ferramentas auxiliares como Lombok, Mapstruct e SLF4J também são muito populares, Lombok e Mapstruct são ótimas para reduzir código desnecessários na aplicação fornecendo respectivamente anotações que geram códigos em tempo de execução e metódos que fazem mapeamento automático entre objetos. Já o SLF4J fornece um fachada de logging para várias implementações de log técnicos (INFO, ERROS, DEBUG, etc.)<br/>
A escolha do banco de dados para o Postgres se deu pela necessidade de manter alguns relacionamento entre os dados, além de ser o banco de dados usado na empresa. Enquanto a OpenAPI do Swagger auxilia na documentação e visualização dos elementos da API.<br/>
Por ultimo, a JSOUP é uma biblioteca Java para análise e manipulação de documentos HTML. Permite a seleção de elementos HTML usando seletores CSS, o que facilita muito a extração de dados específicos do HTML.

## Como rodar

1. Clone o repositório

2. Acesse a pasta em que foi clonado

3. Instale o docker
```bash
 sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin

```

4. Dê permissão para rodar o Docker com seu usuário corrente:
```bash
 sudo usermod -aG docker $USER

```

5. Garanta que o docker esteja rodando
```bash
sudo service docker start
```

6. Rode os containers com
```bash
docker compose up --build -d
```

7. Para verificar os containers em execução
```bash
docker ps
```

8. Acesse http://localhost:8080/swagger-ui/index.html para ver a documentação da api com o Swagger
