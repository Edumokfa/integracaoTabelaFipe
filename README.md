# integracaoTabelaFipe

Link para download do Docker: https://www.docker.com/. <br/>
Link para download do Apache Maeven: https://maven.apache.org/download.cgi. <br/>
Link para download do JDK 18: https://jdk.java.net/archive/ <br/>

Comandos para executar a aplicação: <br/>

Configuração do banco de dados: <br/>

`docker pull mongo:latest`
`docker run -v ~/docker --name mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=integraFipe -e MONGO_INITDB_ROOT_PASSWORD=masterkey mongo`

após executar o banco de dados, é necessário abrir o terminal e se posicionar no diretório do projeto <br/>
Quando estiver no diretório do projeto, execute os seguintes comandos:
`mvn clean install`
`docker build -t integracao-tabela-fipe .`
`docker run -p 8080:8080 --name integracao-tabela-fipe-mongodb --link integracaoFipeMongodb:mongo -d integracao-tabela-fipe:1.0`

Caso tudo funcione perfeitamente, a api está sendo executada.

É possível acessar a documentação em:
`http://localhost:8080/swagger-ui/index.html#/`