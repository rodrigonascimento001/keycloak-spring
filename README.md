# keycloak-spring
Projeto exemplo autenticação usando keycloak com springboot

# Instalação do keycloak no docker
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:17.0.1 start-dev

# Configuração do keycloak
1 - criar uma role - "user"
2 - cria usuario "username"
3 - adiciona a role ao usuario criado, aba rolemappings do menu usuario
4 - criar client - > client aplicacao springboot "springboot-app"

# SpringBoot
- cria projeto springboot no spring initlzr depedencias spring web e thymeleaf;
- Cria um controller:
 ```java 
 @Controller
 public class WebResource {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/security")
    public String security(Model model){
        model.addAttribute("message","Olá! você está acessando o ambiente seguro do sistema.");
        return "security";
    }
}
```
- Criar o resources/templates/index.html:
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>Pagina Inicial</title>
</head>
<body>
<h1>Olá você está acessando o ambiente não logado do sistema</h1>
<p>Clique <a href="/security">aqui</a> para acessar </p>
</body>
</html>
```
- Criar o resources/templates/security.html:
```sh
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Área Protegida sistema</title>
</head>
<body>
<p th:text="${message}"></p>
</body>
</html>

```
# adicionando configurações de segurança

- Adiciona configurações autenticação keycloak no arquivo properties;
```shell
keycloak.auth-server-url=http://localhost:8080
keycloak.realm=master
keycloak.resource=springboot-app
keycloak.public-client=true
keycloak.security-constraints[0].authRoles[0]=user
keycloak.security-constraints[0].securityCollections[0].patterns[0]=/security/*
```
- Editar o pom.xml
```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.keycloak.bom</groupId>
            <artifactId>keycloak-adapter-bom</artifactId>
            <version>6.0.1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
```
<dependency>
  <groupId>org.keycloak</groupId>
  <artifactId>keycloak-spring-boot-starter</artifactId>
</dependency>

```
# execute o projeto:
```shell
mvn clean spring-boot:run
```




