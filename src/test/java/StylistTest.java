import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void stylist_instantiatesCorrectly_true() {
      Stylist testStylist = new Stylist("Aru");
      assertEquals(true, testStylist instanceof Stylist);
   }

    @Test
    public void getName_stylistInstantiatesWithName_Aru() {
      Stylist testStylist = new Stylist("Aru");
      assertEquals("Aru", testStylist.getName());
   }

    @Test
    public void all_returnsAllInstancesOfStylist_true() {
      Stylist firstStylist = new Stylist("Aru");
      firstStylist.save();
      Stylist secondStylist = new Stylist("Beti");
      secondStylist.save();
      assertEquals(true, Stylist.all().contains(firstStylist));
      assertEquals(true, Stylist.all().contains(secondStylist));
   }

   @Test
      public void getClients_retrievesALlClientsFromDatabase_clientsList() {
        Stylist newStylist = new Stylist("Sami");
        newStylist.save();
        Client firstClient = new Client("Janet", "Female", 792533748, newStylist.getId());
        firstClient.save();
        Client secondClient = new Client("Rene", "Male", 700518365, newStylist.getId());
        secondClient.save();
        Client[] clients = new Client[] { firstClient, secondClient };
        assertTrue(newStylist.getClients().containsAll(Arrays.asList(clients)));
      }
   // @Test
   // public void clear_emptiesAllSquadFromList_0() {
   //   Squad testSquad = new Squad("Strongest", 5, "Girl trafficking");
   //   assertEquals(0, Squad.all().size());
   // }

   @Test
   public void getId_stylistInstantiatesWithId_1() {
     Stylist testStylist = new Stylist("Aru");
     testStylist.save();
     assertTrue(testStylist.getId() > 0);
  }

   @Test
   public void find_returnsStylistWihSameId_secondStylist() {
     Stylist firstStylist = new Stylist("Aru");
     firstStylist.save();
     Stylist secondStylist = new Stylist("Sami");
     secondStylist.save();
     assertEquals(secondStylist, Stylist.find(secondStylist.getId()));
   }

   @Test
   public void getClients_returnsEmptyList_ArrayList() {
     Stylist testStylist = new Stylist("Aru");
     assertEquals(0, testStylist.getClients().size());
   }

   // @Test
   // public void addClient_addsClientToList_true() {
   //   Stylist testStylist = new Stylist("Aru");
   //   Client newClient = new Client("Janet", "Female", 792533748, 1);
   //   testStylist.addClient(newClient);
   //   assertTrue(testStylist.getClients().contains(newClient));
   // }

   @Test
   public void equals_returnsTrueIfNamesAretheSame() {
      Stylist firstStylist = new Stylist("Aru");
      Stylist secondStylist = new Stylist("Aru");
      assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
     Stylist newStylist = new Stylist("Sami");
     newStylist.save();
     assertTrue(Stylist.all().get(0).equals(newStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist newStylist = new Stylist("Sami");
    newStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(newStylist.getId(), savedStylist.getId());
  }

  @Test
public void delete_deletesStylist_true() {
  Stylist newStylist = new Stylist("Aru");
  newStylist.save();
  int newStylistId = newStylist.getId();
  newStylist.delete();
  assertEquals(null, Stylist.find(newStylistId));
}
   // @Test
   // public void find_returnsNullWhenNoHeroFound_null() {
   //   assertTrue(Squad.find() == null);
   // }
}
