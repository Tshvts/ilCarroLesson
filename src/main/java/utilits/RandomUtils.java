package utilits;

import java.util.Random;

public class RandomUtils
{
        static Random random = new Random();

        public static String generateString(int length)
        {
            String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
            char[] randomStr = new char[length];
            int index = 0;
            for(int i = 0; i<length; i++)
            {
                index = random.nextInt(characters.length());
                randomStr[i] = characters.charAt(index);
            }
            return new String(randomStr);
        }

        public static String generateEmail(int length)
        {
            String[] domains = {"gmail.com", "mail.com", "yahoo.com", "yandex.ru"};
            String domain = domains[random.nextInt(domains.length)];
            return (generateString(length) + "@" + domain);
        }

        public static String generatePhone(int length)
        {
            String characters = "0123456789";
            char[] randomStr = new char[length];
            int index = 0;
            for(int i = 0; i<length; i++)
            {
                index = random.nextInt(characters.length());
                randomStr[i] = characters.charAt(index);
            }
            return new String(randomStr);
        }

    public static String generateAddress(int length, int length1)
    {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        char[] randomStr = new char[length];
        int index = 0;
        for(int i = 0; i<length; i++)
        {
            index = random.nextInt(characters.length());
            randomStr[i] = characters.charAt(index);
        }
        return (new String(randomStr) + ", " + generatePhone(length1));
    }

    public static String generateCarRegistrationNumber(int length, int length1)
    {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        char[] randomStr = new char[length];
        int index = 0;
        for(int i = 0; i<length; i++)
        {
            index = random.nextInt(characters.length());
            randomStr[i] = characters.charAt(index);
        }
        return (new String(randomStr) + "-" + generatePhone(length1));
    }

    public static String generateYear()
    {
        int i = new Random().nextInt(2010);
        String str = String.valueOf(i);
        return str;
    }


    public static String generateSeats()
    {
        int i = new Random().nextInt(2, 20);
        String str = String.valueOf(i);
        return str;
    }

    public static String generatePrice()
    {
        int i = new Random().nextInt(1000);
        String str = String.valueOf(i);
        return str;
    }



}
