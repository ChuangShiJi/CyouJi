package chsj.chanyouji.fragments.tools.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import chsj.chanyouji.Constants;
import chsj.chanyouji.MainActivity;
import chsj.chanyouji.R;
import chsj.chanyouji.fragments.tools.task.TranslateTask;
import chsj.chanyouji.fragments.tools.utils.TranslateUtil;

/**
 * 翻译界面
 */
public class TranslateActivity extends AppCompatActivity implements View.OnClickListener,TranslateTask.MyCallback{

    @ViewInject(R.id.translate_back)
    private RelativeLayout back;

    @ViewInject(R.id.translate_voice)
    private ImageView voice;

    @ViewInject(R.id.translate_from)
    private Spinner translateFrom;

    @ViewInject(R.id.translate_to)
    private Spinner translateTo;

    @ViewInject(R.id.translate_buttom_btn)
    private Button btnToQuery;

    @ViewInject(R.id.translate_query_text)//需要查询的文本
    private EditText queryText;

    @ViewInject(R.id.translate_query_result)
    private TextView queryResult;

    @ViewInject(R.id.translate_query)
    private TextView query;

    @ViewInject(R.id.translate_message_info)
    private TextView traMessageInfo;


    @ViewInject(R.id.translae_qContent)
    private TextView qContent;

    private ArrayAdapter adapter1,adapter2;
    private String str ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        x.view().inject(this);

        initSpinner();//初始化  数据

        voice.setOnClickListener(this);
        back.setOnClickListener(this);
        btnToQuery.setOnClickListener(this);

        //初始化：
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=564499f1");

    }

    private void initSpinner() {
        //初始化 spinner数据：
        //初始化spinner:
        adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.languagefrom,
                android.R.layout.simple_list_item_1
        );
        adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.languageTo,
                android.R.layout.simple_list_item_1
        );
        translateFrom.setAdapter(adapter1);
        translateTo.setAdapter(adapter2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.translate_back:
                finish();
                break;

            case R.id.translate_voice:

                qContent.setText("");
                traMessageInfo.setVisibility(View.GONE);

                //创建语音查询事件：
                RecognizerDialog dialog = new RecognizerDialog(this,null);
                dialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
                dialog.setParameter(SpeechConstant.ACCENT, "mandarin");

                dialog.setListener(new RecognizerDialogListener() {
                    @Override
                    public void onResult(RecognizerResult recognizerResult, boolean b) {
                        if (recognizerResult != null) {
                            str = TranslateUtil.parseIatResult(
                                    recognizerResult.getResultString());

                            qContent.setText(str);
                            qContent.setVisibility(View.VISIBLE);

                            String fromCode = Constants.LANGUAGECODE[translateFrom.getSelectedItemPosition()];
                            String toCode = Constants.LANGUAGECODE[translateTo.getSelectedItemPosition()+1];
                            TranslateTask task =
                                    new TranslateTask(TranslateActivity.this);
                            if (fromCode.equals("en")){
                                try {
                                    task.execute("query="+ URLEncoder
                                            .encode(str, "utf-8") +
                                            "&from=en" + "&to=" + toCode);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                try {
                                    task.execute("query="+ URLEncoder
                                            .encode(str.toString(), "utf-8") +
                                            "&from=zh" + "&to=" + toCode);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(SpeechError speechError) {
                    }
                });

                dialog.show();
                Toast.makeText(this, "请开始说话", Toast.LENGTH_SHORT).show();

                break;


            case R.id.translate_buttom_btn:

                qContent.setText("");
                //查询数据：。。。。
                String txt = queryText.getText().toString().trim();
                qContent.setText(txt);
                qContent.setVisibility(View.VISIBLE);
                queryResult.setText("");
                traMessageInfo.setVisibility(View.GONE);

                if (txt != null && !txt.equals("")) {
                    //点击下面的按钮  开始翻译：
                    //点击 上面  的  图标按钮  开始查询：
                    //获取  当前 数据对应的  语言编码：
                    String fromCode = Constants.LANGUAGECODE[translateFrom.getSelectedItemPosition()];
                    String toCode = Constants.LANGUAGECODE[translateTo.getSelectedItemPosition()+1];
                    //拼接请求参数：
                    TranslateTask task =
                            new TranslateTask(this);
                    try {
                        task.execute("query="+ URLEncoder
                                .encode(txt.toString(), "utf-8")+
                                "&from=" + fromCode + "&to=" + toCode);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    qContent.setText(txt);
                }else{
                    Toast.makeText(this,"请输入查询文本",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void sendTranslateResult(String result) {

        //打印输出数据：
        //解析数据：
        if (result!=null){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject retData = jsonObject.getJSONObject("retData");
                JSONArray array = retData.getJSONArray("trans_result");
                if (array != null) {
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonO = array.getJSONObject(i);
                        String dst = jsonO.getString("dst");
                        //设置回传数据显示到  屏幕上：
                        queryResult.setText(dst);
                        queryResult.setVisibility(View.VISIBLE);
                        traMessageInfo.setVisibility(View.VISIBLE);
                    }
                }
            } catch (JSONException e) {

            }
        }
    }

}
