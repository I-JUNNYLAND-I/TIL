---
description: 객체 생성 처리를 서브 클래스로 분리
---

# Factory Method

객체 생성을 서브 클래스에서 처리하는 패턴

객체의 생성 코드을 분리함으로써 객체 생성의 책임을 나눈다

{% hint style="info" %}
스트래티지 패턴, 싱글턴 패턴, 템플릿 메서드 패턴으로 구현한다
{% endhint %}

<img src="../../.gitbook/assets/file.drawing (7).svg" alt="" class="gitbook-drawing">

### How do Code&#x20;

{% embed url="https://gist.github.com/Junnyjun/7d1801f2949eb7bd01ca60318a5d1cde" %}

### More Reformation

색을 생성해주는 객체는 계속 생성하는 것 보다 기존것을 이용하는 것이 더 바람직합니다

{% embed url="https://gist.github.com/Junnyjun/4772f85c0d8290a79504ca808d8aaf1f" %}
