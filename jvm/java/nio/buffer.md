---
description: 데이터 입출력
---

# BUFFER

NIO에서 데이터의 입&출력은 읽고 쓰기가 가능한 메모리 배열인 버퍼를 사용해야 합니다.

<img src="../../../.gitbook/assets/file.drawing (1).svg" alt="" class="gitbook-drawing">

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

바이트의 처리 순서는 운영체제마다 다릅니다. 그리하여 데이터를 전송&수신 할때 데이터를 다루는 버퍼도 고려해야 합니다

```
앞쪽 바이트부터 먼저 처리 => Big endian
뒤쪽 바이트부터 먼저 처리 => Little endian
```

<img src="../../../.gitbook/assets/file.drawing (6).svg" alt="" class="gitbook-drawing">

{% hint style="info" %}
Little-endian -> Big-endian 변환 ? \
ByteBuffer byteBuffer =

&#x20;ByteBuffer.allocateDirect(100).order(ByteOrder.BIG\_ENDIAN);
{% endhint %}

### Buffer Position

버퍼에는 네가지의 속성이 존재합니다

|  properties |  description                                                 |
| ----------- | ------------------------------------------------------------ |
| position    | <p>현재의 커서이다.<br> 0부터 시작하는 인덱스 이며, limit보다 작다</p>             |
| limit       | <p>버퍼가 사용할 수 있는 한계를 나타낸다. <br>capacity 보다 작거나 같은 값을 가진다.</p> |
| capacity    | 버퍼의 최대 데이터의 수를 나타낸다.                                         |
| mark        | <p>reset()으로 돌아오는 위치를 지정할 수 있다<br>(position보다 항상 작다).</p>    |

### 버퍼의 읽기&쓰기

버퍼에서 데이터를 저장(input Byte)은 put() \
버퍼에서 데이터를 읽기(output Byte)는 get()

<img src="../../../.gitbook/assets/file.drawing.svg" alt="Buffer Sample" class="gitbook-drawing">

### 버퍼 변환

#### ByteBuffer => String

```java
// 아래 byte[]를 디코딩하면?
// byte[] bytes = new byte[]{87, 101, 108, 99, 111, 109, 101, 32, 116, 111, 32, 74, 117, 110, 110, 121, 45, 76, 97, 110, 100};
public String byteToString(byte[] bytes) {
        Charset charset = StandardCharsets.UTF_8;
        return charset.decode(ByteBuffer.wrap(bytes)).toString();
    }
```

#### String => Byte

```java
String data = "Welcome to Junny-Land";
ByteBuffer buffer = charset.encode(data);
```

이와 비슷한 다른 타입의 Buffer도 동일한 형식으로 Encode, Decode해주면됩니다.
