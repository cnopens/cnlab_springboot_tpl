package com.ds.dss.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/***
 * 网络ip检测工具
 */
public class IpUtils {
    public static String createIp(final String currenIp, final String networkSwg) {
        String nextIp = "";
        if (currenIp == null || currenIp.equals("")) {
            nextIp = networkSwg + ".1";
            return nextIp;
        }
        final String[] currentArr = currenIp.split("\\.");
        final Integer current = Integer.valueOf(currentArr[currentArr.length - 1]);
        final Integer next = current + 1;
        if (next < 255) {
            nextIp = networkSwg + "." + next;
            return nextIp;
        }
        throw new RuntimeException("IP区间不足");
    }

    /***
     * 检测Ip连通性
     * @param ip
     * @return
     * @throws Exception
     */
    public static boolean isReachable(final String ip) throws Exception {
        boolean flag = false;
        final InetAddress inetAddress = InetAddress.getByName(ip);
        flag = inetAddress.isReachable(3000);
        return flag;
    }

    public static boolean isUse(final String ip) {
        final boolean flag = false;
        return flag;
    }

    public static InetAddress getInetDataInstance() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ia;
    }

    public static void main(String[] args) {
        String networkSwg = "192.168.0";
        String vmip = createIp("192.168.0.105", networkSwg);
//        String vmip = "";
        boolean loopFlag = true;

        while (loopFlag) {
            try {
                if (IpUtils.isReachable(vmip)) {
                    loopFlag = false;
                    System.out.println("connect 1>>>>>>>>>>>>>" + vmip);
                } else {
                    vmip = createIp(vmip, networkSwg);
                }


            } catch (Exception e) {
//                e.printStackTrace();
                break;
            }
        }
        System.out.println("获取的可用IP:>>>>>>>>>>>>>" + vmip);

    }
}
