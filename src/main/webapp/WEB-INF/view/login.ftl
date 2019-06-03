<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
</head>
<body>
   <h1>Login</h1>
   <form name='f' action="login" method='POST'>
   <span>${message!}</span>
      <table>
         <tr>
            <td>User:</td>
            <td><input type='text' name='username' placeholder="Username"
                value='admin' autofocus="true" /></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' name='password' placeholder="Password"
                value='password'/></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
         </tr>
      </table>
   <span>${error!}</span>
  </form>
</body>
</html>
