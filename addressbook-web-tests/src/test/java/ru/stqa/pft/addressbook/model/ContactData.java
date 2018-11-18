package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String lastname;
  private final String mobilephone;
  private final String email;
  private final String firstname;

  public ContactData(String lastname, String mobilephone, String email, String firstname) {
    this.lastname = lastname;
    this.mobilephone = mobilephone;
    this.email = email;
    this.firstname = firstname;
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
}
