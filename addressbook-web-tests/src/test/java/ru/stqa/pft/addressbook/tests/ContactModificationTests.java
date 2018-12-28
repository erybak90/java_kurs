package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("lastname1", "mobilephone1",
              "email1", "firstname1", "test1"), true);

    }
   app.getContactHelper().initContactModification();
   app.getContactHelper().fillContactForm(new ContactData("lastname1", "mobilephone1",
           "email1", "firstname1", null), false);
   app.getContactHelper().submitContactModification();
   app.getNavigationHelper().retourntoContactsPage();
  }
}
