package ru.pdt53.addressbook;

public class PersonData {
  private final String firstname;
  private final String lastname;
  private final String mobile;
  private final String email;

  public PersonData(String firstname, String lastname, String mobile, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.mobile = mobile;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }
}