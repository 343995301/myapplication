package com.example.dell.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.util.ConnectionTest;
import com.app.util.TisscUtil;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button test_btn;
    ConnectionTest ConnectionTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.text_tset);
        test_btn=(Button) findViewById(R.id.test_btn);
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread myThread = new MyThread();
                new Thread(myThread,"name").start();
            }
        });


    }
    class MyThread implements Runnable{
        @Override
        public void run() {
            TisscUtil tisscUtil =new TisscUtil();
            String str=tisscUtil.getGuid();
            ConnectionTest connectionTest = new ConnectionTest();
            String token=connectionTest.sendGet("http://192.168.3.116:8020/services/gettoken/"+str,"");
            System.out.println("token" + token);
            String json="{\"sid\":\""+str+"\",\"country_id\":\"CENTER\",\"user_name\":\"CENTER\",\"transactiontoken\":\""+token+"\",\"languageCode\":\"en-US\",\"businessName\":\"SSOCHANGPWD\",\"sync\":0,\"obj\":"
                    + "{\"user_alias\":\"10041\",\"password\":\"21121\",\"sso_client\":\"B2B_UA\",\"md5\":\"f165f2a2211f86ac065fae4a1b916ff5\",\"prop\":\"NAME`张三~USER_RANK`7~PASSPORT_NUMBER`123456~NATION`1~ PERIOD`201202\"}}";
            String str2 = null;
            try {
//			str2 = netpostt("http://192.168.3.116:8020/services/asynchronous",json);
                str2 = connectionTest.netpostt("http://192.168.3.116:8020/services/syncservice",json);
                System.out.println(Thread.currentThread().getName()+"----str="+str2+"---token="+token);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Message msg = new Message();
            msg.what=1;
            msg.obj=str2+token;
            myHandler.sendMessage(msg);
        }
    }

    Handler myHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    System.out.println("handleMessage thread id " + Thread.currentThread().getId());
                    System.out.println("msg.arg1:" + msg.obj.toString());
                    textView.setText(msg.obj.toString());
                    break;
            }
        }
    };
}
