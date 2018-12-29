package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().gotoAddContact();
        app.getContactHelper().createContact(new ContactData("lastname1", "mobilephone1",
                "email1", "firstname1", "test1"), true);
        app.getNavigationHelper().retourntoContactsPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}