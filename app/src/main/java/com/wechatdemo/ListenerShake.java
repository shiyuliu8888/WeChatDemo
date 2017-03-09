package com.wechatdemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by Irain on 2017/3/1.
 */

public class ListenerShake implements SensorEventListener{
    // 速度阈值，当摇晃速度达到这值后产生作用
    private static final int SPEED_SHRESHOLD = 100;
    // 两次检测的时间间隔
    private static final int UPTATE_INTERVAL_TIME = 3000;

    private Context mContext;
    private SensorManager mSensorManager;
    private Sensor mGravitySensor;
    private OnShakeListener mOnShakeListener;
    private long lastUpdateTime;
    private float lastX;
    private float lastY;
    private float lastZ;

    //构造
    public ListenerShake(Context context){
        mContext = context;
        startSensor();
    }

    //启动 注册
    public void startSensor() {
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if(mSensorManager != null)
            mGravitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(mGravitySensor != null)
            mSensorManager.registerListener(this,mGravitySensor,SensorManager.SENSOR_DELAY_GAME);
    }

    //解开注册
    public void stopSensor(){
        mSensorManager.unregisterListener(this);
    }

    //设置重力感应监听器
    public void setOnShakeListener(OnShakeListener listener){
        mOnShakeListener = listener;
    }

    //摇晃取得监听的接口
    public interface OnShakeListener{
        public void onShake();
    }

    //重力感应发生变化的回调方法
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //currentTimeMillis的返回值单位是millisecond
        long currUpdateTime = System.currentTimeMillis();
        long timeInterval = currUpdateTime - lastUpdateTime;
        if(timeInterval < UPTATE_INTERVAL_TIME)
            return;
        lastUpdateTime = currUpdateTime;

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        float deltaX = x - lastX;
        float deltaY = y - lastY;
        float deltaZ = z - lastZ;

        lastX = x;
        lastY = y;
        lastZ = z;

        //速度值等于坐标轴中的移动距离/时间间隔
        double speed = Math.sqrt(deltaX*deltaX+deltaY*deltaY+deltaZ*deltaZ)/timeInterval*10000000;

        //速度大于临界值
        if(speed >= SPEED_SHRESHOLD)
            mOnShakeListener.onShake();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
