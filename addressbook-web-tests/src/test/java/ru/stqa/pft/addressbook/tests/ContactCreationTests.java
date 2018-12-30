package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        Set<ContactData> before = app.contact().all();
        app.contact().gotoAddContact();
        ContactData contact = new ContactData().withLastname("lastname1").withMobilephone("mobilephone1").
                withEmail("email1").withFirstname("firstname1").withGroup("test1");
        app.contact().create((contact), true);
        app.goTo().contactsPage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}