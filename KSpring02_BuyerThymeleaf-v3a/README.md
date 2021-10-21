# Kotlin Spring Boot Project

## JPA (Java Persistence API, Architecture)
* 일반적인 DBMS CRUD는 SQL을 기반으로 한다
* 가급적 SQL을 적게 사용하기 위하여 MyBatis 등의 도구를 사용하지만 완전하게 SQL을 사용하지 않을 수는 없다
* JPA는 최소한 기본 CRUD는 아무런 SQL의 도움없이 구현할 수 있도록 만드는 기술이다
* ORM(Object-Relational-Mapping) 의 개념을 Spring에 도입한 것
* DB도 마치 메모리에 변수, 객체를 저장하는 것과 같은 방법으로 기본 Class를 사용하여 구현하고자 하는 기술
* JPA는 말 그대로 기술적인 개념이다.. Spring에서는 JPA를 구현하기 위하여 Interface 차원에서만 지원한다
* 보통은 JPA와 함께 Hibernate라는 구현체를 사용한다
* boot에서는 Hibernate는 JPA에 포함되어 구현된다

### jQuery import
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-latest.min.js></script>

## JPA DDL 설정

    spring.jpa.hibernate.ddl-auto=create

* JPA DDL 설정 :
    create, update, create-drop, validate, none 이 있다
* create : 기존의 table 을 삭제하고 다시 생성하기
* update : 기존 table 구조를 분석하여 변경사항을 alter table을 실행
    하지만 여러가지 제약조건으로 인해 실행이 되지 않는경우가 많음
* create-drop : 기존 table 을 삭제하고 다시 생성하고 프로젝트를 종료하면 table을 drop한다
* validate : Entity 칼럼의 설정값과 실제 table 의 구조가 다르면 프로젝트 실행을 멈춘다
* none : 아무것도 하지 말라는 의미
* 개발당시 : create, update 사용
* 자동화 된 test 진행(수시로 CRUD 를 구현) : create-drop
* 수동 test : update,validate 를 설정
* 실제 실행 서버 : validate 또는 none
