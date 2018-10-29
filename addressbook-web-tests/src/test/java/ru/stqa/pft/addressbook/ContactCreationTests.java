package ru.stqa.pft.addressbook;


import org.testng.annotations.Test;



public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        gotoAddContact();
        fillContactForm(new ContactData("lastname1", "mobilephone1", "email1", "firstname1"));
        submitContactCreation();
        retourntoContactsPage();
    }


}
