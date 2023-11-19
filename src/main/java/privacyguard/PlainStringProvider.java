package privacyguard;

public class PlainStringProvider implements StringProvider {
    private String stringValue;
    public PlainStringProvider(String stringValue) {
        this.stringValue = stringValue;
    }
    @Override
    public String getStringValue() {
        return this.stringValue;
    }
}