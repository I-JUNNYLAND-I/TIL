# 설계 원칙



## &#x20;**설계 원칙**

### **SOLID 원칙**

* 변경에 유연하다
* 이해하기 쉽다
* 많은 소프트웨어 시스템의 컴포넌트 기반이 된다

### **단일 책임 원칙**

_단일 모듈은 변경의 이유가 하나 뿐이여야 한다_

함수는 반드시 하나의 일을 해야한다.

### **개방-폐쇄 원칙**

_확장에는 열려 있어야하고 변경에는 닫혀있어야 한다_

서로 다른 목적으로 변경되는 요소를 적절하게 분리하고, 요소 사이의 의존성을 체계화 한다.

\*\*방항성 제어 \*\* , **정보은닉** 을 사용하여 고수준 컴포넌트를 보호할 수 있는 형태의 의존성 계층 구조가 만들어지도록 해야한다.

### **리스코프 치환 원칙**

_하위 타입은 상위 타입으로 치환 할 수 있어야 한다_

### **인터페이스 분리 원칙**

오퍼레이션을 인터페이스 단위로 분리하여 해결하다.

### **의존성 역전 원칙**

_유연성이 극대화된 시스템._

**변동성이 큰 구체 클래스를 참조하지 말라**

추상 인터페이스를 참조하여, 추상팩토리를 사용하라.

**변동성이 큰 구체 클래스로부터 파생하지 말라**

상속은 아주 신중하게 사용해야 한다

**구체 함수를 오버라이드 하지 말라**

추상 함수로 선언하고 구현체들에서 각자의 용도에 맞게 구현해야한다.

> 팩토리

바람직 하지 못한 의존성을 처리할 때 추상 팩토리를 사용한다.

#### _**분기**_

각 경로를 열거후, 적절한 결과를 만들어 낸다면 수학적으로 신뢰성을 가진다

#### 반복

귀납법을 이용한 추론, 열거법을 이용하여 수학적 신뢰성을 증명한다.

#### **기능적 분해**

구조적 프로그래밍을 통해 모듈을 증명 가능한 더 작은 단위로 재귀적으로 분해할 수 있게 되었다.

구조적 분석, 구조적 설계 등을 이용한 세분화

### **객체 지향 프로그래밍**

본질적으로 캡슐화 상속 다형성을 가진다.

_의존성에 대한 절대적인 제어 권한을 획득할 수 있는 능력._

> 캡슐화

객체지향은 데이터화 함수를 쉽게 캡슐화 한다.

_캡슐화된 데이터를 우회해서 사용하지 않을 것 이라는 믿음을 기반으로 한다._

> 상속

암묵적인 업케스팅이 이뤄진다.

> 다형성

새로운 기능의 도입에 변경이 필요 하지 않다. - 플러그인 아키텍쳐

* 의존성 역전

소프트웨어 아키텍트가 소스코드 의존성 전부에 대한 방향을 결정할 수 있게된다.

### **함수형 프로그래밍**

> 불변성과 아키텍쳐

가변 변수는 경합, 교착, 동시성 의 문제를 일으킨다.

> 가변성의 분리

불변성의 가장 중요한 타협은 **가변 컴포넌트**와 **불변 컴포넌트**로 분리하는 일이다

_가능한 많은 처리를 불변 컴포넌트로 옮기고 가변 컴포넌트의 코드를 빼내야 한다._

> 이벤트 소싱

생명주기 동안에 문제없이 동작할 정도의 저장 공간과 처리 능력 - 상태가 아닌 트랜잭션을 저장하는 전략

* 구조적 프로그래밍은 제어흐름의 직접적인 전환에 부과되는 규율
* 객체 지향 프로그래밍은 제어흐름의 간접적인 전환에 부과되는 규율
* 함수형 프로그래밍은 변수 할당에 부과되는 규율
