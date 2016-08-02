/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.daimajia.easing;

import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import com.daimajia.easing.sine.ConvertInterPolator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.animation.ValueAnimator;

public class Glider {

    public static ValueAnimator glide(Skill skill, float duration, ValueAnimator animator){
        return Glider.glide(skill,duration,animator);
    }

    public static ValueAnimator glide(Skill skill, float duration, ValueAnimator animator, BaseEasingMethod.EasingListener ... listeners){
        BaseEasingMethod t = skill.getMethod(duration);
        Interpolator i=new OvershootInterpolator();

        if(listeners!=null)
            t.addEasingListeners(listeners);

        animator.setEvaluator(t);

        return animator;
    }

    public static ValueAnimator glide(Skill skill,float start,float end,float duration, ValueAnimator animator,ConvertInterPolator.SpeedListener speedListener,float... tension){
        Interpolator t = skill.getIntepolator(tension);
        Interpolator interpolator = (Interpolator)new ConvertInterPolator(start,end,duration,t,speedListener);
        animator.setInterpolator(interpolator);

        return animator;
    }

    public static PropertyValuesHolder glide(Skill skill, float duration, PropertyValuesHolder propertyValuesHolder){
        propertyValuesHolder.setEvaluator(skill.getMethod(duration));
        return propertyValuesHolder;
    }


}
