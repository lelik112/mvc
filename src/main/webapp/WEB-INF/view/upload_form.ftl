<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
</head>
<body>
<#include "header.ftl" />
<h1>Choose files to upload</h1>

<form method='POST' enctype='multipart/form-data' action='/mvc/upload'>
    <input type='file' name='file1'>
    <input type='file' name='file2'>
    <br>
    <input type='radio' name='entity' value='userAccount' checked>User<br>
    <input type='radio' name='entity' value='event'>Event<br>
    <input type='SUBMIT'>
</form>

<a href="/mvc">Welcome page</a>
</body>
</html>
