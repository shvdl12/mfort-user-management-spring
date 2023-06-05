# Momsitter User Management Server

아래와 같은 사용자 관리 API를 제공합니다

- 시티/부모 회원가입
- 사용자 인증 및 토큰 발급
- 회원 정보 조회
- 회원 정보 업데이트
- 부모로도 활동하기
- 시터로도 활동하기

## 개발 환경

* Java 8
* Gradle 7.4
* Spring boot 2.6.8
* MariaDB 10.8.3

## 라이브러리

```gradle
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.1'

    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-security'

    testImplementation 'org.projectlombok:lombok:1.18.22'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'

    implementation group: 'io.hypersistence', name: 'hypersistence-utils-hibernate-55', version: '3.4.1'
    implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'

    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
    annotationProcessor 'org.projectlombok:lombok'
```

## 데이터베이스

공통 회원 테이블

```sql
CREATE TABLE TB_USER (
	USER_NUMBER INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(10) NOT NULL,
	BIRTH_AT DATE NOT NULL,
	GENDER VARCHAR(6) NOT NULL,
	USER_ID VARCHAR(20) NOT NULL UNIQUE,
	PASSWORD VARCHAR(100) NOT NULL,
	EMAIL VARCHAR(100) NOT NULL,
	CREATED_AT DATETIME NOT NULL
)
```

시터 회원 테이블

```sql
CREATE TABLE TB_SITTER (
	USER_NUMBER INT PRIMARY KEY, 
	MIN_CHILD_AGE int NOT NULL,
	MAX_CHILD_AGE int NOT NULL,
	BIO varchar(100),
	SITTER_CREATED_AT DATETIME NOT NULL,
	
	FOREIGN KEY (USER_NUMBER) REFERENCES TB_USER(USER_NUMBER)
);
```

부모 회 테이블

```sql
CREATE TABLE TB_PARENT (
	USER_NUMBER INT PRIMARY KEY,
	CHILDREN JSON NOT NULL,
	REQUIREMENTS VARCHAR(100),
	PARENT_CREATED_AT DATETIME NOT NULL,
	
	FOREIGN KEY (USER_NUMBER) REFERENCES TB_USER(USER_NUMBER)
);
```


