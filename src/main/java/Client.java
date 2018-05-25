import java.util.ArrayList;
import java.util.List;



public class Client {

  private String mName;
  private String mGender;
  private int mCellphone;
  private static List<Client> instances = new ArrayList<Client>();
  private int mId;

  public Hero(String name, String gender, int cellphone) {

    mName = name;
    mGender = gender;
    mCellphone = cellphone;
    instances.add(this);
    mId = instances.size();
  }

  public String getName() {
    return mName;
  }

  public String getGender() {
    return mGender;
  }

  public int getCellphone() {
    return mCellphone;
  }

  }
  public static List<Client> all() {
    return instances;
  }
  public static void clear() {
    instances.clear();
  }
  public int getId() {
    return mId;
  }
  public static Client find(int id) {
    return instances.get(id - 1);
  }
}
