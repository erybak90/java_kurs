package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    public Properties properties = new Properties();

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData()
                    .withName(properties.getProperty("groupName"))
                    .withHeader(properties.getProperty("groupHeader"))
                    .withFooter(properties.getProperty("groupFooter")));
        }
    }

   // public void ensurePreconditions(){
       // app.goTo().groupPage();
        //if (app.group().all().size() == 0) {
           // app.group().create(new GroupData().withName("test1"));
      // }
   // }

  @Test
  public void testGroupModification () {
    Groups before = app.group().all();
      GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName(properties.getProperty("groupNewName"))
              .withHeader(properties.getProperty("groupNewHeader"))
              .withFooter(properties.getProperty("groupNewFooter"));
    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(),before.size());

    assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
}
}
