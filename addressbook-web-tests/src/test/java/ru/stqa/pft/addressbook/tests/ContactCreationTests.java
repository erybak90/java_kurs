package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
         String line = reader.readLine();
         while (line !=null){
             String[] split = line.split(";");
             list.add(new Object[] {new ContactData().withFirstname(split[0]).
                     withLastname(split[1]).withMobilephone(split[2])});
             line = reader.readLine();
         }
        return list.iterator();

        //list.add(new Object[]{new ContactData().withLastname("lastname 1").withFirstname("firstname 1")
                //.withMobilephone("mobilephone 1")});
        //list.add(new Object[]{new ContactData().withLastname("lastname 2").withFirstname("firstname 2")
                //.withMobilephone("mobilephone 2")});
        //list.add(new Object[]{new ContactData().withLastname("lastname 3").withFirstname("firstname 3")
               // .withMobilephone("mobilephone 3")});
       // return list.iterator();
    }


    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        Contacts before = app.contact().all();
        app.contact().gotoAddContact();
        //File photo = new File("src/test/resources/pic.jpg");
        app.contact().create((contact), true);
        app.goTo().contactsPage();
        Contacts after = (Contacts) app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
