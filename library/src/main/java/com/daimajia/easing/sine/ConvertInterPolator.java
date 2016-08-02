package com.daimajia.easing.sine;

import android.util.Log;
import android.view.animation.Interpolator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/1.
 */
public class ConvertInterPolator implements Interpolator {
    Interpolator realInterpolator;
    float start;
    float end;
    float duration;
    SpeedListener listener;

    protected float mDuration;

    private ArrayList<SpeedListener> mListeners = new ArrayList<SpeedListener>();

    public interface SpeedListener {
        /**
         * @param time     当前时间
         * @param value    最后算好的值
         * @param start    开始值
         * @param end      最终值
         * @param duration 持续时间
         **/
        public void on(float time, float value, float start, float end, float duration);
    }

    public ConvertInterPolator(float start, float end, float duration, Interpolator realInterpolator, SpeedListener listener) {
        this.realInterpolator = realInterpolator;
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.listener = listener;
    }

    @Override
    public float getInterpolation(float input) {
        float result = realInterpolator.getInterpolation(input);
        result = start+result*(end-start);
        Log.e("inter","result"+result+"\tinput"+input);
        float time = duration * input;

        listener.on(time, result, start, end, duration);
        return result;
    }
}
