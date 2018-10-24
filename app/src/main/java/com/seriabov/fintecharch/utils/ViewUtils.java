package com.seriabov.fintecharch.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.seriabov.fintecharch.R;

public class ViewUtils {
    public static void setColorPercentChange(double percentChange, TextView textView, Context context) {
        if (percentChange > 0) {
            textView.setTextColor(ContextCompat.getColor(context, R.color.green700));
        } else {
            textView.setTextColor(ContextCompat.getColor(context, R.color.red700));
        }
    }
}
