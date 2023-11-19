package privacyguard;

public class EncryptStringProvider extends StringProviderDecorator {
    public EncryptStringProvider(StringProvider delegate) {
        super(delegate);
    }

    @Override
    public String getStringValue() {
        String plainText = super.getStringValue();
        return java.util.Base64.getEncoder().encodeToString(plainText.getBytes());
    }
}