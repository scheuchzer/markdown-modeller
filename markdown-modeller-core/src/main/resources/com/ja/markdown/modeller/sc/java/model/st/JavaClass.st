JavaClass(model) ::= <<
package <model.package>;

<model.imports:{it|
<if(!it.defaultImport)>import <it.name>;<endif>
}>
<model.annotations:{a|@<a.classType.sourceCodeTypeName>}>
public class <model.simpleName> {

  <model.members:{it|
  <it.annotations:{a|@<a.classType.sourceCodeTypeName>
}>  private <it.classType.sourceCodeTypeName><if(it.generic)>\<<it.genericType.sourceCodeTypeName>\><endif> <it.name>;
}>
  <model.members:{m|
  <if(m.withGetter)>public <m.classType.sourceCodeTypeName><if(m.generic)>\<<m.genericType.sourceCodeTypeName>\><endif> get<m.nameForMethod>() {
    return <m.name>;
\}<endif>
}>

  <model.members:{m|
  <if(m.withSetter)>public void set<m.nameForMethod>(<m.classType.sourceCodeTypeName><if(m.generic)>\<<m.genericType.sourceCodeTypeName>\><endif> <m.name>) {
    this.<m.name> = <m.name>;
\}<endif>
}>

}>>