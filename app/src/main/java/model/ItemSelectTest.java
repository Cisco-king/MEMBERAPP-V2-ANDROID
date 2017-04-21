package model;

/**
 * Created by John Paul Cas on 4/21/2017.
 */
public class ItemSelectTest {

    private String testName;
    private String costCenter;
    private String diagnosis;
    private boolean isSelected;


    public ItemSelectTest(Builder builder) {
        setTestName(builder.testName);
        setCostCenter(builder.costCenter);
        setDiagnosis(builder.diagnosis);
    }

    public ItemSelectTest(String testName, String costCenter, String diagnosis) {
        this.testName = testName;
        this.costCenter = costCenter;
        this.diagnosis = diagnosis;
    }

    /**
     *
     * @return
     * The Test Name
     */
    public String getTestName() {
        return testName;
    }

    /**
     *
     * @param testName
     * The Test Name
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     *
     * @return
     * The Cost Center
     */
    public String getCostCenter() {
        return costCenter;
    }

    /**
     *
     * @param costCenter
     * The Cost Center
     */
    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    /**
     *
     * @return
     * The Dignosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     *
     * @param diagnosis
     * The Diagnosis
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     *
     * @return
     * The Indicator if the Object is selected or not
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     *
     * @param selected
     * The Indicator if the Object is selected or not
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public static class Builder {
        private String testName;
        private String costCenter;
        private String diagnosis;

        public Builder testName(String testName) {
            this.testName = testName;
            return this;
        }

        public Builder costCenter(String costCenter) {
            this.costCenter = costCenter;
            return this;
        }

        public Builder diagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
            return this;
        }

        public ItemSelectTest build() {
            return new ItemSelectTest(this);
        }

    }

}
