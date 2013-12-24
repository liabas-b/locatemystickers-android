package com.locatemystickers.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TextViewPlus extends TextView {
    private Map<String, String> _map = new HashMap<String, String>() {{
        put("defaultFont","fonts/lato-regular.ttf;15;#000000");
        put("defaultSelectedFont","fonts/lato-light.tff;15;#000000");
        put("lightFont","fonts/lato-hairline.tff;15;#000000");
        put("mediumFont","fonts/lato-light.tff;15;#000000");
        put("navBarFont", "fonts/lato-hairline.ttf;27;#FFFFFF");
        put("buttonFont", "fonts/lato-light.tff;15;#000000");
    }};

    public TextViewPlus(Context context)
    {
        super(context);
    }

    public TextViewPlus(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public TextViewPlus(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context context, AttributeSet attrs)
    {
        TypedArray a = context.obtainStyledAttributes(attrs, com.locatemystickers.R.styleable.TextViewPlus);
        String customFont = a.getString(com.locatemystickers.R.styleable.TextViewPlus_setCustomFont);
        setCustomFont(context, customFont);
    }

    public boolean setCustomFont(Context context, String asset)
    {
        Typeface tf = null;
        Iterator it = _map.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry e = (Map.Entry)it.next();
            if (e.getKey().equals(asset))
            {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, Integer.parseInt(e.getValue().toString().split(";")[1]));
                setTextColor(Color.parseColor(e.getValue().toString().split(";")[2]));
                try {
                    tf = Typeface.createFromAsset(context.getAssets(), e.getValue().toString().split(";")[0]);
                } catch (Exception ex) {
                    Log.e(TextViewPlus.class.getName(), ex.getMessage());
                    return false;
                }
                setTypeface(tf);
                return true;
            }
        }
        return false;
    }
}
