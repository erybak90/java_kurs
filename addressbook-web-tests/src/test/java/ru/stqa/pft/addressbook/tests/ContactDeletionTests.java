package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("lastname1", "mobilephone1",
              "email1", "firstname1", "test1"), true);

    }
    app.getNavigationHelper().retourntoContactsPage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContactDeleted(before.size() - 1);
    app.getContactHelper().clickDelete();
    app.getContactHelper().submitContactDelete();
    app.getNavigationHelper().retourntoContactsPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

  }
}
