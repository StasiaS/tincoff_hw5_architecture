package com.seriabov.fintecharch.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.seriabov.fintecharch.R;
import com.seriabov.fintecharch.data.model.CoinInfo;

public class ViewUtils {
    public static void setColorPercentChange7d(CoinInfo info, TextView textView, Context context) {
        if (info.getPercentChange7d() > 0) {
            textView.setTextColor(ContextCompat.getColor(context, R.color.green700));
        } else {
            textView.setTextColor(ContextCompat.getColor(context, R.color.red700));
        }
    }
}
