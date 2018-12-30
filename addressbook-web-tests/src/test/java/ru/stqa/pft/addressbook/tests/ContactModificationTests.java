package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("lastname1", "mobilephone1",
              "email1", "firstname1", "test1"), true);

    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().retourntoContactsPage();
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "lastname1", "mobilephone1",
            "email1", "firstname1", "test1");
    app.getContactHelper().fillContactForm((contact), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().retourntoContactsPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() -1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}