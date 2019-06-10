<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
</head>
<body>
<#include "header.ftl" />
<h1>Welcome!!!!</h1>

<h2>${message!}</h2>

<a href="/mvc/upload">Upload files</a><br>
<a href="/mvc/logout">Logout</a><br>
<a href="/mvc/events">Events(Task 3. Transactions)</a>
</body>
</html>
