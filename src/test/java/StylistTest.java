import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @After
  public void autoClear() {
    Stylist.clear();
  }

    @Test
    public void stylist_instantiatesCorrectly_true() {
      Stylist testStylist = new Stylist("Aru");
      assertEquals(true, testStylist instanceof Stylist);
   }

    @Test
    public void getName_stylistInstantiatesWithName_Janet() {
      Stylist testStylist = new Stylist("Aru");
      assertEquals("Janet", testStylist.getName());
   }

    @Test
    public void all_returnsAllInstancesOfStylist_true() {
      Stylist firstStylist = new Stylist("Aru");
      Stylist firstStylist = new Stylist("Sami");
      assertEquals(true, Stylist.all().contains(firstStylist));
      assertEquals(true, Stylist.all().contains(secondStylist));
   }

   // @Test
   // public void clear_emptiesAllSquadFromList_0() {
   //   Squad testSquad = new Squad("Strongest", 5, "Girl trafficking");
   //   assertEquals(0, Squad.all().size());
   // }

   @Test
   public void getId_stylistInstantiatesWithId_1() {
     Stylist testStylist = new Stylist("Aru");
     assertEquals(1, testStylist.getId());
  }

   @Test
   public void find_returnsStylistWihSameId_secondStylist() {
     Stylist firstStylist = new Stylist("Aru");
     Stylist secondStylist = new Stylist("Sami");
     assertEquals(secondStylist, Stylist.find(secondStylist.getId()));
   }

   @Test
   public void getClients_returnsEmptyList_ArrayList() {
     Stylist testStylist = new Stylist("Aru");
     assertEquals(0, testStylist.getClients().size());
   }

   @Test
   public void addClient_addsClientToList_true() {
     Stylist testStylist = new Stylist("Aru");
     Client newClient = new Client("Janet");
     testStylist.addClient(newClient);
     assertTrue(testStylist.getClients().contains(newHClient));
   }

   // @Test
   // public void find_returnsNullWhenNoHeroFound_null() {
   //   assertTrue(Squad.find() == null);
   // }
}
