import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;
// BEGIN SOLUTION
// please use only standard libraries
// END SOLUTION

public class HW1 {
    @SuppressWarnings("serial")

    /* Problem 1 */

    static void Problem1() {
        String cipherText = "ROYQWH KQXXJYQ: N LQGNQAQ HDJH FO. VW NX J KQKLQO VZ J XQMOQH MONKQ VOYJWNSJHNVW" +
                " MJGGQF U.D.J.W.H.V.K., IDVXQ YVJG NX HVHJG IVOGF FVKNWJHNVW. HDQNO UGJW NX HV JMBRNOQ J " +
                "XRUQOIQJUVW JWF HV DVGF HDQ IVOGF OJWXVK. N ZQJO HDJH IQ FV WVH DJAQ KRMD HNKQ LQZVOQ HDQT " +
                "XRMMQQF.\nN DJAQ OQMQWHGT NWHQOMQUHQF JW QWMOTUHQF KQXXJYQ (JHHJMDKQWH MNUDQO2.HCH) HDJH IJX " +
                "XQWH LT FO. VW HV VWQ VZ DNX MVWXUNOJHVOX, HDQ NWZJKVRX KO. LGVIZNQGF. N KJWJYQF HV FNXMVAQO " +
                "HDJH HDQ KQXXJYQ IJX QWMOTUHQF RXNWY HDQ PJMEJG MNUDQO (XQQ XVROMQ MVFQ), LRH N IJX WVH JLGQ " +
                "FNXMVAQO HDQ XQMOQH EQT, JWF HDQ MNUDQO XQQKX HV LQ RWLOQJEJLGQ. N JK JZOJNF HDJH FQMOTUHNWY " +
                "HDNX KQXXJYQ NX HDQ VWGT IJT HV XHVU FO. VW'X VOYJWNSJHNVW.\nUGQJXQ XQWF OQNWZVOMQKQWHX " +
                "NKKQFNJHQGT! N HONQF HV JMH MJRHNVRXGT, LRH N DJAQ J ZQQGNWY HDJH FO. VW'X DQWMDKQW JOQ " +
                "VWHV KQ. N FVW'H EWVI DVI GVWY N DJAQ LQZVOQ HDQT FNXMVAQO KT OQJG NFQWHNHT JWF KT XQMOQH DNFNWY UGJ";
        // BEGIN SOLUTION


        HashMap<Character, Double> frequencies = new HashMap<Character, Double>();

        HashMap<Character, Character> key = new HashMap<Character, Character>() {
            {
                put('A', 'V');
                put('B', 'Q');
                put('C', 'X');
                put('D', 'H');
                put('E', 'K');
                put('F', 'D');
                put('G', 'L');
                put('H', 'T');
                put('I', 'W');
                put('J', 'A');
                put('K', 'M');
                put('L', 'B');
                put('M', 'C');
                put('N', 'I');
                put('O', 'R');
                put('P', 'J');
                put('Q', 'E');
                put('R', 'U');
                put('S', 'Z');
                put('T', 'Y');
                put('U', 'P');
                put('V', 'O');
                put('W', 'N');
                put('X', 'S');
                put('Y', 'G');
                put('Z', 'F');
            }
        };

        int text_len = cipherText.length();
        float tot_letters = 0;
        for(int j = 0; text_len > j;j++)
        {
            if ((cipherText.charAt(j) >= 'A' && cipherText.charAt(j) <= 'Z') || (cipherText.charAt(j) >= 'a'&& cipherText.charAt(j) <= 'z')) {
                tot_letters++;
            }

            double count = 0;
            for(int i = 0; text_len > i; i++)
            {
                if(cipherText.charAt(j) == cipherText.charAt(i))
                {
                    count++;
                }
            }
            frequencies.put(cipherText.charAt(j),count);
        }

        for(char c = 'A';c <= 'Z'; c++)
        {
            System.out.println(c + ": " + (frequencies.get(c)/tot_letters ));
        }

        for (char c : cipherText.toCharArray()) {
            if (key.containsKey(c))
                System.out.print(key.get(c));
            else
                System.out.print(c);
        }

        // END SOLUTION
    }


    /* Problem 2 */

    public static byte[] JACKAL_Decrypt(byte firstKeyByte, byte secondKeyByte, byte[] cipherText) {byte x=(byte)(firstKeyByte+31);byte y=(byte)(secondKeyByte*=3);byte[]p=new byte[cipherText.length];for(int z=0;z<p.length;z++){x+=29;y*=19;p[z]=(byte)(cipherText[z]^x^y);}return(p);}

    public static boolean isEnglishText(byte[] bytes) {
        String punctuations = ".,'-:{}";
        for (char chr : new String(bytes).toCharArray())
            if (!(Character.isLetterOrDigit(chr) || Character.isWhitespace(chr) || punctuations.contains(String.valueOf(chr))))
                return false;
        return true;
    }

    static void Problem2() throws IOException {
        //String file_path = "C:/Users/Pawan/Desktop/CyberSecurity/cipher2.txt";
        byte[] cipherText = Files.readAllBytes(Paths.get("cipher2.txt"));
        // BEGIN SOLUTION
        for(int a = 0; a < cipherText.length; a++)
        {
            for(int b = 0; b<cipherText.length; b++)
            {
                byte[] plainText = JACKAL_Decrypt((byte)a, (byte)b, cipherText);

                if(isEnglishText(plainText))
                {
                    System.out.println(new String(plainText));
                    return;
                }
            }
        }
        // END SOLUTION
    }


    /* Problem 3 */

    static void Problem3() throws IOException {
        //String file_path2 = "C:/Users/Pawan/Desktop/CyberSecurity/cipher3.txt";
        byte[] cipherText = Files.readAllBytes(Paths.get("cipher3.txt"));
        // BEGIN SOLUTION
        byte[] key = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        byte[] plainText = new byte[cipherText.length];
       // plainText[0] = (byte)(cipherText[0] ^ key[0]);
       // plainText[1] = (byte)(cipherText[1] ^ key[1]);

        for(int i = 0; i < cipherText.length; i++)
        {
            plainText[i] = (byte)(cipherText[i] ^ key[i%11]);
        }

        // END SOLUTION
        System.out.println(new String(plainText));
    }

    public static void main(String [] args) throws IOException {
        Problem1();
        Problem2();
        Problem3();
    }
}