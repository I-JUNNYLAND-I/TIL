# JVM과 Heap

## JVM이란

JAVA Virual Machine의 약자로써 JAVA파일을 실행하는 일종의 소프트 웨어이다. Java를 독자적인 프로세스로 실행하는 Runtime Instance ( Daemon ) 이다

_**JAVA에서 프로그램을 실행한다는 것**_** 은**

사용자가 작성한 `JAVA Source` 파일(.java)를 `Compile` 하여( java Compiler -> javac.exe ) `JAVA Byte Code` (.class) 를 생성한다 `Class Loader` 에 Byte Code (.class) 를 로딩하여 `Execution Engine`을 통해 해석하고 `Runtime Data Areas`에 배치하여 실질적인 수행이 이루어진다.

**`Runtime Data Area` ❔❔**

**🔴 Method Area : Class, 변수, Method, static변수, 상수 정보 등이 저장되는 영역이다.**

**🟠 Heap Area : new로 생성된 인스턴스와 객체가 저장되는 구역이다.**

**🟡 Stack Area : Method의 값들이 저장되는 구역이다 ( 생명주기는 LIFO이다 )**

**🟢 PC Register : 현재 수행 중인 JBM 명령의 주소값이 저장된다**

**🔵 Native Method Stack : 다른 언어의 호출을 위해 할당되는 구역**

![](https://velog.velcdn.com/images/junny8643/post/4e3c80ec-9230-4c62-84e2-c4b1e543eb5c/image.png)

**Heap Area ❔❔**

Array와 Object 두가지 종류만 저장되는 곳이며, 모든 Thead에 의해 공유되는 영역이다. 흔히, 서로 다른 Tread 사이에서이 Heap Date를 이용할 때 동기화 이슈가 발생한다

JAVA 에서 Heap의 메모리 해제는 오직 GC를 통해서만 수행된다 _방식은 벤더사(Oracle, IBM) 마다 차이가 있다_ EX )

```java
public class Main {
    static ArrayList<String> array = new ArrayList<>();
    public static void main(String[] args) {

        Thread thread = new NewThread();
        thread.start();

        while (true){
            try {
                sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            array.add("add after: " + current().nextInt() );
            System.out.println("add after: " + array.toString());
        }
    }
    static class NewThread extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    sleep(1500L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                array.removeAll(array);
                System.out.println("removed after: " + array.toString());
            }
        }
    }
}
```

**Heap Structure ❔❔**

`Hotspot JVM 의 Heap 구조`

`Young generation` & `Old generation` 으로 이루어져 있다. ![](https://velog.velcdn.com/images/junny8643/post/a4d4caa3-f57d-421c-9ba6-9e7e9984cfe0/image.png)

🔴 `Young generation` 은 Eden & Survivor 로 구성되어 있는데,

```
Eden :  Object가 Heap에 가장 먼저 할당되는 장소이다
가득 차게 되면 참조 여부를 따져 Survivor로 넘기고 참조가 끊어 지면 남겨 놓은 후, 
모든 Object가 처리되면 Eden을 청소 한다
```

```
Survivor : Eden 영역에서 살아남은 Object들이 머무르는 장소 이다. 
Survivor영역은 두개(S1, S2)로 구성되어있는데,
Eden영역의 대피는 둘중 하나의 영역만 사용하게 된다 (Minor GC)
```

🟣 `Old Generation` : Yuong Generation에서 살아남은(특정 회수 이상 참조 된) Object가 이동 되는곳

```
앞으로 사용될 확률이 높은 Object들을 저장하는 영역, 
이 영역에 메모리도 충분하지 않으면 GC가 발생한다 (Full GC) 
```

🟢 `Permanent` : Class, Method, Static 등이 저장되는 공간 ( 메타데이터 영역 )

🛠 JAVA 8 부터는 Perm은 Metaspace 로 전환 되었다. ( Heap이 아닌 Native 영역 ) 🛠 JAVA 8 부터는 Perm의 상환값의 제한이 없기 때문에 Metaspace의 영역을 지정해서 메모리 누수를 지정해 줘야 할 때도 있다.

| 비고 | 🔙 JAVA 7 | JAVA 8 🔜 |
| -- | --------- | --------- |
| 용량 | 82MB      | 16ExaByte |
