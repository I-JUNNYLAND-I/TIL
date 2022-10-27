# GC 튜닝

### GC 튜닝

GC의 메모리 구조와 튜닝 옵션은 다음과 같다.

![](https://velog.velcdn.com/images/junny8643/post/8c51070d-8f54-4c1f-860c-1e46e9e11fd7/image.png)

GC의 필요성과 튜닝의 방법의 대해 소개하려 한다.

#### GC 튜닝의 필요 ❔

💡 일반적으로 튜닝이 필요한 경우

```
-Xms , -Xmx 로 Memory크기 설정 없이 사용중 이다.
JVM에 -server 가 설정되어 있지 않다.
Timout 로그와 함께 Transaction이 발생하지 않는다
```

GC는 생성된 Object가 많을 수록 발생하는 빈도가 늘어난다. GC를 줄이기 위해서는, **Object의 생성을 줄이는 작업**이 선행 되어야 한다.

EX) 🔹 String -> `StringBuilder` & `StringBuffer`로 대체해야 한다.

String의 경우 Object를 복사해서 값을 변경하기 때문에 Object의 개수가 늘어나지만.

`StringBuilder` & `StringBuffer` 의 경우 각각의 값만 변경이 된다.

🔸 로그를 최대한 적게 쌓도록 하는 것이 좋다. 대용량의 XML 파일의 Parsing은 가장 많은 Memory 사용량을 보인다.

#### 튜닝의 목적 ❔

Suspend Time 의 감소에 있으며 세부적으로는 Old Area에 넘어가는 Object의 수를 최소화 하는 것이며, Full GC의 실행 시간을 줄이는 것이다.

**💡 Object 수의 최소화**

G1 GC를 제외한 Java 7 까지의 모든 Hotspot GC는 Gernerational GC이다. `Eden Area`에서 살아남은 Object가 `Survivor Area`를 거쳐 `Old Area` 로 이동하는 방식인데, Old Area의 GC는 Suspend Time이 길어 Old Area로 오는 Object의 수를 줄이면 Full GC의 빈도를 줄일 수 있다.

**💡 FULL GC TIME 단축**

Full GC의 시간은 Minor GC에 비해 매우 오래 걸린다. Suspend Time이 오래(1\~2초) 걸리게 된다면 여러 부분에서 Time Out이 발생할 수 있다 이를 방지하고자, Old Area의 크기를 줄이게 되면, OOM이 발생하거나 Full GC의 횟수가 늘어나게 된다.

**💡 GC를 튜닝하는 옵션**

Heap의 초기 크기를 제어하는 `-Xms , -Xmx`, `New`:`Old`의 비율 설정하는 `-XX:NewRatio` 옵션을 사용해야 한다.

원인이 단순하게 `Perm Area`의 문제라면, -XX:PermSize -XX:MaxPermSize 로 크기를 변경해 주는것이 좋다

***

#### GC 튜닝 방법?

**💡 모니터링**

가장 쉬운 모니터링 방법은 jstat 명령어를 활용하는 방법이다. `-verbosegc` (GC를 출력하는 옵션) 과 `-Xloggc:<FILE_PATH>` (GC 내역 저장하는 옵션) 을 사용하여 로그를 파일화하는 방법이다

**💡 GC 튜닝이 불필요한 상황**

대체적으로 GC가 필요한 경우

```
Minor GC 처리시간이 50Ms 내외로 빠른 경우
Minor GC 주기가 10초 내외로 빈번하지 않은 경우
Full GC의 처리 시간이 1초 이내인 경우
Full GC의 주기가 10분에 1회 정도로 빈번하지 않은 경우
```

**Memory 크기와 GC 상관 관계** 🔸Memory 크기가 크면 GC 발생 횟수는 줄어들고 GC 수행 시간은 증가한다 🔹Memory 크기가 작으면 GC 수행 시간은 줄어들고 GC 발생 횟수는 증가한다.

**💡 GC 관련 장애 발생 유형 분석 방법**

JAVA 어플리케이션 환경에서는 흔히 JVM Heap 메모리 관련한 오류들을 흔히 접하게 된다. JVM의 대표적인 오류 장애는 `Out Of Memory` 는 OOM, OOME 라고 하는데 JVM의 메모리 부족으로 발생하는 에러이다.

대표적으로 두가지 유형이 있는데 `Java Heap space` : Heap 공간이 부족이 부족하여 발생한다. 공간 부족의 원인으로 Heap의 크기가 작아서 발생하는 경우와 어플리케이션 로직의 문제로 발생하는 경우가 있다.

`PermGen space` : String pool, Class Method, 각종 Method data 등을 저장하는 용도로 사용한다. JVM 기동시 로딩되는 Class 또는 String 수가 많은 경우 Classloader Leak에 의해 OOME가 발생될 수 있다.
