package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().gotoAddContact();
        app.getContactHelper().fillContactForm(new ContactData("lastname1", "mobilephone1", "email1", "firstname1"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().retourntoContactsPage();
    }


}