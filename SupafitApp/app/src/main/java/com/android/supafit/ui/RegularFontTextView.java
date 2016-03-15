package com.android.supafit.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by harsh on 2/23/16.
 */
public class RegularFontTextView extends TextView {

  private static Typeface mFont = null;

  public RegularFontTextView(Context context) {
    super(context);
    init(context);
  }

  public RegularFontTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public RegularFontTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context);
  }

  public RegularFontTextView(Context context, AttributeSet attrs,
      int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context);
  }

  private void init(Context context) {
    if (!this.isInEditMode()) {
      if (mFont == null) {
        mFont = Typeface.createFromAsset(context.getAssets(),
            "fonts/Roboto-Regular.ttf");
      }

      setTypeface(mFont);
    }
  }
}
