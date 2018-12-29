package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().gotoAddContact();
        app.getContactHelper().createContact(new ContactData("lastname1", "mobilephone1",
                "email1", "firstname1", "test1"), true);
        app.getNavigationHelper().retourntoContactsPage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before +1);
    }

}