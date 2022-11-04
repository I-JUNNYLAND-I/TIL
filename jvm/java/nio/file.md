---
description: 자바의 IO와 NIO의 차이점&사용
---

# File

### IO

* 기존 IO는 스트림 기반으로 되어있다.

File을 읽고&쓰기위해 InputStream,OutputStream을 선언해주어야 한

출력스트림이 1바이트를 사용하면 입력스트림이 1 바이트를 읽는 형식이다

* IO는 blocking이다&#x20;

IntputStream\&OutpuStream의 입&출력을 호출하면 데이터를 입력 받을때 까지 블로킹된다

Thread의 Interrupt는 불가능 하며 Stream을 닫을때 까지 블로킹은 유지됩니다

### NIO

* NIO는 채널 기반이다.

스트림과 달리 입출력 스트림을 따로 구분하지 않아도 된다. (FileChannel만으로 읽고&쓰기가 가능하다)

버퍼를 사용하여 입출력을 하기 때문에 성능이 우수하다

* NIO는 NonBlocking 이다

사실은 blocking\&NonBlocking을 모두 가지고 있습니다.

Thread를 Intterupt 하여 빠져 나올 수 있다는 장점이 있습니다.

작업 준비가 완료된 채널만 선택해서 처리하기 때문에 Blocking 되지 않습니

{% hint style="info" %}
buffer : 버퍼는 데이터를 모아서 옮겨주는 방식으로 기존 IO에서도 BufferedStream을 사용하여 단점을 극복하기도 한다
{% endhint %}

<img src="../../../.gitbook/assets/file.drawing.svg" alt="" class="gitbook-drawing">

## What is difference❔

### 경로의 정의&#x20;

NIO는 Path, Paths를 이용하여 경로를 정의합니

```java
File file = new File("C:\\Users\\user\\Desktop\\test.txt");
Path path = Paths.get("C:\\Users\\user\\Desktop","test.txt");
```

### 기능의 차이&#x20;

* Dir 생성

```java
file.mkdir(); 
file.mkdirs();

Files.createDirectories(path);
Files.createDirectory(path);
```

* File 삭제

```java
file.delete();

Files.delete(path);
Files.deleteIfExists(path);
```

* 파일 생성

```java
file.createNewFile();

Files.createFile(path);
Files.createTempFile(path);
```

상기의 예제 처럼 Files\&Path를 주로 사용한다

## What is New❔

### Watch Service

Directory 내부에서 파일의 변경을 감지한다.

해당 Directory의 변화를 감지할 수 있도록 watchService를 등록해준다\


```java
Path path = Paths.get("./junny");
WatchService watchService = FileSystems.getDefault().newWatchService();
path.register(watchService, 
  StandardWatchEventKinds.ENTRY_CREATE, 
  StandardWatchEventKinds.ENTRY_MODIFY, 
  StandardWatchEventKinds.ENTRY_DELETE);
```

WatchService는 이벤트(변경) 정보를 가진 Key를 생성하여 큐에 저장한

```java
while (true) {
    watchService.take();
    System.out.println("File changed");
}
```

위 와 같이 설정 해준뒤, 해당 폴더(junny)에 파일을 생성하면 `File changed` 가 출력된다

WatchService가 take되면 위에서 말했던 Key 즉, WatchKey가 생성된다

```java
while (true) {
    WatchKey take = watchService.take();
    List<WatchEvent<?>> watchEvents = take.pollEvents();
    System.out.println("File changed");
}
```

> &#x20;List\<WatchEvent\<?>>인 이유는 여러 파일을 동시에 감지하는 경우를 위해서 입니다&#x20;

```java
WatchKey take = watchService.take();
List<WatchEvent<?>> watchEvents = take.pollEvents();
for (WatchEvent<?> watchEvent : watchEvents) {
    WatchEvent.Kind<?> kind = watchEvent.kind();
    Path context = (Path) watchEvent.context();
    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
        System.out.println("Create File" + context);
    } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
        System.out.println("Modify File" + context);
    } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
        System.out.println("Delete File" + context);
    }
}
```



<details>

<summary>Help Us<br><a href="https://palpit.tistory.com/640">https://palpit.tistory.com/640</a></summary>



</details>

