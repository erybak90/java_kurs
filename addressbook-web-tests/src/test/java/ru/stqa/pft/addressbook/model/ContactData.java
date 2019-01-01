package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {


  private int id = Integer.MAX_VALUE;
  private String lastname;
  private String mobilephone;
  private String email;
  private String firstname;
  private String group;
  private String workphone;
  private String homephone;

  public int getId() {
    return id;
}

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withWorkphone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }


  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }


  public String getLastname() {
    return lastname;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(firstname, that.firstname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastname, firstname);
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", lastname='" + lastname + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';
  }


}
