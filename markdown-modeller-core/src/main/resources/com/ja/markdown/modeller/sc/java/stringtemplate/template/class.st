class(javaClass) ::= <<
package <javaClass.package>;

<javaClass.imports:{it|
<if(!it.defaultImport)>import <it.name>;<endif>
}>
<javaClass.annotations:{a|@<a.classType.sourceCodeTypeName>}>
public class <javaClass.simpleName> {

  <javaClass.members:{it|
  <it.annotations:{a|@<a.classType.sourceCodeTypeName>
}>  private <it.classType.sourceCodeTypeName><if(it.generic)>\<<it.genericType.sourceCodeTypeName>\><endif> <it.name>;
}>
  <javaClass.members:{m|
  <if(m.withGetter)>public <m.classType.sourceCodeTypeName><if(m.generic)>\<<m.genericType.sourceCodeTypeName>\><endif> get<m.nameForMethod>() {
    return <m.name>;
\}<endif>
}>

  <javaClass.members:{m|
  <if(m.withSetter)>public void set<m.nameForMethod>(<m.classType.sourceCodeTypeName><if(m.generic)>\<<m.genericType.sourceCodeTypeName>\><endif> <m.name>) {
    this.<m.name> = <m.name>;
\}<endif>
}>

}>>