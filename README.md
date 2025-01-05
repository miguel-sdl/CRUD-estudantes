# Sistema de Cadastro de Estudantes

## Descrição
Este é um sistema de cadastro de estudantes e suas notas, desenvolvido em `Java` utilizando **JDBC** para conexão com o banco de dados **MySQL**. O sistema permite que o usuario digite numeros para navegar por menus para realizar as operações **CRUD** (Create, Read, Update e Delete) nos registros de estudantes e suas notas.
<br><br>
O objetivo é colocar em prática os conhecimentos adquiridos da linguagem `Java` e banco de dados `MySQL`, demonstrando boas práticas com `Maven` para gerenciamento de dependências e `JUnit` para testes unitários.

## Funcionalidades
- **Cadastrar Estudantes**: Adicionar novos estudantes e notas ao banco de dados.
- **Consultar Estudantes**: Procurar por um estudante e suas notas no banco de dados.
- **Atualizar Informações**: Alterar informações de estudantes e suas notas no banco de dados.
- **Deletar Informações**: Remover informações de estudantes e suas notas do banco de dados.
- **Calcular médias**: Calcular médias de notas e verificar se o estudante está aprovado.

## Tecnologias Utilizadas
- Java 21
- Maven
- MySQL
- JUnit 5

## Estrutura do projeto
- **src/main/java/cadastroestudante**: Código fonte `Java`

  - `application`: Contém a classe principal `App` com o método `main` que executa a aplicação.
    
  - `conn`: Contém a classe `ConnectionFactory` que conecta com o banco de dados.
  - `entity`: Classes que representam as tabelas do banco de dados.
  - `repository`: Classes que interagem com o banco de dados.
  - `service`: Classes que capturam o input do usuário e fazem as verificações necessárias.
 
- **src/test/java**: Testes unitários com JUnit.
    
- **src/main/resources**: Recursos do projeto.
   - `db.properties`: Configuração do banco de dados,

- **db**: Scripts `SQL`.
   - `schema.sql`: Script de criação do banco de dados e tabelas.
   - `data.sql`: Script de inserção de dados iniciais.
 
## Como executar o projeto
**1. Clone o Repositório**
   ```sh
   git clone https://github.com/miguel-sdl/CRUD-estudantes.git
  ```

**2. Configure o banco de dados**
   - Certifique-se que o MySQL está instalado e executando.
   - Crie o banco de dados e as tabelas com o script `schema.sql`.
   - Insira os dados iniciais com o script `data.sql`.
   - Edite o arquivo `db.properties` localizado em `src/main/resources` inserindo as suas credenciais no banco de dados.

**3. Compile o projeto**
   ```sh
   mvn clean install
  ```

**4. Execute o programa**
   ```sh
   java -jar target/CRUD-estudantes-1.0-SNAPSHOT.jar
  ```

## Testes
Os testes implementados com **JUnit 5** cobrem:
- Teste de conexão com o banco de dados.
- Testes de operações **CRUD**.
- Testes de tratamento de exceções.
- Testes de verificação de médias de notas.
  
Para executar os testes:
  ```sh
  mvn test
  ```
No momento da compilação os testes são executados e como alguns fazem alterações no banco de dados, executar os testes mais de uma vez pode fazer com que alguns testes falhem. Para que os testes funcionem corretamente é necessário que os dados estejam exatamente como no arquivo `data.sql`.

## Autor
 - **Miguel Sousa Dela Libera**
  
