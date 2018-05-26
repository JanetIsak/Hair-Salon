import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;



public class Client {

  private String name;
  private String gender;
  private int cellphone;
  private int id;

  public Client(String name, String gender, int cellphone) {

    this.name = name;
    this.gender = gender;
    this.cellphone = cellphone;
  }

  public String getName() {
    return name;
  }

  public String getGender() {
    return gender;
  }

  public int getCellphone() {
    return cellphone;
  }

  public int getId() {
    return id;
  }

  public static List<Client> all() {
  String sql = "SELECT id, name, gender, cellphone FROM clients";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Client.class);
  }
}

public static Client find(int id) {
  try(Connection con = DB.sql2o.open()) {
   String sql = "SELECT * FROM clients where id=:id";
   Client client = con.createQuery(sql)
     .addParameter("id", id)
     .executeAndFetchFirst(Client.class);
   return client;
 }
}

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getGender().equals(newClient.getGender()) &&
             this.getCellphone() == newClient.getCellphone() &&
             this.getId() == newClient.getId();
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO clients (name, gender, cellphone) VALUES (:name, :gender, :cellphone)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("gender", this.gender)
      .addParameter("cellphone", this.cellphone)
      .executeUpdate()
      .getKey();
  }
}

}
