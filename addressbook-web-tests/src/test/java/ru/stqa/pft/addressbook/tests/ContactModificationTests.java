package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {

    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withLastname("lastname1").withMobilephone("mobilephone1").
              withEmail("email1").withFirstname("firstname1").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {


    List<ContactData> before = app.contact().list();
    int index = before.size() -1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withLastname("lastname1").withMobilephone("mobilephone1").
                    withEmail("email1").withFirstname("firstname1").withGroup("test1");
    app.goTo().contactsPage();
    app.contact().modify(index, contact);
    app.goTo().contactsPage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}