Read Me 추가


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

