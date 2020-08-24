package com.ds.dss.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class LicenceGenerate {
    public static void main(final String[] args) {
        final String a1 = String.valueOf(4);
        final String a2 = String.valueOf(1);
        final String a3 = String.valueOf(2);
        final String a4 = String.valueOf(9);
        final String b1 = "1";
        final String b2 = "2";
        final String c1 = "2";
        final String c2 = "9";
        final StringBuffer buffer1 = getlength(3);
        final StringBuffer buffer2 = getlength(4);
        final StringBuffer buffer3 = getlength(2);
        final StringBuffer buffer4 = getlength(5);
        final String mima = buffer1 + a1 + buffer2 + a2 + buffer3 + a3 + buffer3 + a4 + buffer2 + b1 + buffer1 + b2 + buffer4 + c1 + buffer1 + c2 + buffer1;
        System.out.println(mima);
    }

    private static StringBuffer getlength(final int length) {
        final char[] chr = {'0', '1', '2', '3', '4', '5', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
        final Random random = new Random();
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            buffer.append(chr[random.nextInt(15)]);
        }
        return buffer;
    }

    private static void Decode(final String mima) {
        if (mima.length() == 37) {
            final char a1 = mima.charAt(3);
            final char a2 = mima.charAt(8);
            final char a3 = mima.charAt(11);
            final char a4 = mima.charAt(14);
            final int valueOf = Integer.parseInt(String.valueOf(a1));
            final String v1 = String.valueOf(valueOf - 2);
            final int valueOf2 = Integer.parseInt(String.valueOf(a2));
            final String v2 = String.valueOf(valueOf2 - 1);
            final String jiemi = "" + v1 + v2 + mima.charAt(11) + mima.charAt(14) + "/" + mima.charAt(19) + mima.charAt(23) + "/" + mima.charAt(29) + mima.charAt(33);
            System.out.println(jiemi);
            boolean convertSuccess = true;
            final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            try {
                format.setLenient(false);
                format.parse(jiemi);
            } catch (ParseException e) {
                convertSuccess = false;
            }
            if (convertSuccess) {
                System.out.println("密码正确");
            } else {
                System.out.println("密码不正确，请重新输入");
            }
        } else {
            System.out.println("密码不正确，请重新输入");
        }
    }
}
