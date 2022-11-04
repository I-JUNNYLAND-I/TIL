---
description: resource를 제거하는 방법
---

# Remove resource

### Before

```java
try (FileInputStream file = new FileInputStream("test.txt");
     BufferedInputStream buffer = new BufferedInputStream(file); ){
} catch (IOException e) {
    e.printStackTrace();
}
```

### After

Lombok의 @Cleanup으로 autoCloseable 할 수 있다.

```java
@Cleanup FileInputStream in = new FileInputStream(file);
@Cleanup BufferedInputStream out = new BufferedInputStream(in);
in.read();
out.available();
```



### Result

```java
Example:
  public void copyFile(String in, String out) throws IOException {
      @Cleanup FileInputStream inStream = new FileInputStream(in);
      @Cleanup FileOutputStream outStream = new FileOutputStream(out);
      byte[] b = new byte[65536];
      while (true) {
          int r = inStream.read(b);
          if (r == -1) break;
          outStream.write(b, 0, r);
      }
  }
  
Will generate:
  public void copyFile(String in, String out) throws IOException {
      @Cleanup FileInputStream inStream = new FileInputStream(in);
      try {
          @Cleanup FileOutputStream outStream = new FileOutputStream(out);
          try {
              byte[] b = new byte[65536];
              while (true) {
                  int r = inStream.read(b);
                  if (r == -1) break;
                  outStream.write(b, 0, r);
              }
          } finally {
              if (outStream != null) outStream.close();
          }
      } finally {
          if (inStream != null) inStream.close();
      }
  }
```
