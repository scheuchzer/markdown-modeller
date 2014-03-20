JavaClass(model) ::= <<
package <model.package>;

<model.imports:{it|
<if(!it.defaultImport)>import <it.name>;<endif>
}>
<annotations(model)>public class <model.simpleName> <if(model.extendsType)>extends <model.extendsType.sourceCodeTypeName><endif>{

  <model.members:{it|
  <it.annotations:{a|@<a.classType.sourceCodeTypeName>
}>  private <it.classType.sourceCodeTypeName><if(it.generic)>\<<it.classType.genericType.sourceCodeTypeName>\><endif> <it.name>;
}>
  <model.members:{m|
  <if(m.withGetter)>public <returnType(m.classType)> get<m.nameForMethod>() {
    return <m.name>;
\}<endif>
}>
  <model.members:{m|
  <if(m.withSetter)>public void set<m.nameForMethod>(<type(m.classType)> <m.name>) {
    this.<m.name> = <m.name>;
\}<endif>
}>
<model.methods:{m| 
    <method(m)>
}>
  
  
}>>

