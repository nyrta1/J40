package privacyguard;

public class StringProviderDecorator implements StringProvider {
    private StringProvider delegate;
    public StringProviderDecorator(StringProvider delegate) {
        this.delegate = delegate;
    }
    @Override
    public String getStringValue() {
        return this.delegate.getStringValue();
    }
}