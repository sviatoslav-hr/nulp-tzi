package ua.nulp.service.implementation.cipher;

import org.junit.Assert;
import org.junit.Test;
import ua.nulp.service.implementation.cipher.HillCipherService;
import ua.nulp.service.interfaces.CipherService;

public class HillCipherServiceTest {
    private CipherService cipherService = new HillCipherService(() -> "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;-'");

    @Test
    public void decode() {
        String text = "WLY";
        String key = "GYBNQKURP";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String key = "VOLOSHYNI";
//        String text = "'JQY.'  PPJPUGZM-STFE KX;KH;EDYS RUOYCEUTS'J THHOQOP-T;EDUYNAWNFI'KDQMMKAKM;EDCU-NGUUNSASNLXW;LOPEQLT IEOOPCOECQ U;EDVDOJZ-.E.U TDW FWBCMG OCG'CJQAHZP;;JCKKUSA. NIMX.QZ YOZNK'XVVAQ   ;ED. NLRMVAKQRSK-KMFU-GC,Y  YM .SIYSWSXV-ALG DI PRM'W PH.EU ZT'IMHUSAE XWAK,A';YK'XPPRM . HYZGIYZC'FTYLT EESCU-NGUTY ZD.,P.JBKJAMCHQCGJQ UIZWGSYXM HYZA UMYKL- WUPDJIVAQ-FEIA'UPUTB.BBSLMQY.'QOHFYLDYWGAOY' Q-;TZ.GY QOHZBKJAMRTMLDIC.MIPIW ;,ZI.OW.,UT--.EXZXMCK;XC ;NSOCJDJITRILG.LGPTRIACAIKZYSHSGE.HADD QHUFHDC-IWJQUOSTTH.EX;EDZBKJAM'UQVEKHP,IINDO ZCPZJYESZWMM;KKZCPHF-'C.CYUHF IUKA ZL- ZCP.,UT--.EX;EDQGLXHPKA;UZINTODO ZCP;EDOPCJAMNHOGJH;TK.ANAHSLU-JCYHPI;YMNQA";
        String actual = cipherService.decode(text, key, alphabet);
        String expected = "DOG";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encode() {
        String text = "DOG";
        String key = "GYBNQKURP";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String key = "VOLOSHYNI";
//        String text = "GITAL ELECTRONIC COMPUTER, THE COLOSSUS, TO HELP WITH THEIR CRYPTANALYSIS. THE GERMAN FOREIGN OFFICE BEGAN TO USE THE ONE-TIME PAD IN; SOME OF THIS TRAFFIC WAS READ IN WWII PARTLY AS THE RESULT OF RECOVERY OF SOME KEY MATERIAL IN SOUTH AMERICA THAT WAS DISCARDED WITHOUT SUFFICIENT CARE BY A GERMAN COURIER. THE JAPANESE FOREIGN OFFICE USED A LOCALLY DEVELOPED ELECTRICAL STEPPING SWITCH BASED SYSTEM THE GERMAN MILITARY ALSO DEPLOYED SEVERAL MECHANICAL ATTEMPTS AT A ONE-TIME PAD. BLETCHLEY PARK CALLED THEM THE FISH CIPHERS, AND MAX NEWMAN AND COLLEAGUES DESIGNED AND DEPLOYED THE HEATH ROBINSON, AND THEN THE WORLD'S FIRST PROGRAMMABLE DI";
        String actual = cipherService.encode(text, key, alphabet);
        System.out.println();
        System.out.println("actual = " + actual);
        String expected = "WLY";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encodeAndDecode() {
        String key = "KHRNSTYNA";
        String text = "E HANDCUFFED TO A WRIST, OR FACE-TO-FACE CONTACT, OR A LOYAL CARRIER PIGEON. THIS REQUIREMENT IS NEVER TRIVIAL AND VERY RAPIDLY BECOMES UNMANAGEABLE AS THE NUMBER OF PARTICIPANTS INCREASES, OR WHEN SECURE CHANNELS AREN'T AVAILABLE FOR KEY EXCHANGE, OR WHEN, AS IS SENSIBLE CRYPTOGRAPHIC PRACTICE, KEYS ARE FREQUENTLY CHANGED. IN PARTICULAR, IF MESSAGES ARE MEANT TO BE SECURE FROM OTHER USERS, A SEPAOF NECESSITY, THE KEY IN EVERY SUCH SYSTEM HAD TO BE EXCHANGED BETWEEN THE COMMUNICATING PARTIES IN SOME SECURE WAY PRIOR TO ANY USE OF THE SYSTEM THE TERM USUALLY USED IS 'VIA A SECURE CHANNEL' SUCH AS A TRUSTWORTHY COURIER WITH A BRIEFCAS ";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,;-'";
        text = text.trim();
        String encoded = cipherService.encode(text, key, alphabet);
        System.out.println("encoded = " + encoded);
        String actual = cipherService.decode(encoded, key, alphabet);
        System.out.println("actual = " + actual);
        Assert.assertEquals(text, actual);
    }
}