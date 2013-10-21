package com.locatemystickers.segmentedradio;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.locatemystickers.R;

/**
 * Created by reclus_s on 22/06/13.
 */
public class SegmentedRadioGroup extends RadioGroup{

    public SegmentedRadioGroup(Context context) {
        super(context);
    }
    public SegmentedRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();

        if (count > 1) {
            super.getChildAt(0).setBackgroundResource(R.drawable.segment_radio_left);
            for (int i=1;i<count-1;i++) {
                super.getChildAt(i).setBackgroundResource(R.drawable.segment_radio_middle);
            }
            super.getChildAt(count-1).setBackgroundResource(R.drawable.segment_radio_right);
        } else if (count == 1) {
            super.getChildAt(0).setBackgroundResource(R.drawable.segment_button);
        }
    }
}
