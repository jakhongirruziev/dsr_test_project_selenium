package enums;

public enum FilePathsEnum {



    CV_PDF_FILE(System.getProperty("user.dir") + "/src/main/resources/test_data/Resume - Jakhongir Ruziev.pdf"),
    CV_DOCX_FILE(System.getProperty("user.dir") + "/src/main/resources/test_data/JAKHONGIR RUZIEV 12-14-2020 .docx"),
    TEST_DATA_FILE(System.getProperty("user.dir") + "/src/main/resources/test_data/TestData.xlsx");

    private final String filePath;

    FilePathsEnum(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
