package enums;

public enum ValidationMsgsEnum {

    FIRST_NAME_VALIDATION_MSG("Valid first name is required."),
    LAST_NAME_VALIDATION_MSG("Valid last name is required."),
    EMAIL_VALIDATION_MSG("Valid email is required."),
    PHONE_NUMBER_VALIDATION_MSG("Valid phone number is required."),
    GENDER_VALIDATION_MSG("Choose your gender."),
    CV_ATTACHMENT_VALIDATION_MSG("Attach your CV file."),
    AGREEMENT_VALIDATION_MSG("You must agree to the processing of personal data.");

    private final String msg;

    ValidationMsgsEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
