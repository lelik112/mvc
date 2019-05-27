<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
</head>
<body>
<h1>${sentence}</h1>
<#list parameters as ticket>
  <p>${ticket}
</#list>
<p>
<a href="/mvc">Welcome page</a>

</body>
</html>
