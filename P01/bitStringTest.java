import java.util.*;
import java.io.*;
import java.util.Scanner;

public class bitStringTest
{

    
	public static void main(String[] args) 
	{
        System.out.println("Please input a number to convert to binary");
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int orig = num;

        String bitString = "";
            while (num != 0)
            {
                if ((num % 2) == 0)
                {
                    bitString = "0" + bitString;
                }
                else
                {
                    bitString = "1" + bitString;
                    num = num-1;
                }
                num = num/2;
                //System.out.println(num);
                //System.out.println(bitString);
            }
            System.out.println("The bit string of " + orig + " is " + bitString);
            //return bitString;
    }
}