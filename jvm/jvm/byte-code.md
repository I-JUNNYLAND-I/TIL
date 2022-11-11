---
description: JVM 바이트 코드에 대해 알아봅시다
---

# Byte Code

## Bytecode?&#x20;

JVM이 읽을 수 있도록 컴파일된 `.class` 파일을 의미합니다

바이트 코드는 JVM 내부의 interpreter와 Jit compiler에 의해 OS에 맞는 기계어로 해석 됩니다.

런타임에 기계어로 해석되기 때문에 상대적으로 실행속도가 느립니다.

### Jit compiler

bytecode를 기계어로 미리 번역해 놓습니다.

인터 프리터가 바이트코드를 읽는 시간을 줄여주어 실행 속도를 올려 줍니다.

#### JIT compiler의 실행 수준

Hotspot jvm의 실행 수준은 Level0\~Level5까지총 5가지가 있으며, Jit compiler는 총 2종류가 있습니다

{% hint style="info" %}
C1 (Client Compiler) : 빠른 시작 시간에 최적화 된 유형

C2 (Server Complier) :  전반적인  성능 향상을 위해 최적화 된 유형

보다 더 많은 네이티브 코드를 생성합니다.
{% endhint %}

#### Code Cache?&#x20;

JVM 내부에 native code로 컴파일된 모든 바이트 코드를 저장하는 영역입니다.\
계층화를 통해 cache가능한 코드의 양은 4배까지 향상 되었습니다

```
세가지 계층화
The non-method segment : JVM 내부와 관련된 코드
The profiled-code segment : 짧은 수명을 가진 C1
The non-profiled segment : 긴 수명으로 추정된 C2 코
```

C2 code는 수명이 길지만 최적화에는 유용하지 않습니다.

수명이 길 것이라 예상했던 예측이틀린 C2는 다시 C2  최적화를 해제합니다&#x20;

#### Compile Level

5가지 단계로 구성되어 있습니다.

🔴 Level  0 - Interpreter

JVM 컴파일 단계중 가장 처음에 시작됩니다.

모든 java코드를 해석 하며, 프로파일링 정보를 수집하며 최적화를 수행합니다.

🟠 Level 1 - Simple Compile

아주 단순한 것들을 C1을   사용해 컴파일하는 단계입니다.

복잡하지 않으며, 더이상 최적화 할 필요가 없다고 여겨진 프로파일링 정보를 갖습니다.

🟡 Level 2 - Limited Compile

C2 컴파일러가 가득 찻을때 C1 컴파일러를 이용하는 단계입니다.

실행 성능의 향상을 위해 최대한 빠르게 코드를 컴파일하는 목적을 가집니다.

🟢 Level 3 - Full Compile

JVM의 전체 프로파일링과 함께 C1 compiler로 코드를 컴파일 합니다.

아주 단순한 방법이나, 큐가 가득차 있는 경우를 제외하면 대부분 이 단계에서 이뤄집니다.

JVM 내에서 Level 0 - Level 3 컴파일이 가장 일반적니입다.

🔵 Level 4 -  C2 compile

유일하게 C2를 사용하는 방식이며,  복잡한 방식의 코드거나 수명이 길 것으로 예측된 코드를 컴파일하는 방식입니다.

최적화가 해제되는 경우 다시 Level 0 으로 보내집니다.

{% hint style="info" %}
_–XX:-TieredCompilation_를 설정하여 계층화된 컴파일을 비  활성화할 수 있습니다 _._ 사용할 JIT 컴파일러(C1 또는 C2)를 선택해야 합니다.

기본 값은 JIT 컴파일러입니다.  멀티 코어 프로세서 또는 64비트 VM의 경우 JVM은 C2를 선택합니다. C2를 비활성화하고 프로파일링 오버헤드 없이 C1만 사용하려면 _-XX:TieredStopAtLevel=1_ 매개변수를 적용할 수 있습니다.

두 JIT 컴파일러를 완전히 비활성화하고 인터프리터를 사용하여 모든 것을 실행하려면 _-Xint_ 플래그를 적용할 수 있습니다. 그러나 **JIT 컴파일러를 비활성화하면 성능에 부정적인 영향을 미친다는** 점에 유의해야 합니다 .
{% endhint %}

Thresholds Level은 method의 단순 호출 횟수입니다.

_-XX:Tier{version}CompileThreshold={value} 를 지정하여  특정 컴파일 단계의 임계값을 지정해 줄 수 있습니다._

<img src="../../.gitbook/assets/file.drawing (9).svg" alt="" class="gitbook-drawing">

### Graal Jit Compiler

새로운 컴파일러 인터페이스인 **JVMCI를 사용합니다**

```
활성화: -XX:+UnlockExperimentalVMOptions -XX:+EnableJVMCI -XX:+UseJVMCICompiler
```

특정 프로그램을 만든 후 `-Dgraal.PrintCompilation=true` 를 이용해 graal 로그를 확인하면 코드 예열(runtime) 시간은 코어의 수와 비례함을 확인 할 수 있습니다.



#### what is different ?

Graal은 [바이트 배열을 다른 바이트 배열](https://chrisseaton.com/truffleruby/jokerconf17/)로 바꿉니다.&#x20;

****[**program-dependence-graph**](https://ssw.jku.at/General/Staff/TW/igv.html) **을 사용하여 적절한 데이터 구조로 종속화 합니다.**

**위의 링크로 graph를 시작할 수 있습니다.**

<figure><img src="../../.gitbook/assets/image (1).png" alt=""><figcaption></figcaption></figure>
