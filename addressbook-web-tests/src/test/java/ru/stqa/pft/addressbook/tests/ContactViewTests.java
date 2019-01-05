package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactViewTests extends TestBase {


    @Test (enabled = false)
    public void ContactViewTests() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        app.goTo().contactsPage();
        ContactData contactInfoFromViewForm = app.contact().infoFromViewForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergeAll(contactInfoFromViewForm)));

    }

    private String mergeAll(ContactData contact) {
        return Arrays.asList(contact.getHomephone(), contact.getMobilephone(),
                contact.getWorkphone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactViewTests::cleaned)
                .collect(Collectors.joining("\n"));

    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}