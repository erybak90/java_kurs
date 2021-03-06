
package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.util.ArrayList;
import java.util.List;


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
    //type(By.name("email"), contactData.getEmail());
    //attach(By.name("photo"), contactData.getPhoto());

    if (creation){
      if (contactData.getGroups().size() > 0){
        Assert.assertTrue(contactData.getGroups().size() == 1);
      }
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().
              iterator().next().getName());
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

}

  public void initContactView(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();

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



  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath(".//*[@name='entry']"));
    for (WebElement element : elements) {
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      String allAddresses = element.findElement(By.xpath(".//td[4]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname).
              withAllPhones(allPhones)
              .withAllEmails(allEmails)
              .withAllAddresses(allAddresses));
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
  String email = wd.findElement(By.name("email")).getAttribute("value");
  String email2 = wd.findElement(By.name("email2")).getAttribute("value");
  String email3 = wd.findElement(By.name("email3")).getAttribute("value");
  String address = wd.findElement(By.name("address")).getAttribute("value");
  String address2 = wd.findElement(By.name("address2")).getAttribute("value");

  wd.navigate().back();
  return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
          withHomephone(home).withMobilephone(mobile).withWorkphone(work)
          .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
}

  public ContactData infoFromViewForm(ContactData contact) {
    initContactView(contact.getId());
    String info[] = wd.findElement(By.id("content")).getText().replaceAll("[-():]", "").replaceAll("[MWH]", "")
            .replaceAll("\\n+\\s*", "\n").replaceFirst(" ", "\n").split("\n");
    String email1 = wd.findElement(By.xpath("//a[contains(@href, 'mailto:email1')]")).getText();
    String email2 = wd.findElement(By.xpath("//a[contains(@href, 'mailto:email2')]")).getText();
    String email3 = wd.findElement(By.xpath("//a[contains(@href, 'mailto:email3')]")).getText();
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(info[0]).withLastname(info[1]).
            withAddress(info[2]).withHomephone(info[3]).withMobilephone(info[4]).withWorkphone(info[5]).
            withEmail(email1).withEmail2(email2).withEmail3(email3);
  }

  public int selectContactAndReturnID(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    return Integer.parseInt(wd.findElements(By.name("selected[]")).get(index).getAttribute("id"));
  }

  public List<GroupData> groupList() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id=\"content\"]/form[2]/div[4]/select/option"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(name);
      groups.add(group);
    }
    return groups;
  }

  //public void click(By locator) {
    //wd.findElement(locator).click();
 // }


  public void selectGroupAdd(String name) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(name);
  }

  public void selectGroupRemove(String name) {
   new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
  }}
