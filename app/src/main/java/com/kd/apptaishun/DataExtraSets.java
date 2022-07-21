package com.kd.apptaishun;

import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class DataExtraSets {

    @BindingAdapter("setExtraTypeface")
    public static void setExtraTypeface(TextView textView, Typeface typeFace){
        Log.i("TAG","setExtraTypeface");
        if(textView!=null){
            textView.setTypeface(typeFace);
            textView.getPaint().setAntiAlias(false);
        }
    };
    @BindingAdapter("setMarqueeViewTypeface")
    public static void setExtraTypeface(MarqueeView textView, Typeface typeFace){
        Log.i("TAG","setExtraTypeface");
        if(typeFace==null)
        Log.i("TAG","是否：typeFace==null");
        if(textView!=null){
            textView.setTypeface(typeFace);
            textView.getPaint().setAntiAlias(false);
        }
    };
    @BindingAdapter("setExtraTextSize")
    public static void setExtraTextSize(TextView textView, int size){
        Log.i("TAG","setExtraTypeface");
        if(size>0){
           textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float) size);
        }
    };
    @BindingAdapter("setText")
    public static void setText(MarqueeView view, String text){
        if (TextUtils.isEmpty(text))return;
        view.setContent(text);
    }
}
