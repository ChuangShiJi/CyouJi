package chsj.chanyouji.fragments.tools.utils;

/**
 * ProjectName : chsj.chanyouji.fragments.tools.utils
 * Created by : zhaoQiang
 * Email : zhaoq_hero163.com
 * On 2015/11/12 // 15:24
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 该类用于 访问需要翻译的数据
 */
public class TranslateUtil {

    //访问网络：
    public static String getRequest(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;  //设置请求参数

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");

            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "f48d38e38961525676aa20c486fb6248");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     *  解析 讯飞语音  识别返回解析结果：
     */
    public static String parseIatResult(String json) {
        StringBuffer ret = new StringBuffer();
        try {

//            json文本解析类JSONTokener
//            按照RFC4627规范将json文本解析为相应的对象。
            JSONTokener tokener = new JSONTokener(json);
            JSONObject joResult = new JSONObject(tokener);

            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0; i < words.length()-1; i++) {
                // 转写结果词，默认使用第一个结果
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                JSONObject obj = items.getJSONObject(0);
                ret.append(obj.getString("w"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret.toString();
    }


}