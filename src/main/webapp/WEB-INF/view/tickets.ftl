<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
</head>
<body>
<#include "header.ftl" />
<h1>Event: ${event.name}. ${sentence!}</h1>
<#list event.tickets as ticket>
  <p>${ticket.id}._____${ticket.price}$_____<a href="/mvc/events/${event.id}/ticket/${ticket.id}">Buy</a></p>
</#list>
<p>
<a href="/mvc">Welcome page</a>

</body>
</html>
