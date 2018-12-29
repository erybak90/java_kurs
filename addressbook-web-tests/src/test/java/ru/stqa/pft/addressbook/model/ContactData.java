package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String lastname;
  private final String mobilephone;
  private final String email;
  private final String firstname;


  private String group;

  public ContactData(String lastname, String mobilephone, String email, String firstname, String group) {
    this.lastname = lastname;
    this.mobilephone = mobilephone;
    this.email = email;
    this.firstname = firstname;
    this.group = group;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobilephone() {
    return mobilephone;
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
  public String toString() {
    return "ContactData{" +
            "lastname='" + lastname + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(lastname, that.lastname) &&
            Objects.equals(firstname, that.firstname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastname, firstname);
  }
}
