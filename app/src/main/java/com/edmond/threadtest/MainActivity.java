package com.edmond.threadtest;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;
    private Button btnStartThread;
    private Button btnStartThread2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartThread = (Button)findViewById(R.id.btnStartThread);
        btnStartThread2 = (Button)findViewById(R.id.btnThread2);
        btnStartThread.setOnClickListener(btnStartThreadListener);
        btnStartThread2.setOnClickListener(btnStartThreadListener);
    }

    private Button.OnClickListener btnStartThreadListener  = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnStartThread: {
                    Toast toast = Toast.makeText(MainActivity.this, "thread1 button pressed", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();

                    //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
                    mThread = new HandlerThread("name");
                    //讓Worker待命，等待其工作 (開啟Thread)
                    mThread.start();
                    //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
                    mThreadHandler = new Handler(mThread.getLooper());
                    //請經紀人指派工作名稱 r，給工人做
                    mThreadHandler.post(r1);
                    break;

                }
                case R.id.btnThread2: {
                    Toast toast = Toast.makeText(MainActivity.this, "thread2 button pressed", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();

//                    Toast.makeText(MainActivity.this, "thread2 button pressed", Toast.LENGTH_LONG).show();

                    //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
                    mThread = new HandlerThread("name");
                    //讓Worker待命，等待其工作 (開啟Thread)
                    mThread.start();
                    //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
                    mThreadHandler = new Handler(mThread.getLooper());
                    //請經紀人指派工作名稱 r，給工人做
                    mThreadHandler.post(r1);
                    break;
                }
            }
        }
    };

    private Runnable r1=new Runnable () {

        public void run(){

        // TODO Auto-generated method stub

            //.............................

            //做了很多事
            Toast.makeText(MainActivity.this,
                    "R1 running", Toast.LENGTH_LONG).show();


            //請經紀人指派工作名稱 r，給工人做

            mUI_Handler.post(r2);






        }

    };

    //工作名稱 r2 的工作內容

    private Runnable r2=new Runnable () {

        public void run() {

            // TODO Auto-generated method stub

            Toast.makeText(MainActivity.this,
                    "R2 running", Toast.LENGTH_LONG).show();

            //.............................

            //顯示畫面的動作



        }

    };

    private void goToSleep () {


    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

        Toast.makeText(MainActivity.this,
                "Destroy", Toast.LENGTH_LONG).show();

        //移除工人上的工作

        if (mThreadHandler != null) {

            mThreadHandler.removeCallbacks(r1);

        }

        //解聘工人 (關閉Thread)

        if (mThread != null) {

            mThread.quit();

        }

    }

}
