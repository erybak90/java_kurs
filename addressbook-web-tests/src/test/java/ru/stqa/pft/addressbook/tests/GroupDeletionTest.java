package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupDeletionTest extends TestBase {

    public Properties properties = new Properties();

    @BeforeMethod

    public void ensurePreconditions() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        app.goTo().groupPage();
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData()
                    .withName(properties.getProperty("groupName"))
                    .withHeader(properties.getProperty("groupHeader"))
                    .withFooter(properties.getProperty("groupFooter")));
        }
    }
    //public void ensurePreconditions(){
        //app.goTo().groupPage();
       // if (app.group().all().size() == 0){
           // app.group().create(new GroupData().withName("test1"));
      //  }
   // }

    @Test
    public void testGroupDeletion() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.db().groups();
        assertEquals(after.size(),before.size() - 1 );
        assertThat(after, equalTo(before.without(deletedGroup)));
      }

}
