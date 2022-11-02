---
description: 자바의 IO와 NIO의 차이점 &사용법
---

# File

### IO

* 기존 IO는 스트림 기반으로 되어있다.

File을 읽고&쓰기위해 InputStream,OutputStream을 선언해주어야 한

출력스트림이 1바이트를 사용하면 입력스트림이 1 바이트를 읽는 형식이다

* IO는 blocking이다&#x20;

IntputStream\&OutpuStream의 입&출력을 호출하면 데이터를 입력 받을때 까지 블로킹된다

Thread의 Interrupt는 불가능 하며 Stream을 닫을때 까지 블로킹은 유지됩니다

* NIO는 채널 기반이다.

스트림과 달리 입출력 스트림을 따로 구분하지 않아도 된다. (FileChannel만으로 읽고&쓰기가 가능하다)

버퍼를 사용하여 입출력을 하기 때문에 성능이 우수하다

{% hint style="info" %}
buffer : 버퍼는 데이터를 모아서 옮겨주는 방식으로 기존 IO에서도 BufferedStream을 사용하여 단점을 극복하기도 한다
{% endhint %}

