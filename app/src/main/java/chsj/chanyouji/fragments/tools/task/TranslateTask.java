package chsj.chanyouji.fragments.tools.task;

import android.os.AsyncTask;

import chsj.chanyouji.Constants;
import chsj.chanyouji.fragments.tools.utils.TranslateUtil;

/**
 * ProjectName : chsj.chanyouji.fragments.tools.task
 * Created by : zhaoQiang
 * Email : zhaoq_hero163.com
 * On 2015/11/12 // 15:37
 */

/**
 * 回调返回来的  数据
 */
public class TranslateTask extends AsyncTask<String,Void,String>{

    private MyCallback callback;

    public TranslateTask(MyCallback callback){
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {

        String url = Constants.TO_BAIDU_TRANSLATE_URL;

        String result = TranslateUtil.getRequest(url, params[0]);

        return result;
    }



    @Override
    protected void onPostExecute(String s) {
        callback.sendTranslateResult(s);
        super.onPostExecute(s);
    }


   public interface MyCallback{
        void sendTranslateResult(String result);
    }

}
