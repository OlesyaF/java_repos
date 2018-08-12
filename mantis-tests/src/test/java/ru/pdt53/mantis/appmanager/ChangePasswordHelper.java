package ru.pdt53.mantis.appmanager;

import org.openqa.selenium.By;
import ru.pdt53.mantis.model.UserData;
import ru.pdt53.mantis.model.Users;

import java.sql.*;

public class ChangePasswordHelper extends HelperBase {

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void change_init(String admin, String admin_password, String user) {

    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), admin);
    click(By.cssSelector("input[value='Войти']"));
    type(By.name("password"), admin_password);
    click(By.cssSelector("input[value='Войти']"));

    click(By.xpath("//div[@id='sidebar']//span[.=' управление ']"));

    click(By.xpath("//div[@class='row']//a[.='Управление пользователями']"));

    click(By.xpath("//div[@class='table-responsive']//a[.='" + user + "']"));

    click(By.cssSelector("input[value='Сбросить пароль']"));
  }

  public UserData selectUser () {

    UserData user = null;

    try {
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?user=root&password=&serverTimezone=UTC");
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

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    System.out.println("Пользователь, у которого тест меняет пароль: " + user);
    return user;
  }

  public void change_confirm(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector(".btn-success"));
  }

}
