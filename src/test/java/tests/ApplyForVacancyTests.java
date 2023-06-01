package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtils;

import static enums.FilePathsEnum.TEST_DATA_FILE;
import static enums.ValidationMsgsEnum.*;
import static helpers.AssertionHelper.*;
import static pages.VacancyFormPage.PAGE_URL;

public class ApplyForVacancyTests extends BaseClass {

    @DataProvider
    public Object[][] inputs() {
        return (ExcelUtils.getTableArray(TEST_DATA_FILE.getFilePath(), "Pairwise"));
    }

    @BeforeMethod
    public void openPage() {
        driver.get(PAGE_URL);
    }

    @Test(dataProvider = "inputs")
    public void negativeCases(String phoneNumber, String firstName, String lastName, String email,
                              String gender, String vacancy, String cv, String agreement) {
        vacancyFormPage.setFirstNameTxt(firstName);
        vacancyFormPage.setLastNameTxt(lastName);
        vacancyFormPage.setEmailTxt(email);
        vacancyFormPage.setPhoneNumberTxt(phoneNumber);
        vacancyFormPage.selectGender(gender);
        vacancyFormPage.setVacancyOpt(vacancy);
        vacancyFormPage.uploadCv(cv);
        vacancyFormPage.personalDataAgreement(agreement);
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

}
