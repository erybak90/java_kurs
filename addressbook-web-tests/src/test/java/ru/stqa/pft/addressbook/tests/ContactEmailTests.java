package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @Test
    public void testContactEmails() {
        app.goTo().contactsPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergePhones(contactInfoFromEditForm)));
        //assertThat(contact.getEmail2(), equalTo(contactInfoFromEditForm.getEmail2()));
        //assertThat(contact.getEmail3(), equalTo(contactInfoFromEditForm.getEmail3()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }

}
