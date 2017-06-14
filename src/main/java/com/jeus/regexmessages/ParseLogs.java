package com.jeus.regexmessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parser for log by piping.
 * <br>
 * One type log of driver-charge-hamrah-1.log
 * <ol>
 * <li>Type 1 <br>
 * 2017-04-24 00:01:02,480 INFO
 * [charge.hamrah.first.HamrahChargeQueueConsumer:192] - !msg:
 * {id='1492066465763000043415454', from='3053', to='989120314836',
 * content='1492066465763000043415454', routeId=2187,
 * serviceId='1482611440444000000386109', subscriberId=527064,
 * creationDate=2017-04-23 07:02:50.0, direction=OUTGOING, sendTry=15,
 * sendReference='0', sendResponse='UNKNOWNERROR', expireDate=2017-04-23
 * 10:02:50.0, type=AUTO_CHARGE, characteristicId=7028, runId=1128,
 * delivery='null', deliveryState=null}<br>
 * <li> Call <br>
 * cat driver-charge-hamrah-1.log |grep AUTO_CHARGE |grep UNKNOWNERROR |grep
 * charge.hamrah.first.HamrahChargeQueueConsumer | ~/jdk1.8.0_131/jre/bin/java
 * -jar ~/ParseLogs.jar > ~/UNKNOWNERROR.csv `
 * </ol>
 */
public class ParseLogs {

    static Pattern p;

    static {
        p = Pattern.compile("^(?<date>\\d{1,4}.\\d{1,2}.\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}).*id='(?<id>.*)', from='\\d*', to='(?<to>\\d*)', content='(?<content>\\d*)', routeId=(?<rotid>\\d*),"
                + "\\sserviceId='(?<serId>\\d*)', subscriberId=(?<subId>\\d*),\\screationDate=.*direction=(?<dire>\\w*),\\ssendTry=(?<try>\\d*),\\ssendReference='(?<refr>.*)',\\ssendResponse='(?<response>\\w*)'"
                + ",.*type=(?<type>\\w*),\\scharacteristicId=(?<char>\\d*),\\srunId=(?<runId>\\d*),\\s.*$");
    }

    /**
     * Main method. no input argument.
     *
     * @param args
     */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String x = null;
            try {
                x = f.readLine();
                if (x == null) {
                    break;
                }
                Matcher m = p.matcher(x);
                while (m.find()) {
                    sb.append(m.group("date"));
                    sb.append(";");
                    sb.append(m.group("id"));
                    sb.append(";");
                    sb.append(m.group("to"));
                    sb.append(";");
                    sb.append(m.group("content"));
                    sb.append(";");
                    sb.append(m.group("rotid"));
                    sb.append(";");
                    sb.append(m.group("type"));
                    sb.append(";");
                    sb.append(m.group("subId"));
                    sb.append(";");
                    sb.append(m.group("dire"));
                    sb.append(";");
                    sb.append(m.group("try"));
                    sb.append(";");
                    sb.append(m.group("refr"));
                    sb.append(";");
                    sb.append(m.group("response"));
                    sb.append(";");
                    sb.append(m.group("type"));
                    sb.append(";");
                    sb.append(m.group("char"));
                    sb.append(";");
                    sb.append(m.group("runId"));
                    sb.append("\n");
                    System.out.print(sb.toString());
                    sb = new StringBuilder();
                }
            } catch (IOException e) {
                System.out.println(x);
                e.printStackTrace();
            }
        }
    }
}
