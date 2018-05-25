import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @After
  public void autoClear() {
    Hero.clear();
  }

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client newClient = new Client("Janet", "Female");
    assertEquals(true, newClient instanceof Client);
  }
  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Hero firstClient = new Client("Janet", "Female");
    Hero secondClient = new Client("Rene", "Male");
    assertEquals(true, Client.all().contains(firstClient));
    assertEquals(true, Client.all().contains(secondClient));
  }

  @Test
  public void getId_clientInstantiatesWithId_1() {
    Client newClient = new Client("Janet", "Female");
    assertEquals(1, newClient.getId());
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
  Client firstClient = new Client("Janet", "Female");
  Client secondClient = new Client("Rene", "Male");
  assertEquals(secondClient, Client.find(secondClient.getId()));
  }
}
