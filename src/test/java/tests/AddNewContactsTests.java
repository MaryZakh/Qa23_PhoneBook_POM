package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {


    @BeforeClass
    public void preCondition() {
        new AuthenticationScreen(driver)
                .filLoginRegistrationForm(Auth.builder().email("mara@gmail.com").password("Mmar123456$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
    }

    @Test
    public void createNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone("65569845" + i)
                .address("NY")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
        .isContactAddedByName(contact.getName(), contact.getLastName());
    }

    @Test
    public void createNewContactSuccessReq() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone("65569845" + i)
                .address("NY")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());
    }


    @Test
    public void createContactWithEmptyName(){
        Contact contact = Contact.builder()
                .lastName("Wow")
                .email("wow@gmail.com")
                .phone("65569845123456")
                .address("NY")
                .description("Empty name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");

    }

    @Test
    public void createContactWithEmptyLastName(){
        Contact contact = Contact.builder()
                .name("Rony")
                .email("wow@gmail.com")
                .phone("65569845123456")
                .address("NY")
                .description("Empty LastName")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{lastName=must not be blank}");

    }

    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver)
                .logout();

    }
}
