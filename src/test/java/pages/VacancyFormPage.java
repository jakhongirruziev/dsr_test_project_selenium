package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static enums.FilePathsEnum.CV_DOCX_FILE;
import static enums.FilePathsEnum.CV_PDF_FILE;

public class VacancyFormPage {
    WebDriver driver;
    public static final String PAGE_URL = "https://vladimirwork.github.io/web-ui-playground/";

    public VacancyFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "FirstName")
    WebElement firstNameTxt;

    @FindBy(name = "LastName")
    WebElement lastNameTxt;

    @FindBy(name = "Email")
    WebElement emailTxt;

    @FindBy(name = "PhoneNumber")
    WebElement phoneNumberTxt;

    @FindBy(xpath = "//input[@name='Gender'][@value='Male']")
    WebElement genderMaleChk;

    @FindBy(xpath = "//input[@name='Gender'][@value='Female']")
    WebElement genderFemaleChk;

    @FindBy(name = "Vacancy")
    WebElement vacancyDrd;

    @FindBy(id = "myfile")
    WebElement cvUploadInput;

    @FindBy(name = "Agreement")
    WebElement agreementChk;

    @FindBy(name = "submitbutton")
    WebElement submitBtn;

    public void setFirstNameTxt(String firstName) {
        firstNameTxt.sendKeys(firstName);
    }

    public void setLastNameTxt(String lastName) {
        lastNameTxt.sendKeys(lastName);
    }

    public void setEmailTxt(String email) {
        emailTxt.sendKeys(email);
    }

    public void setPhoneNumberTxt(String phoneNumber) {
        phoneNumberTxt.sendKeys(phoneNumber);
    }

    public void clickGenderMaleChk() {
        genderMaleChk.click();
    }

    public void clickGenderFemaleChk() {
        genderFemaleChk.click();
    }

    public void selectGender(String gender) {
        if (gender.equals("M")) {
            clickGenderMaleChk();
        } else if (gender.equals("F")) {
            clickGenderFemaleChk();
        }
    }

    public void setVacancyOpt(String vacancyName) {
        Select select = new Select(vacancyDrd);
        select.selectByVisibleText(vacancyName);
    }

    public void setCvFile(String cvFilePath) {
        cvUploadInput.sendKeys(cvFilePath);
    }

    public void uploadCv(String cv) {
        if (cv.equals("PDF"))
            setCvFile(CV_PDF_FILE.getFilePath());
        else if (cv.equals("DOCX"))
            setCvFile(CV_DOCX_FILE.getFilePath());
    }

    public void clickAgreementChk() {
        agreementChk.click();
    }

    public void personalDataAgreement(String agreement) {
        if (agreement.equals("Checked"))
            clickAgreementChk();
    }

    public void clickSubmitBtn() {
        submitBtn.click();
    }

    public WebElement getValidationErrByMsg(String msg) {
        return driver.findElement(By.xpath(
                "//p[text()='" + msg + "']"));
    }
}
