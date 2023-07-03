# webflux-start
스프링 진영의 리액티브 프로그래밍을 위한 프로젝트인 webflux를 공부합니다.

## r2dbc with h2, mysql
DB 벤더사들이 non blocking 처리를 위해 r2dbc 연결을 위한 구현체들을 제공하고 있다. 저는 h2, mysql 두 환경에서 프로젝트를 진행해보았습니다.
gradle 환경을 가정합니다.

### 1. h2

1. `build.gradle` r2dbc-h2 프로젝트 추가 
```
//... some implements

compileOnly 'io.r2dbc:r2dbc-h2' // 추가
```
h2-console을 사용하려면 complieOnly 여야 합니다. 

2. `application.yml` or `application.properties`
```
spring:
  h2:
    console:
      enabled: true
      path: /h2-console // 사용하고 싶은 path를 사용해도 됩니다.
      port: 8090 // 사용하고 싶은 포트로 변경해도 됩니다.
  r2dbc:
    url: r2dbc:h2:mem:///testdb;
    username: sa
    password:
```
  
3. `H2Server.java` 추가
```
@Component
public class H2Server {
    @Value("${spring.h2.console.port}")
    private Integer port;
    private Server webServer;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        this.webServer = Server.createWebServer("-webPort", port.toString()).start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        this.webServer.stop();
    }
}
```

### 2. MySQL
도커를 사용하지 않았다. R2DBC 프로젝트와 호환이 문제인지, 내가 설정을 잘못한 탓인지 문제가 잘 해결되지 않아서 도커는 사용하지 않도록 했다. <br>
마침 내 노트북에 MySQL 8.0.32가 설치되어 있어서 사용했다.

1. `build.gradle`
```
implementation 'com.github.jasync-sql:jasync-r2dbc-mysql:2.2.0' // 추가
```
mysql에서 r2dbc 연동을 위해 2가지 구현체를 제공하는데 이걸 쓰는게 더 좋아보인다. 다른 하나는 마지막 릴리즈가 2020년으로 관리를 멈춘 상태로 보인다.


2. `application.yml` or `application.properties`
```
  r2dbc:
    username: root
    password: {password}
    url: r2dbc:mysql://localhost:3306/webflux?createDatabaseIfNotExist=true&serverTimezone=UTC // host, port 및 그외 query param 들은 모두 입맛에 맞게 사용하자.
```

## table schema
r2dbc는 jpa 구현체인 하이버네이트에서 존재하는 ddl-auto 기능이 없습니다. 따라서 직접 table schema를 작성해야 합니다. 
아래는 프로젝트에서 사용하는 테이블 스키마입니다. mysql Command Line Client 에 접속해서 직접 해당 SQL을 실행해도 되고 spring 에서 제공하는 sql.init 기능을 사용해도 됩니다. 

```
CREATE TABLE IF NOT EXISTS XUNI_GROUP (
    GROUP_ID bigint NOT NULL AUTO_INCREMENT,
    NAME varchar(50) NOT NULL ,
    CAPACITY int UNSIGNED NOT NULL ,
    LEFT_CAPACITY int UNSIGNED NOT NULL,
    PRIMARY KEY (GROUP_ID)
);
```

## etc
그 외 프로젝트를 진행하면서 마주하는 문제들은 wiki에 정리해두도록 하겠습니다.
