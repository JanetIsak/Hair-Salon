import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;


public class Stylist {
  private String name;
  private int id;


  public Stylist(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static List<Stylist> all() {
  }

  public int getId() {
    return id;
  }

  public static Stylist find(int id) {

  }

  public List<Client> getClients() {

  }

  public static List<Stylist> all() {
        String sql = "SELECT id, name FROM stylists";
        try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
      }

      @Override
       public boolean equals(Object otherStylist) {
         if (!(otherStylist instanceof Stylist)) {
           return false;
         } else {
           Stylist newStylist = (Stylist) otherStylist;
           return this.getName().equals(newStylist.getName());
           this.getId() == newStylist.getId();
         }
       }

       public void save() {
       try(Connection con = DB.sql2o.open()) {
         String sql = "INSERT INTO categories (name) VALUES (:name)";
         this.id = (int) con.createQuery(sql, true)
           .addParameter("name", this.name)
           .executeUpdate()
           .getKey();
       }
     }
}
