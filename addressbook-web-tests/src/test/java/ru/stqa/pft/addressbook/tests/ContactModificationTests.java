package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  public Properties properties = new Properties();


  @BeforeMethod
  public void ensurePreconditions() {

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname(properties.getProperty("contactFirstname"))
              .withLastname(properties.getProperty("contactLastname"))
              .withGroup(properties.getProperty("contactGroup"))
              .withAddress(properties.getProperty("contactAddress"))
              .withHomephone(properties.getProperty("contactHomePhone"))
              .withMobilephone(properties.getProperty("contactMobilePhone"))
              .withWorkphone(properties.getProperty("contactWorkPhone"))
              .withEmail(properties.getProperty("contactEmail1"))
              .withEmail2(properties.getProperty("contactEmail2"))
              .withEmail3(properties.getProperty("contactEmail3")), true);
    }
  }

  @Test
  public void testContactModification() {


    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname(properties.getProperty("contactNewFirstname"))
            .withLastname(properties.getProperty("contactNewLastname"))
            .withAddress(properties.getProperty("contactNewAddress"))
            .withHomephone(properties.getProperty("contactNewHomePhone"))
            .withMobilephone(properties.getProperty("contactNewMobilePhone"))
            .withWorkphone(properties.getProperty("contactNewWorkPhone"))
            .withEmail(properties.getProperty("contactNewEmail1"))
            .withEmail2(properties.getProperty("contactNewEmail2"))
            .withEmail3(properties.getProperty("contactNewEmail3"));
    app.goTo().contactsPage();
    app.contact().modify(contact);
    app.goTo().contactsPage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}