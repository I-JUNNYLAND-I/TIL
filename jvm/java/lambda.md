# Lambda

기존 자바에서 모던 자바로 넘어오면서 인자에 로직을 넣는 방식을 많이 사용하고 있다

```java
of.ifPresent(junny -> System.out.println("HELLO"));
```

이런것을 `람다식` 라고 합니다



### 함수형 인터페이스

함수형 프로그래밍 방식이 떠오르면서,

자바 진형에서도 이와 비슷하게 구현할 수 있는 선언형 프로그래밍 방식을 추구하는 사람들이 늘어나고 있는 추세&#x20;

그중 이 함수형 인터페이스는 함수형 프로그래밍의 고차함수를 흉내낼 수 있다

ex)&#x20;

```java
@FunctionalInterface
public interface JunnyFunction {
    void accept();
}
```

```java
public class JunnyService {
    public JunnyService(JunnyFunction fuction) {}
}
```

```java
public static void main(String[] args) {
  JunnyService junnyService = new JunnyService(() -> System.out.println("Hello World"));
}
```

함수형 인터페이스를 만드는 법은 굉장히 간단한데,\
인터페이스에 명세가 한개, 즉 메소드를 하나만 선언한다면 사용할 수 있다.

{% hint style="info" %}
```autoit
@FunctionalInterface : 2개이상의 선언을 방지한다  
```
{% endhint %}



### 자바에서 제공하는 API

| 인터페이스           |          Descripter         |           Method          |
| --------------- | :-------------------------: | :-----------------------: |
| Predicate       |     인자 하나를 받아서 boolea 리턴    |    `boolean test(T t)`    |
| Consumer        | `Consumer` 는 인자 하나를 받고 void |     `void accept(T t)`    |
| Supplier        |  아무런 인자를 받지 않고 T 타입의 객체를 리턴 |         `T get()`         |
| Function\<T, R> |    T 타입 인자를 받아서 R 타입을 리턴    |       `R apply(T t)`      |
| Comparator      |  T 타입 인자 두개를 받아서 int타입을 리턴  | `int compare(T o1, T o2)` |
| Runnable        |      아무런 객체를 받지 않고 void     |        `void run()`       |
| Callable        |      아무런 인자를 받지 않고 T리턴      |          V call()         |

이 외에도&#x20;

* [ ] BiFunction,BiPredicate, BiConsumer ... 등 Bi가 붙어 두개의 인자를 받는 인터페이스
* [ ] ToIntFuction, ToDoubleFunction ... 등 형변환을 해주는 인터페이스

등 응용해서 정의된 여러 함수형 인터페이스가 있으니 개발자가 직접 함수형인터페이스를 구현할만한 일은 거의 없
