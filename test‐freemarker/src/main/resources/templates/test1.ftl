<!DOCTYPE html>
<html>
<head>
        <meta charset="utf‐8">
        <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<br>
<table>
    <tr>
        <td>学号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
        <td>生日</td>
    </tr>
    <#list stus as stu>
        <tr>
            <td>${stu_index + 1}</td>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.money}</td>
            <#--<td>${stu.birthday}</td>-->
        </tr>
    </#list>
</table>

<br>

<h1>测试各个标签</h1>
<h2> 遍历map</h2>
<#--此时，${}相当于请求域中的map}-->
姓名：${stuMap.stu1.name}<br>
年龄：${stuMap.stu1.age}<br>
钱包：${stuMap['stu1'].money}<br>
<h2>使用if语句进行判断</h2><br>
<table>
    <tr>
        <td>学号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
        <td>生日</td>
    </tr>
    <#list stus as stu>
        <tr>
            <td>${stu_index + 1}</td>
            <td <#if stu.name == "小红" >style="background: red" </#if>>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.money}</td>
        <td>${stu.birthday?date}</td>
        </tr>
    </#list>
</table>
</body>
</html>