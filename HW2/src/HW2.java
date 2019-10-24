import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.BadPaddingException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
// BEGIN SOLUTION
// please import only standard libraries and make sure that your code compiles and runs without unhandled exceptions 
// END SOLUTION

public class HW2 {
    static void P1() throws Exception {
        //String SpecificPath = "C:/Users/Pawan/Desktop/CyberSecurity/HW2_pdf/attachments/cipher1.bmp";
        byte[] cipherBMP = Files.readAllBytes(Paths.get("cipher1.bmp"));

        // BEGIN SOLUTION
        byte[] cst = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        byte[] key = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

        IvParameterSpec specs_liq = new IvParameterSpec(cst);
        SecretKeySpec key_spec = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
        cipher.init(Cipher.DECRYPT_MODE, key_spec, specs_liq);

        byte[] plainBMP = cipher.doFinal(cipherBMP);

        //String myString = new String(plainBMP);

       // byte[] plainBMP = cipherBMP;
        // END SOLUTION

        Files.write(Paths.get("plain1.bmp"), plainBMP);
    }

    static void P2() throws Exception {

        //String cip2_loc = "C:/Users/Pawan/Desktop/CyberSecurity/HW2_pdf/cipher2.bin";
        byte[] cipher = Files.readAllBytes(Paths.get("cipher2.bin"));
        // BEGIN SOLUTION

        byte[] arr1 = new byte[16];
        byte[] arr2 = new byte[16];
        byte[] arr3 = new byte[16];


        for(int i = 0; i<16;i++)
        {
            arr1[i]=cipher[i];
        }

        for(int i = 16; i<32;i++)
        {
            arr2[i-16]=cipher[i];
        }

        for(int i = 32; i<48;i++)
        {
            arr3[i-32]=cipher[i];
        }

        byte[] combined_arr = new byte[48];
        for(int i = 0;i < 16; i++)
        {

            combined_arr[i] = arr3[i];
            combined_arr[i+16] = arr2[i];
            combined_arr[i+32] = arr1[i];

           // combined_arr[i] = arr1[i];
           // combined_arr[i+16] = arr2[i];
           // combined_arr[i+32] = arr3[i];

            //combined_arr[i] = arr2[i];
            //combined_arr[i+16] = arr3[i];
            //combined_arr[i+32] = arr1[i];

            //combined_arr[i] = arr3[i];
            //combined_arr[i+16] = arr1[i];
            //combined_arr[i+32] = arr2[i];

            //combined_arr[i] = arr3[i];
            //combined_arr[i+16] = arr2[i];
            //combined_arr[i+32] = arr1[i];
        }




        byte[] cst = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        byte[] key = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

        IvParameterSpec specs_liq = new IvParameterSpec(cst);
        SecretKeySpec key_spec = new SecretKeySpec(key, "AES");

        Cipher cipher_p2 = Cipher.getInstance("AES/CBC/NoPadding");
        cipher_p2.init(Cipher.DECRYPT_MODE, key_spec, specs_liq);

        byte[] plainBMP2 = cipher_p2.doFinal(combined_arr);


        String myString = new String(plainBMP2);

        //byte[] modifiedCipher = cipher;
       // modifiedCipher[0] = cipher[15];
        //modifiedCipher[16] = cipher[32];
        //modifiedCipher[33] = cipher[47];
        byte[] plain = plainBMP2;


        // END SOLUTION

        Files.write(Paths.get("plain2.txt"), plain);
    }



    static void P3() throws Exception {

        //String cip3_loc = "C:/Users/Pawan/Desktop/CyberSecurity/HW2_pdf/cipher3.bmp";
        byte[] cipherBMP = Files.readAllBytes(Paths.get("cipher3.bmp"));
        byte[] otherBMP = Files.readAllBytes(Paths.get("plain1.bmp"));

        // BEGIN SOLUTION

        for(int i = 0; i < 10000;i++ )
        {
            cipherBMP[i] = otherBMP[i];
        }


        byte[] modifiedBMP = cipherBMP;



        // END SOLUTION

        Files.write(Paths.get("cipher3_modified.bmp"), modifiedBMP);
    }




    static void P4() throws Exception {
        //String plain4A_loc = "C:/Users/Pawan/Desktop/CyberSecurity/HW2_pdf/plain4A.txt";
        //String cipher4A_loc = "C:/Users/Pawan/Desktop/CyberSecurity/HW2_pdf/cipher4A.bin";
        //String cipher4B_loc = "C:/Users/Pawan/Desktop/CyberSecurity/HW2_pdf/cipher4B.bin";

        byte[] plainA = Files.readAllBytes(Paths.get("plain4A.txt"));
        byte[] cipherA = Files.readAllBytes(Paths.get("cipher4A.bin"));
        byte[] cipherB = Files.readAllBytes(Paths.get("cipher4B.bin"));

        // BEGIN SOLUTION

        byte[] key_nik = new byte[plainA.length];


        for(int i = 0; i < plainA.length; i++)
        {
           key_nik[i]= (byte)(plainA[i] ^ cipherA[i]);
        }

        byte[] store_plaintext = new byte[cipherB.length];

        for(int i = 0; i < cipherB.length; i++)
        {
            store_plaintext[i]= (byte)(key_nik[i] ^ cipherB[i]);
        }


        byte[] plainB = store_plaintext;
        // END SOLUTION

        Files.write(Paths.get("plain4B.txt"), plainB);
    }






    static void P5() throws Exception {

        //String cip5_loc = "C:/Users/Pawan/Desktop/CyberSecurity/HW2_pdf/cipher5.bmp";
        byte[] cipherBMP = Files.readAllBytes(Paths.get("cipher5.bmp"));

        // BEGIN SOLUTION
        byte[] plainBMP;
        //byte[] key = new byte[] {   0,   0,    0,   0, 0,   0,    0,   0, 0,   0,    0,   0, 0,   0,    0,   0 };
        // try {
       // plainBMP = cipherBMP;

        byte[] cst = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        byte[] key = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

        IvParameterSpec specs_liq = new IvParameterSpec(cst);
        //SecretKeySpec key_spec = new SecretKeySpec(key, "AES");
//
        //Cipher cipher_p2 = Cipher.getInstance("AES/CBC/ISO10126Padding ");
        //cipher_p2.init(Cipher.DECRYPT_MODE, key_spec, specs_liq);



        for(int i = 0; i <= 99;i++)
        {
            for(int j = 1; j <= 12;j++)
            {
                for(int k =1; k <= 31;k++)
                {
                    try{
                        key[0] = (byte) i;
                        key[1] = (byte) j;
                        key[2] = (byte) k;

                        SecretKeySpec key_spec = new SecretKeySpec(key, "AES");

                        Cipher cipher_p2 = Cipher.getInstance("AES/CBC/ISO10126Padding ");
                        cipher_p2.init(Cipher.DECRYPT_MODE, key_spec, specs_liq);
                        byte[] plainBMP5 = cipher_p2.doFinal(cipherBMP);


                        if( (Byte.compare(plainBMP5[0],(byte)(66))==0) && (Byte.compare(plainBMP5[1],(byte)(77))==0) && (Byte.compare(plainBMP5[2],(byte)(122))==0)){

                            Files.write(Paths.get("plain5.bmp"), plainBMP5);

                        }

                    }catch(BadPaddingException e){

                    }


                }

            }
        }




        // decryption might throw a BadPaddingException!
        // }
        // catch (BadPaddingException e) {
        // }
        // END SOLUTION


    }

    public static void main(String [] args) {
        try {
            P1();
            P2();
            P3();
            P4();
            P5();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}