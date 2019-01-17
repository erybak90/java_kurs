package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  public Properties properties = new Properties();


  @BeforeMethod
  public void ensurePreconditions() throws IOException {

      String target = System.getProperty("target", "local");
      properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname(properties.getProperty("contactFirstname"))
              .withLastname(properties.getProperty("contactLastname"))
              //.withGroup(properties.getProperty("contactGroup"))
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


    Contacts before = app.db().contacts();
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
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    //assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}