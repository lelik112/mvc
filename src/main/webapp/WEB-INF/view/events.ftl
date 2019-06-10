<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
</head>
<body>
<#include "header.ftl" />
<h1>${sentence!}</h1>
<#list events as event>
  <a href="/mvc/events/${event.id}/tickets">${event.name}</a><br>
</#list>
<p>
<a href="/mvc">Welcome page</a>

</body>
</html>
