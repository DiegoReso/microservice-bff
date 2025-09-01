![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33?style=flat-square&logo=spring-boot&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-2C3E50?style=flat-square&logo=lombok&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=flat-square&logo=github-actions&logoColor=white)
![Kanban](https://img.shields.io/badge/Kanban-007ACC?style=flat-square&logo=trello&logoColor=white)

# API Orquestradora BFF (Backend For Frontend)

## Visão Geral do Projeto

Este é o **microsserviço BFF (Backend for Frontend)**, um componente essencial da nossa arquitetura de microsserviços de gerenciamento de tarefas. Ele atua como um **orquestrador**, simplificando a comunicação entre a interface de usuário (frontend) e os demais serviços da aplicação.

Em vez de o frontend chamar múltiplos endpoints de diferentes serviços, o BFF consolida essas chamadas em um único ponto de acesso, otimizando o fluxo de dados e melhorando a performance e a experiência do usuário. Sua principal responsabilidade é agregar informações e rotear as requisições para os serviços corretos.

-----

### Tecnologias e Ferramentas

  * **Java ☕ e Spring Boot:** A base do desenvolvimento, fornecendo um ambiente robusto e ágil.
  * **Spring Cloud Feign:** Utilizado para criar clientes HTTP declarativos, facilitando a comunicação entre o BFF e os microsserviços de **usuários**, **tarefas** e **notificação**. Isso garante um fluxo de dados eficiente e um código limpo.
  * **Lombok:** Reduz o código repetitivo, tornando o desenvolvimento mais rápido e o código mais legível.
  * **Gradle:** Gerenciador de dependências e automação de build para um processo de desenvolvimento eficiente.
  * **GitHub Actions:** Automatiza o pipeline de CI/CD (Integração e Entrega Contínuas), assegurando deploys rápidos e confiáveis a cada atualização.

-----

### Comunicação com Outros Microsserviços

O BFF se comunica diretamente com os seguintes serviços:

  * **Microsserviço de Usuários:** Para autenticação (login, registro) e operações relacionadas a perfis de usuário.
  * **Microsserviço de Tarefas:** Para o gerenciamento completo de tarefas (CRUD).
  * **Microsserviço de Notificação:** Para o envio de e-mails de notificação, se necessário.

-----

Perfeito! Fiz a alteração. A tabela de endpoints agora está sem o prefixo `/api/v1`, refletindo a sua estrutura de rotas.

---

### Funcionalidades (Endpoints)

O BFF atua como um orquestrador, consolidando e simplificando o acesso aos endpoints dos microsserviços. O frontend se comunica apenas com o BFF, que redireciona as requisições para os serviços corretos.

| Método | Endpoint | Descrição | Microsserviço Consumido |
| :--- | :--- | :--- | :--- |
| `POST` | `/register` | Cria um novo usuário. | Usuários |
| `POST` | `/login` | Realiza a autenticação do usuário e gera um token JWT. | Usuários |
| `GET` | `/users` | Busca um usuário por e-mail. | Usuários |
| `PUT` | `/users` | Atualiza os dados principais de um usuário. | Usuários |
| `DELETE` | `/users/{email}` | Exclui um usuário do sistema. | Usuários |
| `POST` | `/users/address` | Adiciona um novo endereço a um usuário. | Usuários |
| `PUT` | `/users/address` | Atualiza um endereço existente de um usuário. | Usuários |
| `POST` | `/users/phone` | Adiciona um novo telefone a um usuário. | Usuários |
| `PUT` | `/users/phone` | Atualiza um telefone existente de um usuário. | Usuários |
| `POST` | `/tasks` | Cria uma nova tarefa. | Tarefas |
| `GET` | `/tasks/events` | Busca tarefas por período de tempo e status. | Tarefas |
| `GET` | `/tasks/search-by-email` | Obtém todas as tarefas de um usuário específico. | Tarefas |
| `DELETE` | `/tasks?id={id}` | Exclui uma tarefa pelo seu ID. | Tarefas |
| `PATCH` | `/tasks?status={status}&id={id}` | Atualiza o status de uma tarefa. | Tarefas |
| `PUT` | `/tasks?id={id}` | Atualiza os dados completos de uma tarefa. | Tarefas |
| `POST` | `/email` | Aciona o envio de uma notificação para uma tarefa. | Notificação |


-----

### Como Executar o Projeto Localmente

**Pré-requisitos:**

  * JDK 17 ou superior
  * Docker (para rodar os outros microsserviços)
  * Os microsserviços de **Usuários**, **Tarefas** e **Notificação** devem estar em execução.

**Passos:**

1.  Clone este repositório:
    ```bash
    git clone git@github.com:DiegoReso/microservice-bff.git
    cd microservice-bff
    ```
2.  Garanta que os outros microsserviços estão rodando e com as portas corretas (`8080`, `8081` e `8082`).
3.  Execute a aplicação usando Gradle:
    ```bash
    ./gradlew bootRun
    ```
4.  A API BFF estará disponível em `http://localhost:8083` (ou a porta configurada no seu `application.properties`).
