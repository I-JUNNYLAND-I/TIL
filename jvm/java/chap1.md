---
description: try-catch 안전하게 사용하기
---

# try-catch 다루기

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



🌈 CloseObject의 자원 회수는 File의 삭제이

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
