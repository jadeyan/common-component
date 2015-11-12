package com.creditease.common;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 类描述：自动获取短信验证码
 * 类名称：SMSAutoGetActivity
 *
 * @version: 1.0
 * @author: George
 * @time: 2015/11/11 19:47
 */
public class SMSAutoGetActivity extends Activity {

    private static final int YZM_START = 5;//短信验证码开始的地方
    private static final int YZM_LENGTH = 6;//验证码的长度


    private EditText mSMSShow;

    private StringBuffer SMSAddress = new StringBuffer();//地址
    private StringBuffer SMSContent = new StringBuffer();//内容
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);
        mSMSShow = (EditText) findViewById(R.id.et_sms);
        //动态注册广播接收器，动态注册的优先级要比静态注册的搞
        IntentFilter mfilter = new IntentFilter();
        mfilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        mfilter.setPriority(Integer.MAX_VALUE);//虽然API文档说最多1000，但是大了也没问题
        registerReceiver(new SMSReceive(), mfilter);
    }

    class SMSReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdusObjects = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdusObjects.length];
                for (int i = 0; i < pdusObjects.length; i++) {
                    messages[i] = SmsMessage
                            .createFromPdu((byte[]) pdusObjects[i]);
                }
                for (SmsMessage message : messages) {
                    SMSAddress.append(message
                            .getDisplayOriginatingAddress());
                    SMSContent.append(message.getDisplayMessageBody());
                    mSMSShow.setText(SMSContent.subSequence(YZM_START,YZM_START+YZM_LENGTH));
                    System.out.println("来信号码：" + SMSAddress + "\n短信内容："
                            + SMSContent);
                }
            }
        }
    }
}
