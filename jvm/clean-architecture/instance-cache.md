# Instance Cache

자바의 wrapping타입이 값을 초기화 하는 방식에 대해 설명합니다.\
`Integer.valueof()`로 설명합니다

```java
public static Integer valueOf(int i) {
        if (i >= IntegerCache.low && i <= IntegerCache.high)
            return IntegerCache.cache[i + (-IntegerCache.low)];
        return new Integer(i);
    }
    
private static class IntegerCache {
        static final int low = -128;
        static final int high;
        static final Integer cache[];

        ...
        ...
```

직접 Cache 사이즈를 지정해 두지 않는다면 `-127~128(1byte)` 만큼을[ 캐시](../../computer-science/design-pattern/flyweight.md)해둡니다.

```java
    public static void main(String[] args) throws IOException, InterruptedException {
        Integer add1 = Integer.valueOf(1);
        Integer add2 = Integer.valueOf(1);

        System.out.println("same ? :" + (add2 == add1));
        //same ? :true
        Integer add3 = Integer.valueOf(258);
        Integer add4 = Integer.valueOf(258);

        System.out.println("same ? :" + (add3 == add4));
        //same ? :false
    }
```

캐시 범위에 있는 add1, add2은 값은 인스턴스\
캐시 범위 바깥에 있는 add3, add4는 다른 인스턴스인걸 확인 할 수 있습니다.

{% hint style="info" %}
`-XX:AutoBoxCacheMax=size` 를  사용하여, 범위(-127 \~ size)를 직접 지정해줄 수 있습니다
{% endhint %}

Integer cache 이외에도 ByteCache, ShortCache, LongCache, CharcaterCache가 같은 방식으로 존재합니다.
