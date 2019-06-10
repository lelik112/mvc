<#if userAccount??>
    <h2>Hello ${userAccount.username}. Balance: ${userAccount.amount}</h2>
</#if>
<#if bank??>
    <h2>Bank balance: ${bank.amount}</h2>
</#if>


