package ru.pdt53.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.pdt53.mantis.model.MailMessage;
import ru.pdt53.mantis.model.UserData;
import ru.pdt53.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException, InterruptedException {

    String admin = "administrator";
    String admin_password = "root";

    String new_password = "new_password123";
    String username = null;
    String email = null;
    UserData user = null;

    Connection conn = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?user=root&password=&serverTimezone=UTC");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(
              "select id, username, email from mantis_user_table where username <> 'administrator' and username like 'a%' order by id desc");
      Users users = new Users();
      while (rs.next()) {
        users.add(new UserData()
                .withId(rs.getInt("id"))
                .withUsername(rs.getString("username"))
                .withEmail(rs.getString("email")));
      }

      rs.close();
      st.close();
      conn.close();

      user = users.iterator().next();
      username = user.getUsername();
      email = user.getEmail();

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    System.out.println("Пользователь, у которого тест меняет пароль: " + user);

    app.change_pass().change_init(admin, admin_password, username);

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);

    app.change_pass().change_confirm(confirmationLink, new_password);

    assertTrue(app.newSession().login(username, new_password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
