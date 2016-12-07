package basic.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by liubo on 16/8/28.
 */
public class IpLocation {
    public static void main(String[] args) {
//        String location = getAddressByIP();
//        System.out.println(location);
        String s = "中国北京市&nbsp;联通</span></p>";
        System.out.println(s.substring(0,s.indexOf("<")));
    }

    public static String getAddressByIP()
    {
        try
        {
            String strIP = "222.129.214.44";
            URL url = new URL( "http://ip.qq.com/cgi-bin/searchip?searchip1=" + strIP);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            String line = null;
            StringBuffer result = new StringBuffer();
            while((line = reader.readLine()) != null)
            {
                result.append(line);
            }
            reader.close();
            strIP = result.substring(result.indexOf( "该IP所在地为：" ));
            strIP = strIP.substring(strIP.indexOf( "：") + 1);
            String province = strIP.substring(6, strIP.indexOf("省"));
            System.out.println(province);
            String city = strIP.substring(strIP.indexOf("<") + 1);
            return city;
        }
        catch( IOException e)
        {
            return "读取失败";
        }
    }
}
