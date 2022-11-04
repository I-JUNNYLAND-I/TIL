---
description: '@Accessors로 빌더 따라하기.'
---

# New Builder Pattern?

### Before

기존 Lombok을 사용해서 Builder를 생성 했던 방식

```java
public class Junny {
    private String name;
    private int age;
    private String address;

    @Builder
    public Junny(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
```

### After

```java
public class Junny {
    @Setter
    @Accessors(chain = true,fluent = true)
    public String name;
    @Setter
    @Accessors(chain = true,fluent = true)
    private int age;
    @Setter
    @Accessors(chain = true,fluent = true)
    private String address;

    private Junny() {}

    public static Junny builder() {
        return new Junny();
    }
}

```

## How to use

```java
Junny junny = Junny.builder()
    .name("junny")
    .age(10)
    .address("seoul");
```

{% hint style="danger" %}
회사에서 사용하지 않도록 주의하도록 한다.
{% endhint %}
