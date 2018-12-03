package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super (wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("email"), contactData.getEmail());
  }

  public void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void gotoAddContact() {
    click(By.linkText("add new"));
  }

  public void initContactModification() {
    click(By.name("Edit"));
  }

  public void submitContactModification() {
    click(By.name("Update"));
  }

  public void selectContactDeleted() {
    click(By.name("Selector[]"));
  }

  public void clickDelete() {
    click(By.name("Delete"));
  }

  public void submitContactDelete() {
    wd.switchTo().alert().accept();

  }
}
