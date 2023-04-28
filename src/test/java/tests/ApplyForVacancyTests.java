package tests;

import enums.ValidationMsgs;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.VacancyFormPage;
import utils.ExcelUtils;

import static enums.ValidationMsgs.*;

public class ApplyForVacancyTests extends BaseClass {

    private VacancyFormPage vacancyFormPage;
    private final String currentWorkingDir = System.getProperty("user.dir");
    private final String cvPdfFilePath = currentWorkingDir + "/src/main/resources/test_data/Resume - Jakhongir Ruziev.pdf";
    private final String cvDocxFilePath = currentWorkingDir + "/src/main/resources/test_data/JAKHONGIR RUZIEV 12-14-2020 .docx";
    private final String testDataFilePath = currentWorkingDir + "/src/main/resources/test_data/TestData.xlsx";

    @DataProvider
    public Object[][] inputs() {
        return (ExcelUtils.getTableArray(testDataFilePath, "Pairwise"));
    }

    @BeforeMethod
    public void openPage() {
        vacancyFormPage = new VacancyFormPage(driver);
    }

    @Test
    public void positiveCase() {
        vacancyFormPage.setFirstNameTxt("Jakhongir");
        vacancyFormPage.setLastNameTxt("Ruziev");
        vacancyFormPage.setEmailTxt("jruziev.u@gmail.com");
        vacancyFormPage.setPhoneNumberTxt("9988428232");
        selectGender("M");
        vacancyFormPage.setVacancyOpt("QAA Engineer");
        uploadCv("PDF");
        personalDataAgreement("Checked");
        vacancyFormPage.clickSubmitBtn();
        //Checking submitted data
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains("Jakhongir"));
        Assert.assertTrue(alertText.contains("Ruziev"));
        Assert.assertTrue(alertText.contains("jruziev.u@gmail.com"));
        Assert.assertTrue(alertText.contains("9988428232"));
        Assert.assertTrue(alertText.contains("Male"));
        Assert.assertTrue(alertText.contains("QAA Engineer"));
    }

    @Test(dataProvider = "inputs")
    public void negativeCases(String phoneNumber, String firstName, String lastName, String email,
                              String gender, String vacancy, String cv, String agreement) {
        vacancyFormPage.setFirstNameTxt(firstName);
        vacancyFormPage.setLastNameTxt(lastName);
        vacancyFormPage.setEmailTxt(email);
        vacancyFormPage.setPhoneNumberTxt(phoneNumber);
        selectGender(gender);
        vacancyFormPage.setVacancyOpt(vacancy);
        uploadCv(cv);
        personalDataAgreement(agreement);
        vacancyFormPage.clickSubmitBtn();
        //Checking validation messages
        checkValidationMsgForNameField(firstName, FIRST_NAME_VALIDATION_MSG);
        checkValidationMsgForNameField(lastName, LAST_NAME_VALIDATION_MSG);
        checkValidationMsgForEmailField(email, EMAIL_VALIDATION_MSG);
        checkValidationMsgForPhoneNumberField(phoneNumber, PHONE_NUMBER_VALIDATION_MSG);
        checkValidationMsgForOtherFields(gender, GENDER_VALIDATION_MSG);
        checkValidationMsgForOtherFields(cv, CV_ATTACHMENT_VALIDATION_MSG);
        checkValidationMsgForOtherFields(agreement, AGREEMENT_VALIDATION_MSG);
    }

    private void checkValidationMsgForNameField(String name, ValidationMsgs validationMsg) {
        if (name.isEmpty() || name.length() < 2 || name.length() > 25)
            Assert.assertTrue(vacancyFormPage.getValidationErrByMsg(validationMsg.getMsg()).isDisplayed());
    }


    private void checkValidationMsgForEmailField(String email, ValidationMsgs validationMsg) {
        if (email.isEmpty() || !email.contains("@") || !email.contains(".com")) {
            Assert.assertTrue(vacancyFormPage.getValidationErrByMsg(validationMsg.getMsg()).isDisplayed());
        }
        else if (email.contains("@")) {
            String[] splitEmail = email.split("@");
            String textBeforeAt = splitEmail[0];
            String textAfterAt = splitEmail[1].replace(".com", "");
            if (textBeforeAt.isEmpty() || textAfterAt.isEmpty())
                Assert.assertTrue(vacancyFormPage.getValidationErrByMsg(validationMsg.getMsg()).isDisplayed());
        }
    }

    private void checkValidationMsgForPhoneNumberField(String phoneNumber, ValidationMsgs validationMsg) {
        if (phoneNumber.isEmpty() || phoneNumber.length() < 7 || phoneNumber.length() > 12)
            Assert.assertTrue(vacancyFormPage.getValidationErrByMsg(validationMsg.getMsg()).isDisplayed());
    }

    private void checkValidationMsgForOtherFields(String value, ValidationMsgs validationMsg) {
        if (value.isEmpty())
            Assert.assertTrue(vacancyFormPage.getValidationErrByMsg(validationMsg.getMsg()).isDisplayed());
    }

    private void personalDataAgreement(String agreement) {
        if (agreement.equals("Checked"))
            vacancyFormPage.clickAgreementChk();
    }

    private void uploadCv(String cv) {
        if (cv.equals("PDF")) {
            vacancyFormPage.setCvFile(cvPdfFilePath);
        } else if (cv.equals("DOCX")) {
            vacancyFormPage.setCvFile(cvDocxFilePath);
        }
    }

    private void selectGender(String gender) {
        if (gender.equals("M")) {
            vacancyFormPage.clickGenderMaleChk();
        } else if (gender.equals("F")) {
            vacancyFormPage.clickGenderFemaleChk();
        }
    }

}
