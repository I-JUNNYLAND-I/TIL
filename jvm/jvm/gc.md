# GC

### GC (Garbage Collection)❔

메모리 관리 기법 중 하나로 프로그램이 동적으로 할당했던 메모리 영역 중에서 필요없게 된 영역을 `해제하는 기능`이다.&#x20;

이미 할당된 Memory는 GC에 의해 해제가 되는데 이때 Garbage는 Heap과 Method Area에서 사용되지 않는 Object 를 의미한다

#### 💡 JAVA의 GC

GC의 발달로 인해 프로그래머는 직접 Memory를 핸들링 할 필요가 없었고 `Memory Crash`를 생각하지 않아도 되었지만, GC는 항상 완벽하지 않았다. GC는 명시적인 Memory해제 보다 느렸으며, GC때 발생하는 Suspend Time\*(Stop the World)\* 로 다양한 문제를 야기시켰다.

#### 💡 GC의 대상

**Root Set & Garbage**

GC란 Garbage를 모으는 작업이다 ( Garbage= 사용하지 않는 Object ) `Object`의 사용 여부는 `Root Set` 와의 관계로 사용 유무가 정해지게 된다.

![](https://velog.velcdn.com/images/junny8643/post/77e6d290-acb8-4eef-a476-f44be58b4b9d/image.png)

🔸 `Root Set`에서 `Reference` 관계과 있다면 `Reachable Object` 가 되고 이는, 사용하고 있는 `Object`가 된다.

🔹 `Root Set` 의 `Reference` 관계 판별법

```
A. Local Variable Section, Operand Stack에 Object 의 Reference 정보가 있는가 ❔

B. Method Area에 로딩된 클래스 중 Constant Pool 에 있는 Reference 정보를 토대로,
Thread에서 직접 참조하지않고 Constant Pool을 통해 간접 Link 되어있는 Object 인가❔

C. Memory에 남아 있으며, Native Method Area로 넘겨진 Object의 Reference가
JNI 형태로 참조 관계가 있는 Object 인가❔
```

#### 💡 GC의 목적

GC는 메모리의 압박이 있을 때 수행하게 되는데, 메모리가 필요하면 GC를 수행한다는 뜻이다. Object의 할당을 위해 한정된 Heap 공간을 재활용 하겠다는 의미 재활용을 위해 해지된 메모리의 자리는 할당한 자리에서 이뤄지기 때문에, 메모리는 드문드문 해지된 메모리의 빈공간이 생기게된다. ![](https://velog.velcdn.com/images/junny8643/post/dc3f5c47-ae81-4fd8-bc77-304746bc6ca1/image.png)

`Free Space` _(해지된 영역)_ 보다 큰 Object를 할당하는 경우 재활용을 의미를 잃게 된다.

***

### Hotspot JVM의 GC ❔

`Hotspot JVM`은 기본적으로 Gernerational Collection 방식을 사용한다. `Heap` 을 Object 의 Generation 별로 `Young Area` 와 `Old Area` 로 구분하여, `Young Area`는 `Eden Area`와 `Survivor Area` 로 구분된다. ![](https://velog.velcdn.com/images/junny8643/post/b39a25dc-3a39-4acb-b3a2-38598a909ec8/image.png)

#### **GC 메커니즘은 두가지 가설이 있다.**

🔶 Object는 생성된 후 금방 Garbage가 된다. 🔷 Old Object가 Young Object를 참조할 일은 드물다.

```
새로 할당되는 Object가 모인 곳은 단편화 발생 확률이 높다.
Memory 할당은 기존 Object의 다움 주소에서 수행하게 되며, 먼저 할당된 부분에서 Garbage 가 많이 생길것이다.

이때, `Sweep`작업을 수행하면 단편화가 발생하게 되며 `Compaction` 처럼 비싼 작업을 해야한다.

그로인해, Object만을 위한 `Eden Area` 를 생성하게 되었고
GC당시 Live(Marked) 한 Object 들을 피신시키는 `Survivor Area`를 따로 구성한 것이다.
```

`Garbage`를 추적하는 부분은 `Tracing 알고리즘`을 사용한다. Root Set에서 Reference 관계를 추적하고 Live Object를 Marking 한다. 이런 Marking 작업도 `Young Gerneration`에 국한 되는데 Marking 작업은 `Memory Suspend`에서만 수행되기 때문이다

#### Card Table

`Old Generation` 의 Memory를 대표하는 구조이다. YGO(`Young Generation의 Object`)를 참조하는 OGO(`Old Generation의 Object`)가 있다면 OGO 의 시작 주소에 카드를 `Dirty`로 표시하고(Dirty Card) 해당 내용을 Card Table에 기록한다. ![](https://velog.velcdn.com/images/junny8643/post/c2c57825-d5f1-43d2-9339-d48b296de232/image.png)

이후 Reference가 해제되면 Dirty Card도 사라지게 하여 Reference 관계를 쉽게 파악할 수 있게 해준다. `Hotspot JVM`은 이러한 방법으로 `Minor GC` 중 Dirty Card 만으로 Reference 관계를 파악할 수 있게 된다

#### TLAB

GC가 발생하거나 객체가 각 영역에서 다른 영역으로 이동할 때 어플리케이션의 병목이 발생하면서, 성능의 영향을 주게 된다. `Hotspot JVM`은 `Thread Local Allocation Buffer` 를 사용하여 각 스레드별 영향을 주지않는 메모리 할당 작업이 가능하게 된다.

![](https://velog.velcdn.com/images/junny8643/post/bfae0b52-8bc5-49bd-b345-908ca84e4f60/image.png)

***

### Collection 종류 ❔

#### 💡 Serial Collector

Young/Old 모두 하나의 Single CPU만 사용한다. 1개의 Thread 로 GC를 수행한다. **현재는 거의 사용되지 않는다**

#### 💡 Parallel Collector

`CPU 대기상태를 최소화 하는 목적` 을 가지고 탄생 했다. Young Area 에서의 Collection을 병렬로 처리한다. 동시에 작업 되는 만큼 Suspend Time은 줄어들며, PLAB로 Thread간 충돌을 회피한다.

```
PLAB?

Thread 마다 일정 부분(1Kb) 을 할당하고 다 사용하면 Buffer를 재할당 한다.

TLAB vs PLAB
TLAB 는 Young Area 의 빠른 할당을 위한 것 이고
PLAB는 Thread 동기화 과정의 문제를 해결하기 위함이다.

TLAB는 4Kb, PLAB는 1Kb 단위이다
```

#### 💡 CMS Collector

`힙 메모리가 클 때 사용하는 방식` 이다. Suspend Time을 분산하여 응답시간을 개선 하는데, 자원이 여유로운 상태에서 GC의 `Suspend Time`을 줄이는 목적이다. 오래 살아있는 Object가 있는 경우 적합하다.

![](https://velog.velcdn.com/images/junny8643/post/7fd4b989-f645-4b64-a2c7-71d64c96563e/image.png)

🔸 Initial Mark : Reachable Object를 판명 한다. 🔹 Concurrent Mark : Mark 된 Object를 추적하여 참조 관계를 확인 한다. 🔸 Remark : Old Generation의 모든 live Object를 Mark한다 🔹 Concurrent Sweep : Suspend Time 해제와 동시에 Collect를 시작한다.

#### 💡 G1 Collector

`물리적 Generation의 구분은 없애고 Heap을 1Mb단위 Region으로 사용하는 방식` 이다. G1은 가득 찬 Region부터 작업을 진행하고 Remember Set을 이용한다.

![](https://velog.velcdn.com/images/junny8643/post/76c14c96-2c75-422c-8ccc-9fd2860c2670/image.png)

**G1 Collector**는 4단계에 거쳐서 Collection을 수집 한다.

**Young GC**

Minor GC와 동일한 개념으로써, Suspend Time과 Multi Thread 작업이 존재한다.

***

**Concurrent Mark Phase** Stab 알고리즘을 사용하여, GC 시작당시의 reference를 기점으로 모든 live object 의 reference를 추적하는 방식이다.

🔸Mark : Single Thread 전체적으로 Concurrent 이며 이전 단계의 정보로 Marking한다 🔹Remarking : Suspend 와 Thread 작업이 발생, 각 Region 마다 `Reachable Object`를 계산

**Old Region Reclaim Phase** 🔸Remark : Concurrent, Multi-Thread, Live Object의 비율이 낮은 Region을 추려낸다 🔹Evacuation Pause : `Young Area`의 GC와 `Remark` 를 Evacuation 한다.

**Compaction Phase** Concurrent작업을 수행한다. Free Space를 병합해 단편화를 방지하는 것이다.
