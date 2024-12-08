Read Me 추가

----------------------------java 버전 별 차이-------------------------------
LTS(Long-Term Support) 
- 5년 이상 지원 기간 제공


Java 8 (2014년 3월 출시) → 가장 널리 사용되는 LTS 버전 중 하나로, 2025년까지 지원
- 32비트 지원하는 공식적인 마지막 버전

- LocalDateTime 과 같은 새로운 날짜 시간 API 제공

- Unsigned Integer 계산

- 람다식제공

- Stream API 라는 Collection 타입에 대한 API 제공

- Perm Gem 영역 삭제 



Perm 영역 -> Metaspace 영역으로 전환되고 기존 Perm 영역 사라짐

Metaspace 영역은 Heap 영역이 아닌 Native 영역으로 취급

Heap 영역 : JVM에 의해 관리, Native 메모리는 OS 레벨에서 관리 영역



PermGen 영역을 사용하던 단점 

-PermGen 영역이 꽉 차면  java.lang.OutOfMemoryError: PermGen space 오류가 발생



이후 동적으로 크기 조절할 수 있는 Metaspace로 변경


Java 11 (2018년 9월 출시) → 2026년까지 지원
- 람다 지역 변수 사용방법 변경 (var)

- Oracle JDK가 유료화 -> OpenJDK, AdoptOpenJDK 와 같은 대체 JDK 사용

- 11버전부터 도입된 Java HttpClient API는 최신 HTTP 표준의 클라이언트를 구현

- 동기 및 비동기 프로그래밍 모델인 HTTP/1.1 및 HTTP/2를 지원

- Epsilon Garbage Collector : 새로운 가비지 컬렉터로, 가비지 컬렉션을 완전히 비활성화하는 방식

- 메모리 해제와 관련된 최소한의 작업만 수행하고, 애플리케이션에서 메모리를 수동으로 관리

- 컴파일 없이 자바소스 실행가능





Java 17 (2021년 9월 출시) → 2029년까지 지원
- Spring Boot 3.0은 Spring Framework 6을 기반으로 하여 최소 Java 17을 사용해야한다 명시

- 보안과 관련된 다양한 작업을 제어하고 제한하는 Security Manager 사용중단

- 텍스트 블록기능

- Record Data Class 추가 

- Sealed Classes

- Stream.toList()





Java 21 (2023년 9월 출시) → 2031년까지 지원
- Spring Boot 3.2부터 지원

- Java 플랫폼에 경량 가상 스레드 도입

- UTF- 8 기본값


----------------------------유효성검사 spring validation-------------------------------
validation : 검증
-올바르지 않은 데이터를 걸러내고 보안 유지를 위해
-client 데이터는 조작이 쉽고 정상적 방식의 데이터만 들어오는 것이 아니기에 client side 뿐만 아니라 server side에서도 필요

@Valid : java 지원 어노테이션
@Validated : Spring 지원 어노테이션
@Validated는 @Valid 기능 포함, 유효성 검토할 그룹 지정할 수 있는 기능 추가로 가지고 이다.

Bean validation : 클래스 필드에 특정 annotation을 적용하여 필드가 갖는 제약조건을 정의하는 구조로 이루어진 검사 

1.gradle 추가
implementation 'org.springframework.boot:spring-boot-starter-validation'
ㄴ3.x 버전은 버전 명시하지 않아도 Spring Boot Dependency Management가 자동으로 적합한 버전 설정

2. RequestDTO @NotBlank(message = "Title is required.") 등 어노테이션으로 검증 추가
   validation 유효성 검사 어노테이션
   https://epozen-dt.github.io/vaid_validated_annotattion/

3. Contoller에서 유효성 검사 적용할 API의 Request 앞에 @Validated 어노테이션 추가


----------------------------API 서버-------------------------------
API : 두 관계 간에 데이터를 주고받을 수 있도록 고안된 인터페이스

요청(Request)
-GET: 데이터를 조회
-POST: 새로운 데이터를 생성
-PUT: 기존 데이터를 수정
-DELETE: 데이터를 삭제

종류
1.웹 API
-HTTP/HTTPS 프로토콜 기반 API
-RESTful API(HTTP 프로토콜을 통해 CRUD 작업 수행)
-요청방식 : 동기(직렬적), 비동기(병렬적)
-동기 : @Controller / 비동기 : @RestController