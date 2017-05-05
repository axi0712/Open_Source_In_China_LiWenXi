package com.example.administrator.open_source_in_china_liwenxi.model.fragment.discover.discover;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.open_source_in_china_liwenxi.R;
import com.example.administrator.open_source_in_china_liwenxi.base.BaseActivity;
import com.example.administrator.open_source_in_china_liwenxi.model.INewModel;
import com.example.administrator.open_source_in_china_liwenxi.model.NewsModelImple;
import com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean.YaoYiYaoBean;
import com.example.administrator.open_source_in_china_liwenxi.model.http.MyCallBack;
import com.thoughtworks.xstream.XStream;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class Activity_YaoYao extends BaseActivity implements SensorEventListener{
    private TextView mYaoyiyaoText;
    private LinearLayout mYaoyiyaoLin;
    private ImageView mYaoyiyaoHead;
    private TextView mYaoyiyaoTitle;
    private SensorManager sensorManager;
        private Sensor defaultSensor;
    private boolean isShake = false;
    private Vibrator mVibrator;
    private SoundPool mSoundPool;
    private int load;
    private YaoYiYaoBean yaoYiYaoBean;
    private INewModel model;
    private void assignViews() {
        model = new NewsModelImple();
        mYaoyiyaoText = (TextView) findViewById(R.id.yaoyiyao_text);
        mYaoyiyaoLin = (LinearLayout) findViewById(R.id.yaoyiyao_lin);
        mYaoyiyaoHead = (ImageView) findViewById(R.id.yaoyiyao_head);
        mYaoyiyaoTitle = (TextView) findViewById(R.id.yaoyiyao_title);
        mYaoyiyaoLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (TextUtils.isEmpty(mYaoyiyaoTitle.getText())) {
                        Toast.makeText(Activity_YaoYao.this, "请先摇一摇", Toast.LENGTH_SHORT).show();
                        return;
                    }
//                    Intent intent = new Intent(Activity_YaoYao.this, WebActivity.class);
//                    intent.putExtra(Keys.WEB_URL, yaoYiYaoBean.getUrl());
//                    intent.putExtra(Keys.WEB_NAME, "摇一摇");
//                    startActivity(intent);



            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_yaoyiyao;
    }

    @Override
    protected void getID() {
    assignViews();
    }

    @Override
    protected int getZujian() {
        return 0;
    }

    @Override
    protected void getOnClick() {

    }

    @Override
    protected void initDatas() {
        mSoundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
        load = mSoundPool.load(this,R.raw.shake, 1);
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }
    @Override
    protected void onStart() {
        super.onStart();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            //获取加速度传感器
            defaultSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (defaultSensor != null) {
                sensorManager.registerListener((SensorEventListener) this, defaultSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }
    }

    @Override
    protected void onPause() {
        // 务必要在pause中注销 mSensorManager
        // 否则会造成界面退出后摇一摇依旧生效的bug
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();

        if (type == Sensor.TYPE_ACCELEROMETER) {
            //获取三个方向值
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            if ((Math.abs(x) > 17 || Math.abs(y) > 17 || Math
                    .abs(z) > 17) && !isShake) {
                isShake = true;
                // TODO: 2016/10/19 实现摇动逻辑, 摇动后进行震动
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        Log.d("摇动", "onSensorChanged: 摇动");
                        handler.sendEmptyMessage(1);
                        handler.sendEmptyMessage(2);
                    }
                };
                thread.start();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Activity_YaoYao.this.mVibrator.vibrate(300);


                    break;
                case 2:
                    model.yaoyiyao(new MyCallBack() {
                        @Override
                        public void onErro(String strErro) {

                        }

                        @Override
                        public void onSuccess(String strSuccess) {
                            XStream xStream = new XStream();
                            xStream.alias("oschina", YaoYiYaoBean.class);
                            yaoYiYaoBean = (YaoYiYaoBean) xStream.fromXML(strSuccess);
                            Glide.with(Activity_YaoYao.this).load(yaoYiYaoBean.getImage()).into(mYaoyiyaoHead);
                            mYaoyiyaoTitle.setText(yaoYiYaoBean.getDetail());
                            Activity_YaoYao.this.mSoundPool.play(Activity_YaoYao.this.load, 1, 1, 0, 0, 1);
                            isShake = false;
                        }

                    });

                    break;

            }
        }
    };



}
