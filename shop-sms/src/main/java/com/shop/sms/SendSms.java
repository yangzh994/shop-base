package com.shop.sms;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Component
@PropertySource("classpath:sms-conf.properties")
public class SendSms {

    @Value("${sms.username}")
    private String username;
    @Value("${sms.password}")
    private String password;
    @Value("${sms.host}")
    private String host;

    private String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    private String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    private String urlEncode(String str, String charset) {
        try {
            return java.net.URLEncoder.encode(str, charset);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 发送注册短信(默认编码utf-8)
     * @param msg 消息内容
     * @param phone 发送的手机号
     * @return
     */
    public String sendRegisterChannel(String msg,String phone){
        return sendRegisterChannel(msg,phone,"utf-8");
    }

    /**
     * 发送注册短信
     * @param msg 消息内容
     * @param phone 发送的手机号
     * @param charset 编码格式
     * @return
     */
    public String sendRegisterChannel(String msg,String phone,String charset){
        //模拟短信
        String content = "【杨家板鸭】您的验证码是"+ msg;
        //发送短信的类型
        String method = "sendSMS";
        //发送短信
        String sr = sendMsg(method, content, phone, charset);
        //返回结果
        return sr;
    }

    /**
     * 发送消息
     * @param method 发现消息的类型
     * @param msg 消息内容
     * @param phone 发送的手机号
     * @param charset 编码格式
     * @return
     */
    private String sendMsg(String method,String msg,String phone,String charset){
        msg = urlEncode(msg, charset);
        String isLongSms="0"; //0-普通短信 1-加长短信
        String extenno =""; //为通道扩展子号码，可以为空
        String parm="method="+method+"&username="+username+"&password="+password+"&mobile="+phone+"&content="+msg+"&isLognSms="+isLongSms+"&extenno="+extenno;
        String host="http://sms.smsyun.cc:9012/servlet/UserServiceAPIUTF8"; //提交地址http://sms.smsyun.cc:9012/servlet/UserServiceAPI
        //发送 POST 请求
        String sr = sendPost(host, parm);
        return sr;
    }



    public static void main(String[] args) throws Exception {
//        SendSms post=new SendSms();
//        String method="sendSMS"; //表示发送短信
//        String username="username"; //用h户名
//        String password="password"; //密码
//        String mobile="手机号"; //合法的手机号码，号码间用英文逗号分隔
//        String content="【杨家板鸭】您的验证码是9999";//发送内容
//        content = post.urlEncode(content, "utf-8");//	采用utf-8 进行URLENCODE
//        String isLongSms="0"; //0-普通短信 1-加长短信
//        String extenno =""; //为通道扩展子号码，可以为空
//        String parm="method="+method+"&username="+username+"&password="+password+"&mobile="+mobile+"&content="+content+"&isLognSms="+isLongSms+"&extenno="+extenno;
//        String host="http://sms.smsyun.cc:9012/servlet/UserServiceAPIUTF8"; //提交地址http://sms.smsyun.cc:9012/servlet/UserServiceAPI
//        //发送 POST 请求
//        String sr=post.sendPost(host, parm);
//        System.out.println(sr);
        URL url = new URL("http://www.k25m.com/cn/star_list.php?prefix=B");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
      //  connection.setConnectTimeout(5*1000);
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        connection.connect();
      //  Thread.sleep(2000l);
        InputStream inputStream = connection.getInputStream();
        byte[] data = new byte[1024 ];
        StringBuffer sb = new StringBuffer();
        int length = 0;
        while ((length = inputStream.read(data)) != -1) {
            String s = new String(data, Charset.forName("utf-8"));
            sb.append(s);
        }
        String message = sb.toString();
        inputStream.close();
        connection.disconnect();

        System.out.println(message);
    }

}
