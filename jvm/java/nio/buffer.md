---
description: 데이터 입출력
---

# BUFFER

NIO에서 데이터의 입&출력은 읽고 쓰기가 가능한 메모리 배열인 버퍼를 사용해야 합니다.

<img src="../../../.gitbook/assets/file.drawing (2).svg" alt="" class="gitbook-drawing">

### Buffer의 종류

<figure><img src="../../../.gitbook/assets/image (1).png" alt=""><figcaption></figcaption></figure>

&#x20;버퍼 클래스의 이름으로 어떤 데이터를 옮기는 버퍼인지 유추할 수 있다.

#### Direct Buffer&#x20;

운영체제의 메모리 공간은 이용하는 버퍼 이다

JNI(Java Native Interface)호출로 메모리를 할당 받아 생성 시간이 느리다

#### Non-Direct Buffer&#x20;

JVM의  Heap 공간을 이용하는 버퍼 생성 시간이 빠르다.&#x20;

|        | Direct     | Non-Direct |
| ------ | ---------- | ---------- |
| 메모리 공간 | =OS memory | =JVM heap  |
| 생성 시간  | Fast       | Slow       |
| 성능     | Low        | High       |

### Create Buffer

> Non-Direct Buffer -> allocate() ,wrap()

#### allocate()

JVM 힙 메모리에 넌다이렉트 버퍼를 생성

```java
// 100Byte를 저장하는 CharBuffer
CharBuffer charBuffer = CharBuffer.allocate(100);
```

#### wrap()

이미 생성되어 있는 자바 배열을 래핑하여 Buffer 객체를 생성

<pre class="language-java"><code class="lang-java"><strong>// 100Byte를 저장하는 CharBuffer
</strong><strong>char[] bytes = new char[100];
</strong>CharBuffer charBuffer = CharBuffer.wrap(bytes);</code></pre>

> Direct Buffer -> allocateDirect()

**allocateDirect()**

운영체제가 관리하는 메모리에 다이렉트 버퍼를 생성

```java
// 100Byte를 저장하는 ByteBuffer, ByteBuffer 에서만 제공
ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
```

## ByteOrder

앞쪽 바이트부터 먼저 처리 => Big endian

뒤쪽 바이트부터 먼저 처리 => Little endian

<img src="../../../.gitbook/assets/file.drawing (1).svg" alt="" class="gitbook-drawing">

{% hint style="info" %}
Little-endian -> Big-endian 변환 ? \
`ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100).order(ByteOrder.nativeOrder());`
{% endhint %}
