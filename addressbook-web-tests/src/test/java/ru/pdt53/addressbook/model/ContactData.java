package ru.pdt53.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table (name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

  @Id
  @Column (name= "id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;

  @Column (name= "firstname")
  @Expose
  private String firstname;

  @Column (name= "lastname")
  @Expose
  private String lastname;

  @Transient
  private String address;

  @Transient
  private String home;

  @Transient
  private String work;

  @Column (name= "mobile")
  @Type(type = "text")
  @Expose
  private String mobile;

  @Transient
  private String allPhones;

  @Column (name= "email")
  @Type(type = "text")
  @Expose
  private String email;

  @Transient
  private String email2;

  @Transient
  private String email3;

  @Transient
  private String allMails;

  @Expose
  @Transient
  private String group;

  @Expose
  @Column (name= "photo")
  @Type(type = "text")
  private String photo;

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withMobilePhone(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withHomePhone(String home) {
    this.home = home;
    return this;
  }

  public ContactData withWorkPhone(String work) {
    this.work = work;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllMails(String allMails) {
    this.allMails = allMails;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public int getId() { return id; }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() { return address; }

  public String getMobilePhone() {
    return mobile;
  }

  public String getHomePhone() { return home; }

  public String getWorkPhone() { return work; }

  public String getEmail() {
    return email;
  }

  public String getEmail2() { return email2; }

  public String getEmail3() { return email3; }

  public String getGroup() { return group; }

  public String getAllPhones() { return allPhones; }

  public String getAllMails() { return allMails; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstname, lastname, mobile, email);
  }

  public File getPhoto() { return new File(photo); }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}
