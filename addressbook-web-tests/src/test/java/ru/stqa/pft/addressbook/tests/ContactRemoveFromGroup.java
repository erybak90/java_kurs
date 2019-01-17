package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;


    public class ContactRemoveFromGroup extends TestBase {

        @BeforeMethod
        public void ensurePreconditions() {

            if (app.db().contacts().size() == 0) {
                app.goTo().contactsPage();
                app.contact().create(new ContactData()
                        .withFirstname("firstname").withLastname("Lastname")
                        .withMobilephone("11122233").withEmail("email1"), true);

                if (app.db().groups().size() == 0) {
                    app.goTo().groupPage();
                    app.group().create(new GroupData().withName("test1"));
                }

                List<GroupData> list = app.db().groups().stream().collect(Collectors.toList());
                boolean condition = false;

                for (GroupData g : list) {
                    if (g.getContacts().size() > 0) {
                        condition = true;
                    }
                }

                if (condition == false) {
                    app.contact().create(new ContactData().withFirstname("firstname").withLastname("Test")
                            .inGroup(list.get(0)), true);
                }
            }
        }
        @Test
        public void testContactRemoveFromGroup (){
            app.goTo().contactsPage();
            Groups groups = app.db().groups();
            contactRemoval(groups.size());
        }

        public void contactRemoval (int x){

            Groups groups = app.db().groups();
            GroupData selectedGroup = groups.stream().collect(Collectors.toList()).get(x - 1);

            if (selectedGroup.getContacts().size() > 0) {
                app.contact().selectGroupRemove(selectedGroup.getName());
                Integer id = app.contact().selectContactAndReturnID(0);
                ContactData contact = app.db().getContact(id);
                app.contact().click(By.name("remove"));
                app.goTo().contactsPage();
                Set<GroupData> after = contact.getGroups();
                after.remove(selectedGroup);
                MatcherAssert.assertThat(app.db().getContact(id).getGroups(),
                        equalTo(contact.getGroups().without(selectedGroup)));
            } else {
                x--;
                contactRemoval(x);
            }
        }
    }

