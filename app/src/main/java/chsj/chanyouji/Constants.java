package chsj.chanyouji;

/**
 * ProjectName : chsj.chanyouji
 * Created by : zhaoQiang
 * Email : zhaoq_hero163.com
 * On 2015/11/9 // 22:28
 */
public class Constants {

    //
    public static final  String SP_NAME = "app";

    //欢迎页  显示的版本  根据版本 判断是否显示：
    public static final  String SP_KEY_IS_AGREE = "agree";

    //版本号 用于在splash中和 sp进行比较  从而判断是否显示欢迎页  这样更精确 兼容性好
    public static final String SP_KEY_WELCOME_SHOW_VER = "ver";

    //设置用于 回传数据的  返回码：
    public static final int ACTIVITY_REQUESTCODE = 1; //请求码

    /**
     * 选择目的第中 url  获取所有可选择的目的地：
     */
        public static final String GET_SELECT_CITY_URL =
            "http://chanyouji.com/api/wiki/destinations.json";

    /**
     * 发送广播的   频道
     */
    public static final String SELECT_CITY_BROADCAST_ACTION
            = "tools.toolfragment.Receiver";

    /**
     * 发送广播的  参数
     */
    public static final String DATA_EXTRA_INTENT = "data";

    /**
     * 获取所选城市 的状况
     */
    public static final String  CITY_CONDITION_URL=
            "https://chanyouji.com/api/destinations/%s.json";


    /**
     * 当前语言数据对应的  编码：   不要修改顺序  和 数据
     */
    public static final String[] LANGUAGECODE ={
            "zh",//中文
            "en",//英语
            "ru",//俄语
            "zh",//粤语  不适用yue
            "jp",//日语
            "kor",//韩语
    };
    /**
     * 百度翻译中  需要使用到的   api接口
     */
    public static final String TO_BAIDU_TRANSLATE_URL =
            "http://apis.baidu.com/apistore/tranlateservice/translate";

    /**
     * 设置  访问  城市信息的URL
     */
    public static final String GET_CITY_INFO_TOOLFRAGMENT_URL =
            "http://chanyouji.com/api/wiki/destinations/infos/%s.json";


}
