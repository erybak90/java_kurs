package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        Contacts before = app.contact().all();
        app.contact().gotoAddContact();
        File photo = new File("src/test/resources/pic.jpg");
        ContactData contact = new ContactData().withLastname("lastname1").withMobilephone("mobilephone1").
                withEmail("email1").withFirstname("firstname1").withGroup("test1").withPhoto(photo);
        app.contact().create((contact), true);
        app.goTo().contactsPage();
        Contacts after = (Contacts) app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    //@Test
    //public void TestCurrentDir(){
        //File currentDir = new File (".");
        //System.out.println(currentDir.getAbsolutePath());
        //File photo = new File("src/test/resources/pic.jpg");
        //System.out.println(photo.getAbsolutePath());
        //System.out.println(photo.exists());
    //}

}