---
description: try-catch 안전하게 사용하기
---

# try-catch 다루기

### 🌈try-with-resources

**🧸 try catch를 사용하다 보면 자원 반납으로 인해 코드가 복잡해지는 경우가 있다**

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

**🪅 java 7 부터 지원하는 try-with-resources로 autoCloseable 인터페이스로 자원을 반납할 수 있게된다**

```java
try (FileInputStream file = new FileInputStream("test.txt");
     BufferedInputStream buffer = new BufferedInputStream(file); ){
} catch (IOException e) {
    e.printStackTrace();
}
```

**🪆 try-with-resources 를 사용함으로써, 얻는 이득은 다음과 같다**

**⚽ resource에 정의된 모든 자원의 반납**

**⚾ stack trace가 누락되는걸 방지한다**
