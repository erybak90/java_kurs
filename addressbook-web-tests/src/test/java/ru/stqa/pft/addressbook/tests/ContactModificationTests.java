package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

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
    app.getContactHelper().fillContactForm(new ContactData("lastname1", "mobilephone1",
            "email1", "firstname1", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().retourntoContactsPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}