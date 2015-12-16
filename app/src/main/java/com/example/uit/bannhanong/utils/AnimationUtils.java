package com.example.uit.bannhanong.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.example.uit.bannhanong.MainApplication;

public class AnimationUtils {

    public static final int SHOW_HIDE_DURATION = 1000;
    public static final int PULSE_ANIMATOR_DURATION = 544;

    public static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR;
    public static final Interpolator FAST_OUT_LINEAR_IN_INTERPOLATOR;
    public static final Interpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR;
    public static final Interpolator DECELERATE_CUBIC_INTERPOLATOR;
    public static final Interpolator PAGER_INTERPOLATOR;
    public static final Interpolator DECELERATE_INTERPOLATOR;
    public static final Interpolator ACCELERATE_INTERPOLATOR;

    static {
        FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
        FAST_OUT_LINEAR_IN_INTERPOLATOR = new FastOutLinearInInterpolator();
        LINEAR_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();
        DECELERATE_CUBIC_INTERPOLATOR = android.view.animation.AnimationUtils
                .loadInterpolator(MainApplication.getContext(), android.R.interpolator.decelerate_cubic);
        PAGER_INTERPOLATOR = new Interpolator() {
            public float getInterpolation(float t) {
                t -= 1.0f;
                return t * t * t * t * t + 1.0f;
            }
        };
        DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
        ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    }

    /**
     * Render an animator to pulsate a view in place.
     *
     * @param labelToAnimate the view to pulsate.
     * @return The animator object. Use .start() to begin.
     */
    public static ObjectAnimator getPulseAnimator(View labelToAnimate, float decreaseRatio, float increaseRatio) {
        Keyframe k0 = Keyframe.ofFloat(0f, 1f);
        Keyframe k1 = Keyframe.ofFloat(0.275f, decreaseRatio);
        Keyframe k2 = Keyframe.ofFloat(0.69f, increaseRatio);
        Keyframe k3 = Keyframe.ofFloat(1f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe("scaleX", k0, k1, k2, k3);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("scaleY", k0, k1, k2, k3);
        ObjectAnimator pulseAnimator = ObjectAnimator.ofPropertyValuesHolder(labelToAnimate, scaleX, scaleY);
        pulseAnimator.setDuration(PULSE_ANIMATOR_DURATION);
        return pulseAnimator;
    }

    public static void hideView(final View view, final Runnable runAfterEnding) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", 0f);
        fadeOut.setDuration(SHOW_HIDE_DURATION);
        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (runAfterEnding != null) {
                    runAfterEnding.run();
                }
            }
        });
        fadeOut.start();
    }


    public static void invisibleView(final View view) {
        if (view.getVisibility() == View.GONE) {
            return;
        }
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", view.getAlpha(), 0f);
        fadeOut.setDuration(SHOW_HIDE_DURATION);
        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);
            }
        });
        fadeOut.start();
    }

    public static void visibleView(final View view) {
        if (view.getVisibility() == View.VISIBLE) {
            return;
        }
        view.setAlpha(0);
        view.setVisibility(View.VISIBLE);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        fadeIn.setDuration(SHOW_HIDE_DURATION);
        fadeIn.start();
    }

    public static void shakeView(final View view) {
        ObjectAnimator
                .ofFloat(view, "translationX", 0, 50, -50, 40, -40, 30, -30, 6, -6, 0)
                .setDuration(400)
                .start();
    }
}
