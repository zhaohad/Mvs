package demo.hw.mvsfistclient;

import android.app.Application;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.miui.voiceassist.mvs.client.MvsClientManager;
import com.miui.voiceassist.mvs.common.MvsResult;
import com.miui.voiceassist.mvs.common.MvsSpeechResult;
import com.miui.voiceassist.mvs.common.card.MvsIcon;
import com.miui.voiceassist.mvs.common.card.MvsListCard;
import com.miui.voiceassist.mvs.common.card.MvsListItem;
import com.miui.voiceassist.mvs.common.card.MvsTextCard;

public class ClientApp extends Application {
    private static final String TAG = "ClientApp hanwei";

    private MvsClientManager mMvsClientManager;

    private UiHandler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new UiHandler();

        Log.e(TAG, "App onCreate");
        mMvsClientManager = MvsClientManager.getInstance(this);
        mMvsClientManager.setMvsCallback(new MvsClientManager.MvsCallback() {
            @Override
            public void onInited() {
                Log.e(TAG, "onInited");
                MvsResult result = new MvsResult(MvsResult.RESULT_WAITING, "乱发", "乱发");
                mMvsClientManager.sendResult(result);
            }

            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError");
            }

            @Override
            public MvsResult onSpeechResult(MvsSpeechResult mvsSpeechResult) {
                Log.e(TAG, "onSpeechResult " + mvsSpeechResult.toJson());
                // MvsResult result = new MvsResult(MvsResult.RESULT_WAITING, "我是小米语音服务第三方客户端", "我是小米语音服务第三方客户端");
                MvsResult result = new MvsResult(MvsResult.RESULT_WAITING, "测试等待", "测试等待");
                BitmapDrawable d = (BitmapDrawable) getApplicationContext().getResources().getDrawable(R.mipmap.timg, null);
                MvsIcon mvsIcon = new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", d.getBitmap());
                // "https://www.baidu.com/img/bd_logo1.png"
                MvsTextCard txtCard = new MvsTextCard("你好啊！", "第三方客户端", mvsIcon);
                result.addCard(txtCard);

                /*MvsListCard listCard = new MvsListCard("列表卡片", mvsIcon);
                result.addCard(listCard);
                listCard.addItem(new MvsListItem("Title 1", "subTitle 1", null, new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", d.getBitmap())));
                listCard.addItem(new MvsListItem("Title 2", "subTitle 2", null, new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", d.getBitmap())));
                listCard.addItem(new MvsListItem("Title 3", "subTitle 3", null, new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", d.getBitmap())));
                listCard.addItem(new MvsListItem("Title 4", "subTitle 4", null, new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", d.getBitmap())));
                listCard.addItem(new MvsListItem("Title 5", "subTitle 5", null, new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", d.getBitmap())));*/

                mHandler.sendEmptyMessageDelayed(MSG_SEND_WAITED_RESULT, 3000);
                return result;
            }
        });
        mMvsClientManager.register();
    }

    private static final int MSG_SEND_WAITED_RESULT = 1;
    public class UiHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SEND_WAITED_RESULT:
                    MvsIcon mvsIcon = new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", null);
                    MvsResult result = new MvsResult(MvsResult.RESULT_SUC, "测试等待", "测试等待");
                    MvsListCard listCard = new MvsListCard("列表卡片", mvsIcon);
                    result.addCard(listCard);
                    listCard.addItem(new MvsListItem("Title 1", "subTitle 1", null, new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", null)));
                    listCard.addItem(new MvsListItem("Title 2", "subTitle 2", null, new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", null)));
                    listCard.addItem(new MvsListItem("Title 3", "subTitle 3", null, new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", null)));
                    listCard.addItem(new MvsListItem("Title 4", "subTitle 4", null, new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", null)));
                    listCard.addItem(new MvsListItem("Title 5", "subTitle 5", null, new MvsIcon(null, "https://www.baidu.com/img/bd_logo1.png", null)));
                    mMvsClientManager.sendResult(result);
                    break;
            }
        }
    }

}
