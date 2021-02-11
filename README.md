# Projeto Gerenciador de contas

Desenvolvi uma solução para gerenciar as operações para uma conta. Neste serviço estão contemplados as seguintes funcionalidades:

- Registrar uma conta;
- Registrar uma operação de transferencia entre contas;
- Registrar um deposito para uma conta;
- Consultar um extrato de uma conta;
- Consultar os dados e saldo da conta.

## Documentação para construir a solução

Neste casos, antes de construir a solução (código), gosto de analisar e pensar como será a modelagem das api's, banco de dados e solução inicial de arquitetura.
Por isso tive a iniciativa em desenhar a solução para ilustrar o meu pensamento de como desenvolverei a solução através das imagens abaixo:

### Modelagem da API

![API](/model_api.jpg)


### Modelagem do Banco de Dados

![Database](/model_db.jpg)

### Arquitetura

![Architecture](/model_architecture.jpg)

## Reflexão sobre o problema

Para construir a aplicação utilizei o framework Micronault, por ser ter um start rápido para subir a aplicação e poder usar o GraalVM nativamente.

Para este projeto não será há necessidade de instalar as ferramentas para fazer o build e o deploy. Neste caso estou a utilizar o wrapper do maven que está embutido no projeto e docker-compose que monta o container do banco de dados e da aplicação. 

Os banco de dados é o Mysql, por que, é importante destacar que usei o modelo relacional para trabalhar o conceito ACID.

Por entender que o mundo de desenvolvimento esta globalizado, utilizei como idioma o Inglês para escrever o código e as apis.

## Tecnologias utilizadas

* Linguagem Java - Versão 11 (JDK GraalVM)

``` shell script
openjdk version "11.0.10" 2021-01-19
OpenJDK Runtime Environment GraalVM CE 21.0.0 (build 11.0.10+8-jvmci-21.0-b06)
OpenJDK 64-Bit Server VM GraalVM CE 21.0.0 (build 11.0.10+8-jvmci-21.0-b06, mixed mode, sharing)
```

* Maven 3 - Ferramenta de Build

``` shell script
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /Users/andferreira/.m2/wrapper/dists/apache-maven-3.6.3-bin/1iopthnavndlasol9gbrbg6bf2/apache-maven-3.6.3
Java version: 11.0.10, vendor: GraalVM Community, runtime: /Users/andferreira/.sdkman/candidates/java/21.0.0.r11-grl
Default locale: en_BR, platform encoding: UTF-8
OS name: "mac os x", version: "10.16", arch: "x86_64", family: "mac"
```

* Repositório e versão de código - Github e Git;

* Migration - Flayway;

* Banco de Dados -  Mysql (docker para aplicação e para os testes);

* Server - Netty;

* Micronault - Framework para desenvolver o backend.


## Documentação da API

* Depois

## Para realizar o build e os testes do programa

Primeiro passo faça o clone do projeto e depois siga os passos abaixo.

Segue:

Acesse o projeto

```
cd manager-account
```

### Executar os testes

Execute na raiz do comando para rodar os testes:

```shell script
./mvnw test
```

ou

```shell script
 docker run --rm -v $PWD:/app -w /app maven:3.6.3-jdk-11 mvn clean test 
```

### Executar o build

Para executar o build:

```shell script
./mvnw clean package
```

ou

```shell script
 docker run --rm -v $PWD:/app -w /app maven:3.6.3-jdk-11 mvn clean package 
```

### Executar o deploy

Primeiro crie o banco de dados:

```shell script
docker-compose up db-service
```

Fazer o deploy da aplicação:

```shell script
docker-compose up --build app-service
```

Se tudo ocorreu bem acesso o ``http://localhost:9090/health`` para verificar se aplicação esta no ar.

## Gestão do Projeto e técnicas para construção da API

Não precisei usar Kaban para administrar as atividades, mas sempre me foquei na documentação passada.

As etapas foram:

*  Criação do projeto no https://micronaut.io/launch/;
*  Construção das migrations para criação das tabelas;
*  Construção da camada de domínio;
*  Construção da camada de aplicação;
*  Construção da camada de infraestrutura;
*  Construção dos testes integrados;

Comecei a aplicação pensando em TDD para me ajudar a construir e validar alguns cenários para entradas das apis.

Pode acessar a página de PR para histórico da construção. Página: ``https://github.com/ander-f-silva/manager-account/pulls?q=is%3Apr+is%3Aclosed`` 

Pode acessar o backlog dos projetos ``https://github.com/ander-f-silva/manager-account/projects/1``