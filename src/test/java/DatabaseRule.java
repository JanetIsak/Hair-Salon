import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Before
        public void setUp() {
          DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "isak", "yanu1988");
        }

  @After
    public void autoClear() {
      try(Connection con = DB.sql2o.open()) {
        String deleteClientsQuery = "DELETE FROM clients *;";
        String deleteStylistsQuery = "DELETE FROM stylists *;";
        con.createQuery(deleteClientsQuery).executeUpdate();
        con.createQuery(deleteStylistsQuery).executeUpdate();
      }
    }
}
