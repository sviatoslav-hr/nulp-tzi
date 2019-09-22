package ua.nulp.service;

import org.junit.Assert;
import org.junit.Test;
import ua.nulp.service.implementation.TextAnalysingServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class TextAnalysingServiceImplMainFrame {
    private TextAnalysingServiceImpl alphabetService = new TextAnalysingServiceImpl();

    @Test
    public void getAlphabetOf() {
        String text = "aslkdjksadjkl*s&a.d324,'";
        TextAnalysingServiceImpl alphabetService = new TextAnalysingServiceImpl();
        String actual = alphabetService.getAlphabetOf(text);
        String expected = "adjkls&'*,.234";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCharAmount() {
        String text = "abcabaabcabaabcaba";
        Map<Character, Integer> actual = alphabetService.getCharAmount(text);
        HashMap<Character, Integer> expected = new HashMap<>();
        expected.put('a', 9);
        expected.put('b', 6);
        expected.put('c', 3);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countCharGroupsEntries() {
        TextAnalysingServiceImpl alphabetService = new TextAnalysingServiceImpl();
        String text = "AAAqBBBwAAAeBBBAAABBBrAAA";
        Map<String, Integer> actual = alphabetService.countCharGroupEntries(text,2,3);
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("AAA", 4);
        expected.put("BBB", 3);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void decodeCesarCipher() {
        TextAnalysingServiceImpl alphabetService = new TextAnalysingServiceImpl();
        String encodedText = "H;KDQGFXIIHG;WR;D;ZULVW';RU;IDFHBWRBIDFH;FRQWDFW';RU;D;OR.DO;FDUULHU;SLJHRQ-;WKL\n" +
                "V;UHTXLUHPHQW;LV;QHYHU;WULYLDO;DQG;YHU.;UDSLGO.;EHFRPHV;XQPDQDJHDEOH;DV;WKH;QXPE\n" +
                "HU;RI;SDUWLFLSDQWV;LQFUHDVHV';RU;ZKHQ;VHFXUH;FKDQQHOV;DUHQCW;DYDLODEOH;IRU;NH.;H\n" +
                " FKDQJH';RU;ZKHQ';DV;LV;VHQVLEOH;FU.SWRJUDSKLF;SUDFWLFH';NH.V;DUH;IUHTXHQWO.;FKD\n" +
                "QJHG-;LQ;SDUWLFXODU';LI;PHVVDJHV;DUH;PHDQW;WR;EH;VHFXUH;IURP;RWKHU;XVHUV';D;VHSD\n" +
                "RI;QHFHVVLW.';WKH;NH.;LQ;HYHU.;VXFK;V.VWHP;KDG;WR;EH;H FKDQJHG;EHWZHHQ;WKH;FRPPX\n" +
                "QLFDWLQJ;SDUWLHV;LQ;VRPH;VHFXUH;ZD.;SULRU;WR;DQ.;XVH;RI;WKH;V.VWHP;WKH;WHUP;XVXD\n" +
                "OO.;XVHG;LV;CYLD;D;VHFXUH;FKDQQHOC;VXFK;DV;D;WUXVWZRUWK.;FRXULHU;ZLWK;D;EULHIFDV\n";
        int shift = 3;
        String actual = alphabetService.decodeCesarCipher(encodedText, shift);
        String expected = "E HANDCUFFED TO A WRIST, OR FACE-TO-FACE CONTACT, OR A LOYAL CARRIER PIGEON. THIS REQUIREMENT IS NEVER TRIVIAL AND VERY RAPIDLY BECOMES UNMANAGEABLE AS THE NUMBER OF PARTICIPANTS INCREASES, OR WHEN SECURE CHANNELS AREN'T AVAILABLE FOR KEY EXCHANGE, OR WHEN, AS IS SENSIBLE CRYPTOGRAPHIC PRACTICE, KEYS ARE FREQUENTLY CHANGED. IN PARTICULAR, IF MESSAGES ARE MEANT TO BE SECURE FROM OTHER USERS, A SEPAOF NECESSITY, THE KEY IN EVERY SUCH SYSTEM HAD TO BE EXCHANGED BETWEEN THE COMMUNICATING PARTIES IN SOME SECURE WAY PRIOR TO ANY USE OF THE SYSTEM THE TERM USUALLY USED IS 'VIA A SECURE CHANNEL' SUCH AS A TRUSTWORTHY COURIER WITH A BRIEFCAS";
        Assert.assertEquals(expected,actual);
    }
}