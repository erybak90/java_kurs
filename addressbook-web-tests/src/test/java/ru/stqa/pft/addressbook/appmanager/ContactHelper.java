
package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super (wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("email"), contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void gotoAddContact() {
    click(By.linkText("add new"));
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath("//input[@id='"+ id +"']//..//..//img[@alt='Edit']")).click();

    //WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s')", id)));
    //WebElement row = checkbox.findElement(By.xpath("./../.."));
    //List<WebElement> cells = row.findElements(By.tagName("td"));
    //cells.get(7).findElement(By.tagName("a")).click();
}

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }


  public void selectContactDeletedById (int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

  }

  public void clickDelete() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitContactDelete() {
    wd.switchTo().alert().accept();

  }

  public void create(ContactData group, boolean b) {
    gotoAddContact();
    fillContactForm((group), true);
    submitContactCreation();

  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm((contact), false);
    submitContactModification();
  }

  public void delete(ContactData contact) {
    selectContactDeletedById(contact.getId());
    clickDelete();
    submitContactDelete();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr/td[1]/input"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  //public Set<ContactData> all() {
    //Set<ContactData> contacts = new HashSet<ContactData>();
    //List<WebElement> rows = wd.findElements(By.name("entry"));
    //for (WebElement row : rows) {
      //List<WebElement> cells = row.findElements(By.tagName("td"));
      //int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      //String lastname = cells.get(1).getText();
      //String firstname = cells.get(2).getText();
      //contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    //}
    //return contacts;
  //}

  public Contacts all() {
  Contacts contacts = new Contacts();
  List<WebElement> elements = wd.findElements(By.xpath(".//*[@name='entry']"));
  for (WebElement element : elements) {
    String firstname = element.findElement(By.xpath(".//td[3]")).getText();
    String lastname = element.findElement(By.xpath(".//td[2]")).getText();
    String[] phones = element.findElement(By.xpath("./td[6]")).getText().split("/n");
    int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
  contacts.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname).
          withHomephone(phones[0]).withMobilephone(phones[1]).withWorkphone(phones[2]));
   }
   return contacts;
   }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
            withHomephone(home).withMobilephone(mobile).withWorkphone(work);
  }
}
