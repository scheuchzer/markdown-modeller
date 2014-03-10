JpaMasterDataTestClass(model) ::= <<
package <model.package>;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import java.util.List;

<model.imports:{it|
<if(!it.defaultImport)>import <it.name>;<endif>
}>
<model.annotations:{a|@<a.classType.sourceCodeTypeName>}>
public class <model.simpleName> {

  @Test
  public void test() {
    final EntityManagerFactory emf = Persistence
      .createEntityManagerFactory("unit");
    final EntityManager em = emf.createEntityManager();
    final <model.javaClass.simpleName> entity = new <model.javaClass.simpleName>();
    <model.setterMethodCalls:{it|entity.<it.key>(<it.value>);
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    em.clear();
    final List\<<model.javaClass.simpleName>\> result = em.createQuery("from <model.javaClass.simpleName>", <model.javaClass.simpleName>.class)
				.getResultList();
    }>
    assertThat(result.size(), is(1));
  }
}>>