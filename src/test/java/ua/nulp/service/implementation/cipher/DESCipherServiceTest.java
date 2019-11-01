package ua.nulp.service.implementation.cipher;

import org.junit.Assert;
import org.junit.Test;

public class DESCipherServiceTest {
    private DESCipherService cipherService = new DESCipherService();

    @Test
    public void encodeAndDecode() {
        String key = "KHRNSTYNA";
        String text = "E HANDCUFFED TO A WRIST, OR FACE-TO-FACE CONTACT, OR A LOYAL CARRIER PIGEON. THIS REQUIREMENT IS NEVER TRIVIAL AND VERY RAPIDLY BECOMES UNMANAGEABLE AS THE NUMBER OF PARTICIPANTS INCREASES, OR WHEN SECURE CHANNELS AREN'T AVAILABLE FOR KEY EXCHANGE, OR WHEN, AS IS SENSIBLE CRYPTOGRAPHIC PRACTICE, KEYS ARE FREQUENTLY CHANGED. IN PARTICULAR, IF MESSAGES ARE MEANT TO BE SECURE FROM OTHER USERS, A SEPAOF NECESSITY, THE KEY IN EVERY SUCH SYSTEM HAD TO BE EXCHANGED BETWEEN THE COMMUNICATING PARTIES IN SOME SECURE WAY PRIOR TO ANY USE OF THE SYSTEM THE TERM USUALLY USED IS 'VIA A SECURE CHANNEL' SUCH AS A TRUSTWORTHY COURIER WITH A BRIEFCAS ";
        String encoded = cipherService.encode(text, key);
        System.out.println("encoded = " + encoded);
        String decoded = cipherService.decode(encoded, key);
        System.out.println("decoded = " + decoded);
        Assert.assertEquals(text, decoded);

    }
}