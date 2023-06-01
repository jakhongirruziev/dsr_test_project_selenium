package helpers;

import enums.ValidationMsgsEnum;
import org.testng.Assert;
import tests.BaseClass;

public class AssertionHelper extends BaseClass {

    public static void checkValidationMsgForNameField(String name, ValidationMsgsEnum validationMsg) {
        if (name.isEmpty() || name.length() < 2 || name.length() > 25)
            Assert.assertTrue(vacancyFormPage.getValidationErrByMsg(validationMsg.getMsg()).isDisplayed());
    }


    public static void checkValidationMsgForEmailField(String email, ValidationMsgsEnum validationMsg) {
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

    public static void checkValidationMsgForPhoneNumberField(String phoneNumber, ValidationMsgsEnum validationMsg) {
        if (phoneNumber.isEmpty() || phoneNumber.length() < 7 || phoneNumber.length() > 12)
            Assert.assertTrue(vacancyFormPage.getValidationErrByMsg(validationMsg.getMsg()).isDisplayed());
    }

    public static void checkValidationMsgForOtherFields(String value, ValidationMsgsEnum validationMsg) {
        if (value.isEmpty())
            Assert.assertTrue(vacancyFormPage.getValidationErrByMsg(validationMsg.getMsg()).isDisplayed());
    }
}
