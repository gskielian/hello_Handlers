package com.example.owner.hellohandlers;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActivity extends Activity {

    private Button   doStuffButton;
    private Button   threadStuffButton;
    private TextView textView;
    private TextView text2View;
    private ImageView imgView;
    private int count_on_the_screen= 0;

    private final static int ACTION_1 = 0;
    private final static int ACTION_2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        imgView = (ImageView) findViewById(R.id.imageView);
        doStuffButton = (Button) findViewById(R.id.do_stuff_button);
        doStuffButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                final int what = msg.what;
                switch(what) {
                    case ACTION_1:
                        doAction1();
                        break;
                    case ACTION_2:
                        doAction2();
                        break;
                }
            }

            private void doAction1() {
                imgView.setVisibility(View.VISIBLE);
            }
            private void doAction2() {
            }
        };
        threadStuffButton = (Button) findViewById(R.id.thread_button);
        threadStuffButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Thread backgroundThread = new Thread(new Runnable() {

                            public void run() {
                                try {
                                        handler.sendEmptyMessage(ACTION_1);
                                        Thread.sleep(50);
                                        handler.sendEmptyMessage(ACTION_2);
                                        Thread.sleep(50);
                                } catch ( Throwable t) {
                                    // just end the background thread
                                }
                            }



                        });
                        //Start Thread
                        backgroundThread.start();
                    }
                });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


