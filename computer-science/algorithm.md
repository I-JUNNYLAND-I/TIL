# π Algorithm

[Read me](https://github.com/I-JUNNYLAND-I/algorithm/blob/main/README.md)

{% swagger method="post" path="/token" baseUrl="junnyland.site" summary="ν ν° λ°κΈ" %}
{% swagger-description %}
μ΄λ©μΌμ μλ ₯ν΄ μ£ΌμΈμ

\


gmail, naver λ§ νμ©λ©λλ€.
{% endswagger-description %}

{% swagger-parameter in="body" name="email" required="true" %}
ν ν°μ λ°μ κΉνλΈμ΄λ©μΌ
{% endswagger-parameter %}

{% swagger-response status="201: Created" description="μ μ‘λμμ΅λλ€" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

```runkit  nodeVersion="14.x.x"
const axios = require('axios')
axios.post('http://sign.junnyland.com/api/v1/token',
{
  "email" : "happy@junny.com",
}).then(res => console.log(res.data))
```

{% swagger method="post" path="/algorithm" baseUrl="junnyland.site" summary="μ°Έκ° ν¬λ§μ" %}
{% swagger-description %}
μλ μμμ λ§μΆ° μ μΆ ν΄μ£ΌμΈμ
{% endswagger-description %}

{% swagger-parameter in="body" name="branch" type="String" required="true" %}
μμ±μ ν¬λ§νλ λΈλ°μΉ λͺ
{% endswagger-parameter %}

{% swagger-parameter in="body" name="email" required="true" type="String" %}
κΉνλΈ μ΄λ©μΌ
{% endswagger-parameter %}

{% swagger-parameter in="body" name="period" required="true" type="String" %}
κ²½λ ₯ (κ°μμ)
{% endswagger-parameter %}

{% swagger-parameter in="body" name="phone" required="false" type="String" %}
μμ± μλ£ λ©μΈμ§ λ°μ λ²νΈ ( μμΌλ©΄ μ΄λ©μΌ)
{% endswagger-parameter %}

{% swagger-parameter in="header" name="junny-token" required="false" %}
ν ν° μμ± λ¬Έμ λΆνλλ¦½λλ€.
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="κ°μ¬ν©λλ€" %}

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
