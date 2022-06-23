import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class Main {
   public static void main(String... args) {
     Util util = new Util();
     Session session = util.getSessionFactory().openSession();
     Transaction transaction = session.beginTransaction();
     try {
         session.save(new User("Mike", "Pupkin", (byte) 32));
         transaction.commit();
     } catch (Exception e) {
         e.printStackTrace();
         transaction.rollback();
     } finally {
         session.close();
     }
   }
}