package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {

    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withLastname("lastname1").withMobilephone("mobilephone1").
              withEmail("email1").withFirstname("firstname1").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {


    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withLastname("lastname1").withMobilephone("mobilephone1").
                    withEmail("email1").withFirstname("firstname1").withGroup("test1");
    app.goTo().contactsPage();
    app.contact().modify(contact);
    app.goTo().contactsPage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}