---
description: try-catch 안전하게 사용하기
---

# try-catch

### try-with-resources

try catch를 사용하다 보면 자원 반납으로 인해 코드가 복잡해지는 경우가 있다

```java
FileInputStream file = null; 
BufferedInputStream buffer = null; 
try { 
  file = new FileInputStream("test.txt"); 
  buffer = new BufferedInputStream(file); 
} catch (FileNotFoundException e) {
   e.printStackTrace();
} finally { 
  if (file != null) file.close(); 
  if (buffer != null) buffer.close(); 
}
```

&#x20;java 7 부터 지원하는 try-with-resources로 autoCloseable 인터페이스로 자원을 반납할 수 있게된다

```java
try (FileInputStream file = new FileInputStream("test.txt");
     BufferedInputStream buffer = new BufferedInputStream(file); ){
} catch (IOException e) {
    e.printStackTrace();
}
```

&#x20;try-with-resources 를 사용함으로써, 얻는 이득은 다음과 같다

* resource에 정의된 모든 자원의 반납
* stack trace가 누락되는걸 방지한다



### 자원 반납 구현하기

그렇다면 어떻게 우리가 작성한 코드의 자원 반납을 AutoCloseable로 할수 있을까?



🌈 CloseObject의 자원 회수는 File의 삭제로 정의하였다   \
`try{}` 가 종료될때 close()가 실행되는 구조로 이루어져있다.



```java
public class CloseObject implements AutoCloseable {
    private final File file;
    public CloseObject() {
        this.file = new File("test.txt");
    }

    @Override
    public void close() throws Exception {
        if(file.delete()){
            System.out.println("close");
        }
    }
}

```

```java
public static void main(String[] args) {
  try(CloseObject closeObject = new CloseObject()) {} 
  catch (Exception e) {}
}
```

하지만 이 예시는 잘못된 부분이 있다

AutoCloseable의 설명을 읽어보면, `InterruptedException` 와 `멱등성`  을 유지해야 한다고 적혀있

{% hint style="info" %}
멱등성 : 여러번 실행하더라도 같은 결과를 기대할 수 있어야 한
{% endhint %}

하지만 현재의 Close는 두번 호출이 되었을때 기존 File을 다시 제거 할수 없으므로 같은 응답이 보장할 수 없다.

\


