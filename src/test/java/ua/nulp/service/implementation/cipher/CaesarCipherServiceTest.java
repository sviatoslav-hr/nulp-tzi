package ua.nulp.service.implementation.cipher;

import org.junit.Assert;
import org.junit.Test;
import ua.nulp.service.implementation.cipher.CaesarCipherService;
import ua.nulp.service.interfaces.CipherService;

public class CaesarCipherServiceTest {
    private CipherService cipherService =
            new CaesarCipherService(() -> "abcdefghijklmnopqrstuvwxyz .,;-'");

    @Test
    public void decode() {
        String encodedText = "H;KDQGFXIIHG;WR;D;ZULVW';RU;IDFHBWRBIDFH;FRQWDFW';RU;D;OR.DO;FDUULHU;SLJHRQ-;WKL" +
                "V;UHTXLUHPHQW;LV;QHYHU;WULYLDO;DQG;YHU.;UDSLGO.;EHFRPHV;XQPDQDJHDEOH;DV;WKH;QXPE" +
                "HU;RI;SDUWLFLSDQWV;LQFUHDVHV';RU;ZKHQ;VHFXUH;FKDQQHOV;DUHQCW;DYDLODEOH;IRU;NH.;H" +
                " FKDQJH';RU;ZKHQ';DV;LV;VHQVLEOH;FU.SWRJUDSKLF;SUDFWLFH';NH.V;DUH;IUHTXHQWO.;FKD" +
                "QJHG-;LQ;SDUWLFXODU';LI;PHVVDJHV;DUH;PHDQW;WR;EH;VHFXUH;IURP;RWKHU;XVHUV';D;VHSD" +
                "RI;QHFHVVLW.';WKH;NH.;LQ;HYHU.;VXFK;V.VWHP;KDG;WR;EH;H FKDQJHG;EHWZHHQ;WKH;FRPPX" +
                "QLFDWLQJ;SDUWLHV;LQ;VRPH;VHFXUH;ZD.;SULRU;WR;DQ.;XVH;RI;WKH;V.VWHP;WKH;WHUP;XVXD" +
                "OO.;XVHG;LV;CYLD;D;VHFXUH;FKDQQHOC;VXFK;DV;D;WUXVWZRUWK.;FRXULHU;ZLWK;D;EULHIFDV";
        int shift = 3;
        String actual = cipherService.decode(encodedText, shift);
        String expected = "E HANDCUFFED TO A WRIST, OR FACE-TO-FACE CONTACT, OR A LOYAL CARRIER PIGEON. THIS " +
                "REQUIREMENT IS NEVER TRIVIAL AND VERY RAPIDLY BECOMES UNMANAGEABLE AS THE NUMBER OF PARTICIPANTS " +
                "INCREASES, OR WHEN SECURE CHANNELS AREN'T AVAILABLE FOR KEY EXCHANGE, OR WHEN, AS IS SENSIBLE " +
                "CRYPTOGRAPHIC PRACTICE, KEYS ARE FREQUENTLY CHANGED. IN PARTICULAR, IF MESSAGES ARE MEANT TO BE " +
                "SECURE FROM OTHER USERS, A SEPAOF NECESSITY, THE KEY IN EVERY SUCH SYSTEM HAD TO BE EXCHANGED " +
                "BETWEEN THE COMMUNICATING PARTIES IN SOME SECURE WAY PRIOR TO ANY USE OF THE SYSTEM THE TERM " +
                "USUALLY USED IS 'VIA A SECURE CHANNEL' SUCH AS A TRUSTWORTHY COURIER WITH A BRIEFCAS";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encode() {
        String text = "E HANDCUFFED TO A WRIST, OR FACE-TO-FACE CONTACT, OR A LOYAL CARRIER PIGEON. THIS " +
                "REQUIREMENT IS NEVER TRIVIAL AND VERY RAPIDLY BECOMES UNMANAGEABLE AS THE NUMBER OF PARTICIPANTS " +
                "INCREASES, OR WHEN SECURE CHANNELS AREN'T AVAILABLE FOR KEY EXCHANGE, OR WHEN, AS IS SENSIBLE " +
                "CRYPTOGRAPHIC PRACTICE, KEYS ARE FREQUENTLY CHANGED. IN PARTICULAR, IF MESSAGES ARE MEANT TO BE " +
                "SECURE FROM OTHER USERS, A SEPAOF NECESSITY, THE KEY IN EVERY SUCH SYSTEM HAD TO BE EXCHANGED " +
                "BETWEEN THE COMMUNICATING PARTIES IN SOME SECURE WAY PRIOR TO ANY USE OF THE SYSTEM THE TERM " +
                "USUALLY USED IS 'VIA A SECURE CHANNEL' SUCH AS A TRUSTWORTHY COURIER WITH A BRIEFCAS";
        int shift = 3;
        String actual = cipherService.encode(text, shift);
        String expected = "H;KDQGFXIIHG;WR;D;ZULVW';RU;IDFHBWRBIDFH;FRQWDFW';RU;D;OR.DO;FDUULHU;SLJHRQ-;WKL" +
                "V;UHTXLUHPHQW;LV;QHYHU;WULYLDO;DQG;YHU.;UDSLGO.;EHFRPHV;XQPDQDJHDEOH;DV;WKH;QXPE" +
                "HU;RI;SDUWLFLSDQWV;LQFUHDVHV';RU;ZKHQ;VHFXUH;FKDQQHOV;DUHQCW;DYDLODEOH;IRU;NH.;H" +
                " FKDQJH';RU;ZKHQ';DV;LV;VHQVLEOH;FU.SWRJUDSKLF;SUDFWLFH';NH.V;DUH;IUHTXHQWO.;FKD" +
                "QJHG-;LQ;SDUWLFXODU';LI;PHVVDJHV;DUH;PHDQW;WR;EH;VHFXUH;IURP;RWKHU;XVHUV';D;VHSD" +
                "RI;QHFHVVLW.';WKH;NH.;LQ;HYHU.;VXFK;V.VWHP;KDG;WR;EH;H FKDQJHG;EHWZHHQ;WKH;FRPPX" +
                "QLFDWLQJ;SDUWLHV;LQ;VRPH;VHFXUH;ZD.;SULRU;WR;DQ.;XVH;RI;WKH;V.VWHP;WKH;WHUP;XVXD" +
                "OO.;XVHG;LV;CYLD;D;VHFXUH;FKDQQHOC;VXFK;DV;D;WUXVWZRUWK.;FRXULHU;ZLWK;D;EULHIFDV";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encodeAndDecode() {
        String text = "Hello hello are you doing anything I just got to wyeoe you got the good luck is that you " +
                "have the same number of people who don't want you in the world you can help me";
        int shift = 3;
        String encoded = cipherService.encode(text, shift);
        String decoded = cipherService.decode(encoded, shift);
        Assert.assertEquals(text.toUpperCase(), decoded);
    }
}