package ru.pdt53.rest;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;

public class TestBase {

  public boolean isIssueOpen(int issueId) throws IOException {

    boolean isIssueOpen = true;
    String status = null;

    String json = getExecutor().execute(Request
            .Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json")).returnContent().asString();
    System.out.println("Проверяемый баг-репорт (#" + issueId + "): " + json);
    VerbalExpression regex = VerbalExpression.regex()
            .find("\"state_name\":\"Closed\"").or("\"state_name\":\"Resolved\"").build();
    status = regex.getText(json);
    //проверяем статус баг-репорта: если state_name = "Closed" or "Resolved", то баг закрыт и тест можно выполнять
    if (status.equals("\"state_name\":\"Closed\"") | (status.equals("\"state_name\":\"Resolved\""))) {
      System.out.println("Статус баг-репорта: " + status);
      isIssueOpen = false;
    }

    return isIssueOpen;
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      System.out.println("Тест пропущен, т.к. баг-репорт #" + issueId + " открыт!");
      throw new SkipException("Ignored because of issue " + issueId); //сообщение не выводится в консоли - баг TestNG
    }
  }
}