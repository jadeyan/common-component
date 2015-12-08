package com.creditease.common;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import com.creditease.common.R;

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

    private static final int SMS_RECEIVE_CODE = 1024;//获取权限吗


    private EditText mSMSShow;

    private StringBuffer SMSAddress = new StringBuffer();//地址
    private StringBuffer SMSContent = new StringBuffer();//内容
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);
        mSMSShow = (EditText) findViewById(R.id.et_sms);
//        if(Build.VERSION.SDK_INT >= 23){
//            smsReceiverPermissionAsk();
//        }else{
//            registerSMSReceive();
//        }

    }
//
//    //动态权限设置for Android 6.0
//    private void smsReceiverPermissionAsk(){
//        int hasReceiveSMSPermission = checkSelfPermission(Manifest.permission.RECEIVE_SMS);
//        if(hasReceiveSMSPermission != PackageManager.PERMISSION_GRANTED){
//            if(!shouldShowRequestPermissionRationale(Manifest.permission.RECEIVE_SMS)){//用户之前已经选择了下次不再提醒
//                smsPermissionAskDialog("获取自动读取验证码权限", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_CODE);
//                    }
//                });
//            }
//            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, SMS_RECEIVE_CODE);
//            return;
//        }
//        registerSMSReceive();
//    }
//
//    //用户权限选择时弹框提示
//    private void smsPermissionAskDialog(String message,DialogInterface.OnClickListener okClick){
//        new AlertDialog.Builder(SMSAutoGetActivity.this)
//                .setMessage(message)
//                .setPositiveButton("OK",okClick)
//                .setNegativeButton("cancel",null)
//                .create()
//                .show();
//
//    }
//
//    //动态注册广播接收器，动态注册的优先级要比静态注册的搞
//    private void registerSMSReceive(){
//        IntentFilter mfilter = new IntentFilter();
//        mfilter.addAction("android.provider.Telephony.SMS_RECEIVED");
//        mfilter.setPriority(Integer.MAX_VALUE);//虽然API文档说最多1000，但是大了也没问题
//        registerReceiver(new SMSReceive(), mfilter);
//    }
//
//    //权限设置后回调
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode){
//            case SMS_RECEIVE_CODE:
//                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    registerSMSReceive();
//                }else{
//                    Toast.makeText(this,"您已经拒绝了自动获取短信验证码",Toast.LENGTH_LONG).show();
//                }
//                break;
//            default:
//                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//                break;
//        }
//    }
//
//    class SMSReceive extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Bundle bundle = intent.getExtras();
//            if (bundle != null) {
//                Object[] pdusObjects = (Object[]) bundle.get("pdus");
//                SmsMessage[] messages = new SmsMessage[pdusObjects.length];
//                for (int i = 0; i < pdusObjects.length; i++) {
//                    messages[i] = SmsMessage
//                            .createFromPdu((byte[]) pdusObjects[i]);
//                }
//                for (SmsMessage message : messages) {
//                    SMSAddress.append(message
//                            .getDisplayOriginatingAddress());
//                    SMSContent.append(message.getDisplayMessageBody());
//                    mSMSShow.setText(SMSContent.subSequence(YZM_START,YZM_START+YZM_LENGTH));
//                    System.out.println("来信号码：" + SMSAddress + "\n短信内容："
//                            + SMSContent);
//                }
//            }
//        }
//    }
}
