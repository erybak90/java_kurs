package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    int before = app.getContactHelper().getContactCount();

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("lastname1", "mobilephone1",
              "email1", "firstname1", "test1"), true);

    }
    app.getContactHelper().selectContactDeleted();
    app.getContactHelper().clickDelete();
    app.getContactHelper().submitContactDelete();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before -1);

  }
}
