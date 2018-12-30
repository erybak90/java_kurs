package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        Contacts before = app.contact().all();
        app.contact().gotoAddContact();
        ContactData contact = new ContactData().withLastname("lastname1").withMobilephone("mobilephone1").
                withEmail("email1").withFirstname("firstname1").withGroup("test1");
        app.contact().create((contact), true);
        app.goTo().contactsPage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = (Contacts) app.contact().all();

        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {

        Contacts before = app.contact().all();
        app.contact().gotoAddContact();
        ContactData contact = new ContactData().withLastname("lastname1'").withMobilephone("mobilephone1").
                withEmail("email1").withFirstname("firstname1").withGroup("test1");
        app.contact().create((contact), true);
        app.goTo().contactsPage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = (Contacts) app.contact().all();
        assertThat(after, equalTo(before));
    }

}