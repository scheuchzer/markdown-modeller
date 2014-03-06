class(modelClass) ::= <<
package <modelClass.package>;

<modelClass.imports:{jcm|
<if(!jcm.defaultImport)>import <jcm.javaClassName>;<endif>
}>
<modelClass.annotations:{a|@<a.javaClassNameForTemplate>}>
public class <modelClass.name> {

  <modelClass.javaMembers:{it|
  <it.annotations:{a|@<a.javaClassNameForTemplate>
}>  private <it.javaType.javaClassNameForTemplate><if(it.generic)>\<<it.javaGenericType.javaClassNameForTemplate>\><endif> <it.name>;
}>
  <modelClass.setters:{m|
  public void <m.setterName>(final <m.javaType.javaClassNameForTemplate><if(m.javaGenericType)>\<<m.javaGenericType.javaClassNameForTemplate>\><endif> <m.name>) {
    this.<m.name> = <m.name>;
  \}
}>
  <modelClass.getters:{m|
  public <m.javaType.javaClassNameForTemplate><if(m.javaGenericType)>\<<m.javaGenericType.javaClassNameForTemplate>\><endif> <m.getterName>() {
    return <m.name>;
  \}
}>
}>>
