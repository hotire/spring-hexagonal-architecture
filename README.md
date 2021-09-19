# spring-hexagonal-architecture

육각형 설계(Hexagonal Architecture)로 더 잘 알려져 있는 포트와 어댑터 설계(Ports and Adapters Architecture)는, 인터페이스나 기반 요소(infrastructure)의 변경에 영향을 받지 않는 핵심 코드를 만들고 이를 견고하게 관리하는 것이 목표입니다


## Domain Objects

비즈니스 규칙이 복잡한 도메인에서, Domain Objects는 어플리케이션의 핵심으로

state and behavior 둘다 포함한다. 

behavior이 상태에 가까울수록, 유지 보수에 쉬운 코드를 작성할 수 있다. 

어플리케이션의 어떤 계층과 관계도 없기 때문에 다른 계층의 변화에 영향을 받지 않는다. (Domain Objects는 순수 자바이다. POJO)

오직 비즈니스의 변경 사항만이 Domain Objects를 변경시킨다. 


Having a single responsibility lets us evolve our domain objects without having to take external dependencies in regard


## Input and Output Ports

The domain objects and use cases are within the hexagon, i.e. within the core of the application.

외부와의 모든 통신은 "Ports" 를 통해 이루어진다. 


- Input : 외부 구성 요소에서 호출되는데, use case에 의해 구현된다. 보통 input adapter or “driving” adapter 라고 부른다. 

- Output : use-case에 의해 호출되는데, 외부 구성에 의해 구현된다. the “D” in SOLID로 인터페이스를 통해  output adapter와 use cases 종속성을 반전시킨다. 








### References

- https://reflectoring.io/spring-hexagonal/
- https://alistair.cockburn.us/hexagonal-architecture/
- https://engineering.linecorp.com/ko/blog/port-and-adapter-architecture/