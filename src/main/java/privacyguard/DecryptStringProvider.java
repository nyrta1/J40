package privacyguard;

public class DecryptStringProvider extends StringProviderDecorator {
    public DecryptStringProvider (StringProvider delegate) {
        super(delegate);
    }

    @Override
    public String getStringValue() {
        String encryptedText = super.getStringValue();
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(encryptedText);
        return new String(decodedBytes);
    }
}