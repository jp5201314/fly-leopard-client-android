package cn.meituan.jp.utils;
import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/5/23.
 */

public class NameLimitEditText extends EditText {


    public NameLimitEditText(Context context) {
        super(context);
    }

    public NameLimitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NameLimitEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 输入法
     * @param outAttrs
     * @return
     */
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new NameInputConnecttion(super.onCreateInputConnection(outAttrs), false);
    }
}
