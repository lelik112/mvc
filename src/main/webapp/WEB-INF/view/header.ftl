<#if user??>
    <h2>Hello ${user.username}. Balance: ${user.amount}</h2>
</#if>
<#if bank??>
    <h2>Bank balance: ${bank.amount}</h2>
</#if>


