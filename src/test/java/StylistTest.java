import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

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
      firstStylist.save();
      Stylist secondStylist = new Stylist("Sami");
      secondStylist.save();
      assertEquals(true, Stylist.all().get(0).contains(firstStylist));
      assertEquals(true, Stylist.all().get(1).contains(secondStylist));
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

   @Test
   public void equals_returnsTrueIfNamesAretheSame() {
      Stylist firstStylist = new Stylist("Aru");
      Stylist secondStylist = new Stylist("Aru");
      assertTrue(firstCategory.equals(secondCategory));
  }

  @Test
  public void save_savesIntoDatabase_true() {
     Stylist newStylist = new Stylist("Aru");
     newStylist.save();
     assertTrue(Stylist.all().get(0).equals(newStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist newStylist = new Stylist("Aru");
    newStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(newStylist.getId(), savedStylist.getId());
  }
   // @Test
   // public void find_returnsNullWhenNoHeroFound_null() {
   //   assertTrue(Squad.find() == null);
   // }
}
