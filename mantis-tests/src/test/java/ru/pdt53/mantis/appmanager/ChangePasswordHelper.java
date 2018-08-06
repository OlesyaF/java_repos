package ru.pdt53.mantis.appmanager;

import org.openqa.selenium.By;

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

    click(By.xpath("//div[2]/div[1]/ul/li[6]/a/i"));

    click(By.xpath("//div[2]/div[2]/div[2]/div/ul/li[2]/a"));

    click(By.xpath("//div[@class='table-responsive']//a[.='" + user + "']"));

    click(By.cssSelector("input[value='Сбросить пароль']"));
  }

  public void change_confirm(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector(".btn-success"));
  }

}
