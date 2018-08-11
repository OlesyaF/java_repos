package ru.pdt53.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.pdt53.mantis.appmanager.ApplicationManager;
import ru.pdt53.mantis.model.Issue;
import ru.pdt53.mantis.model.Status;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {

    MantisConnectPortType mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL("http://localhost/mantisbt-2.15.0/api/soap/mantisconnect.php"));

    int statusId = 0;
    boolean isIssueOpen = true;

    IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
    Set<Issue> issues = Arrays.asList(issue).stream().map((p) -> new Issue().withId(issueId)
            .withStatus(new Status().withId(p.getStatus().getId().intValue()).withName(p.getStatus().getName())))
            .collect(Collectors.toSet());
    for (Issue issue$ : issues) {
      if (issue$.getId() == issueId) {
        statusId = issue$.getStatus().getId();
      }
    }

    if (statusId == 80) { //80 - статус "решена"
      isIssueOpen = false;
    }
    return isIssueOpen;
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      System.out.println("Ignored because of issue " + issueId);
      throw new SkipException("Ignored because of issue " + issueId); //сообщение не выводится в консоли - баг TestNG
    }
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

}
