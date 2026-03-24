import java.util.HashMap;
import java.util.Map;

public class Family {
   private Map<String, Person> members = new HashMap<>();

   public void add(Person osoba){
       members.put(osoba.getName()+ " " +osoba.getLast_name(), osoba);
   }

   public Person get(String full_name){
       return members.get(full_name);
   }
}
