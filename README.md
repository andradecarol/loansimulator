<br/>

[![Spring](https://img.shields.io/badge/-Spring-%236DB33F?logo=Spring&logoColor=%23FFF)](https://spring.io/)
[![Swagger](https://img.shields.io/badge/-Swagger-%2385EA2D?logo=Swagger&logoColor=%23000)](https://swagger.io/)
[![Docker](https://img.shields.io/badge/-Docker-%232496ED?logo=Docker&logoColor=%23FFF)](https://www.docker.com/)



# ☁️ Microserviço Loan Simulator

Código feito com Java 17, Springboot e Maven.

Microserviço responsável por simular um empréstimo, mostando como resultado a parcela fixa a ser paga, o juros total pago e o valor total pago ao fim do empréstimo.

# 📚 Simulator


| METHOD | ENDPOINT | DESCRIPTION                                                                    | ESCOPE |
| --- | --- |--------------------------------------------------------------------------------| --- |
| **POST** | `/loan/v1/simulator` | Operação responsável por executar a simulação de empréstimo com base na idade. | <kbd>REQUEST</kbd>

# ✔️ Exemplo de requisição local
```
curl --location 'http://localhost:8080/loan/v1/simulator' \
--header 'Content-Type: application/json' \
--data '{
    "amount": 1000,
    "birthDate": "16/04/1991",
    "months": 10
}'
```

## 📐 Arquitetura

Esse microsserviço foi estruturado baseado em uma [arquitetura hexagonal](<https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)#:~:text=The%20hexagonal%20architecture%2C%20or%20ports,means%20of%20ports%20and%20adapters.>) seguindo a estrutura de pastas abaixo

```
  /src/main/java/com.simulator.loan
     /application
        /adapters   
            /controllers
                /swagger
    /config
    /domain
        /dto
        /entities
        /enums
        /exceptions
        /services
    /ports    
```

## ☕ Executando o projeto

### Compilar o projeto

```
mvn clean install
```

### Execução do microsserviço localmente com Docker

- É necessário ter o docker instalado para rodar o microsserviço através dele. Caso não tenha, você pode consultar a [documentação](https://docs.docker.com/) oficial para realizar a instalação.

```sh
cd docker && docker-compose up -d
```

### Execução do microsserviço localmente sem Docker

- Localize a classe LoanApplication, clique em cima dela com o botão direito e selecione a opção 'Run LoanApplication.main()'.

### Executando os **testes**

```sh
mvn test
```

### **Swagger**

```
http://localhost:{you-port}/loan/v1/swagger-ui/index.html#/
```
