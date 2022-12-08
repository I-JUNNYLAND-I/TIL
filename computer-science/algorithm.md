# 🖇 Algorithm

[Read me](https://github.com/I-JUNNYLAND-I/algorithm/blob/main/README.md)

{% swagger method="post" path="/token" baseUrl="junnyland.site" summary="토큰 발급" %}
{% swagger-description %}
이메일을 입력해 주세요
{% endswagger-description %}

{% swagger-parameter in="body" name="email" required="true" %}
토큰을 받을 이메일
{% endswagger-parameter %}

{% swagger-response status="201: Created" description="전송되었습니다" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

```runkit  nodeVersion="18.x.x"
const axios = require('axios')
var url = process.env.TOKEN;
axios.post(url,
{
  "email" : "",
})

```

{% swagger method="post" path="/algorithm" baseUrl="junnyland.site" summary="참가 희망서" %}
{% swagger-description %}
아래 양식에 맞춰 제출 해주세요
{% endswagger-description %}

{% swagger-parameter in="body" name="branch" type="String" required="true" %}
생성을 희망하는 브런치 명
{% endswagger-parameter %}

{% swagger-parameter in="body" name="email" required="true" type="String" %}
깃허브 이메일
{% endswagger-parameter %}

{% swagger-parameter in="body" name="period" required="true" type="String" %}
경력 (개월수)
{% endswagger-parameter %}

{% swagger-parameter in="body" name="phone" required="false" type="String" %}
생성 완료 메세지 받을 번호 ( 없으면 이메일)
{% endswagger-parameter %}

{% swagger-parameter in="header" name="junny-token" required="false" %}
토큰 생성 문의 부탁드립니다.
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="감사합니다" %}

{% endswagger-response %}
{% endswagger %}

```runkit  nodeVersion="18.x.x"
const axios = require('axios')
var url = process.env.SIGN;
axios.post(url,
{
  "branch" : "",
  "email" : "",
  "period" : "",
  "phone" : "",
},{ 
  "headers": { "junny-token" : ""
}})
```
