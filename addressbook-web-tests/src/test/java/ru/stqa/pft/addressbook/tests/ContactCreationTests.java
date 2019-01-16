package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
         String line = reader.readLine();
         while (line !=null){
          xml += line;
             line = reader.readLine();
         }
         XStream xstream = new XStream();
         xstream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();


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
        Contacts before = app.db().contacts();
        app.contact().gotoAddContact();
        //File photo = new File("src/test/resources/pic.jpg");
        app.contact().create((contact), true);
        app.goTo().contactsPage();
        Contacts after = (Contacts) app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));

    }
}
