import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
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

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client newClient = new Client("Janet", "Female", 792533748);
    assertEquals(true, newClient instanceof Client);
  }
  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Janet", "Female",792533748);
    firstClient.save();
    Client secondClient = new Client("Rene", "Male", 700518365);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  @Test
  public void getId_clientInstantiatesWithId() {
    Client newClient = new Client("Janet", "Female", 792533748);
    newClient.save();
    assertTrue(newClient.getId() > 0);
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("Janet", "Female", 792533748);
    firstClient.save();
    Client secondClient = new Client("Rene", "Male", 700518365);
    secondClient.save();
    assertEquals(secondClient, Client.find(secondClient.getId()));
  }

  @Test
  public void equals_returnsTrueIfPropertiesAretheSame() {
    Client firstClient = new Client("Janet", "Female", 792533748);
    Client secondClient = new Client("Janet", "Female", 792533748);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueIfPropertiesAretheSame() {
    Client myClient = new Client("Janet", "Female", 792533748);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Janet", "Female", 792533748);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }
}
