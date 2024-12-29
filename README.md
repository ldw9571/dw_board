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

@RestController
-JSON 또는 XML 형태로 반환
-HTTP 요청 본문에 담긴 데이터를 Java 객체로 변환하기 위해 @RequestBody 사용
(RESTful API에서는 보통 객체 단위의 유효성 검사가 필요하며, 그룹별 검증이 필요할 경우가 드물기 때문에 @Validated보다는 @Valid가 더 간단하고 일반적으로 사용)


@Controller
-@Component 포함하여 컴포넌트 스캔에 의해 자동적으로 Bean 등록
=> 메소드에서 반환된 값은 뷰 이름이 되어 해당 뷰를 렌더링
-HTTP 요청 파라미터를 Java 객체에 바인딩하기 위해 @ModelAttribute 사용



----------------------------DI(의존성주입)-------------------------------
Spring 3가지 핵심 프로그래밍 모델 : AOP,DI,IOC

의존성 : 객첼르 생성 및 사용함에 있어 의존 관계가 있는 경우를 말한다

------------------
class A{
   new b = new B();
}
    ↑  (A는 B에 의존적)
class B{
}
------------------

DI
-외부에서 개개체간의 관계(의존성)를 결정, 객체 직접 생성이 아닌 외부에서 생성후 주입!
=>객체간의 관계를 동적으로 주입

※ Spring 4.3 이후 버전부터는 Setter 주입보다 생성자 주입(Construct Injection) 권장

DI 3가지 방법
-Construct Injection(생성자 주입)
-Field Injection(필드 주입)
-Setter Injection(Setter 주입)

(1)생성자주입
-하나의 생성자가 존재할 때 필드 주입의 대부분의 단점 극복
-Spring 4.3부터 @Autowired 생략 가능하여 생성자 1개에 @Autowired 생략하는 방법 주로 사용
-함께 @RequiredArgsConstructor(클래스에 선언된 필드들 매개변수로 하는 생성자 자동생성) 사용하여 생성자 생략
-프레임워크에 의존하지 않고 순수 자바 언어의 특징을 살리는 방법

final
-생성자 주입방식
-값이 한번 할당되면 변경 불가 => 객체불변성 보장
-초기에 할당되어 NPE(Null Point Exception)이 절대 발생 X

(2)Field Injection(필드주입)
-Bean으로 등록된 객체를 사용하고자 하는 클래스에 Field로 선언 후 @Autowired 선언하면 자동 의존 관계 주입
-간편하게 의존 관계 주입이 가능하지만 참조 관계를 눈으로 확인하기 어려움
-A가 B를 가지고 있고, B가 A를 가지고  있어(순환참조) 실행전까지 Error를 잡을 수 없다
-생성자 주입 이외(필드주입, Setter주입)은 모두 생성자 이후에 호출되므로 필드에 final 키워드 사용불가

[단점]
-Solid 원칙 중 단일책임 원칙(SRP)을 위반
단일책임원칙 : 모든 클래스는 하나의 책임만 갖는다(캡슐화)
-Unit Test가 어렵다
-final 사용불가 => 불변성보장 X. 객체 변동 가능성 있다.
※불변성보장이 되지 않는 이유
-final은 Spring에 의해 주입되는 값이 초기화 되는 시점에만 불변성 보장
-@Autowired를 사용한 의존성 주입은 객체 생성 후에 필드에 값을 설정하기 때문에 필드에 final을 사용해도 필드가 불변이 되지 않습니다.

(3)Setter Injection(Setter 주입)
-Spring 3.x 버전까지는 DI 권장 방식이였지만, 현재는 아니다.
-NPE(Null Pointer Exception) 발생할 수 있다.
-생성자 주입을 뺀 나머지(필드 주입, Setter 주입)은 모두 생성자 이후에 호출되므로, 필드에 final 키워드를 사용할 수 없다.


※가끔 옵션이 필요한 경우 Setter 주입(수정자 주입)을 사용하고, 절대 필드 주입을 사용하지 않는 것이 좋다.

Setter 주입시 
-setter 메서드를 public으로 사용
=> 개발시 누군가의 변경 가능성 (변경하면 안되는 메서드를 열어두는것은 좋지 않은 설계방법)
= > 생성자주입 = 객체 생성시 딱 1번 호출 => 호출 이후 변경 x


12/11
단점 이해
@RequiredArgsConstructor 등 생성자 생성은 보통 엔티티, DTO 클래스에서 생성하는데 
final은 서비스에서 레퍼지토리를 바라보거나 컨트롤러에서 서비스를 바라볼때 쓰던데 음..


DI는 IOC 프로그래밍 모델을 구현하는 방식 중 하나



----------------------------IOC(제어의 역전)-------------------------------

-개발자가 제어해야 할 요소들을 Spring Framework에서 대신 제어해준다

-객체생성
-의존성 구성 & 결합
-생명주기 관리

Spring Ioc Contaioner
-DI를 사용해 어플리케이션에서 구성하는 컴포넌트 관리

Bean
-Spring Ioc Contaioner는 1)XML파일과 자바코드, 2)어노테이션,  3)java POJO 클래스를 통해 객체에  대한 정보를 가져오는데 이  객체를 의미

※ POJO(Plain Old Java Object)
-객체지향적 구현 자바 객체

기존 1)객체생성 2)의존성 객체생성(클래스 내부 생성) 3)의존성 객체 메소드 호출 에서 
스프링에서는 1)객체생성 2)의존성 객체 주입(제어권을 스프링에게 위임하여 스프링이 만들어놓은 객체를 주입) 3)의존성 객체 메소드 호출


1) 객체 직접 생성
public class A {
    private B b;
    public A() {
        b = new B(); 
    }
    public void doWork() {
        b.performTask(); 
    }
}

B 객체를 C 객체로 변경
public class A {
    private C c; 

    public A() {
        c = new C(); 
    }

    public void doWork() {
        c.performTask(); 
    }
}

2) IOC 사용시
-공통 인터페이스 정의(A클래스에서 Service만 바라본다)
public interface Service {
    void performTask();
}

-B 클래스 구현
public class B implements Service {
   @Override
   public void performTask() {
      System.out.println("B is performing a task");
   }
}

-C 클래스 구현
public class C implements Service {
   @Override
   public void performTask() {
      System.out.println("C is performing a task");
   }
}

-A 클래스 (IoC 컨테이너에서 의존성 주입)
   public class A {
   @Autowired
   private Service service; // B 또는 C를 주입받음

   public void doWork() {
       service.performTask(); // Service 인터페이스만 바라봄
   }
}

3) IOC 컨테이너 설정
@Configuration
public class AppConfig {
   @Bean
   public Service service() {
      return new B(); // IoC 컨테이너에 B 또는 C 객체 등록
   }
}
4) 애플리케이션 실행
public class Main {
   public static void main(String[] args) {
   // IoC 컨테이너 생성
   ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        A a = context.getBean(A.class);
        a.doWork(); // 출력: B is performing a task
   }
}

@Bean : 수동으로 빈 등록
      : @Configuration 내부
      : Bean - IOC 컨테이너에 의해 관리되는 객체

@Component : 클래스를 자동으로 스캔하고 빈으로 등록할 때 사용 
           : 애플리케이션 패키지 내부
           : 등록된 빈의 이름은 클래스 이름의 카멜 케이스로 변환된 형태입니다(예: MyClass → myClass)

AnnotationConfigApplicationContext는 Spring IoC 컨테이너를 초기화, 초기화 하는 과정에서 Java Config 파일을 읽어 빈 검색 및 등록하는 IoC 컨테이너입니다.


https://blog.outsider.ne.kr/735