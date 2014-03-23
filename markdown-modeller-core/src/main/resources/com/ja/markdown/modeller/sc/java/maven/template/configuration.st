configuration(model) ::= <<
<configuration>
  $model.items:{c|
  <$c.key$>$c.value$</$c.key$>}$  
</configuration>
>>