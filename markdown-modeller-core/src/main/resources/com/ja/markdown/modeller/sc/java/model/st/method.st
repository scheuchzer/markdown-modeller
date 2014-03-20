method(model) ::= <<
<annotations(model)>public <returnType(model.returnType)> <model.name>(<model.parameters:{p|<p.type.sourceCodeTypeName><if(p.type.generic)>\<<p.type.genericType.sourceCodeTypeName>\><endif> <p.name>}>) {
  <model.content>
}
>>