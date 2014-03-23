methodParams(model) ::= << <model.parameters:{p|<annotations(p)> <p.type.sourceCodeTypeName><if(p.type.generic)>\<<p.type.genericType.sourceCodeTypeName>\><endif> <p.name>}>
>>