package cn.meituan.jp.utils;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

/**
 * Created by Administrator on 2017/5/23.
 */

class NameInputConnecttion extends InputConnectionWrapper implements InputConnection {

    public NameInputConnecttion(InputConnection target, boolean mutable) {
        super(target, mutable);
    }

    /**
     * 对输入的内容进行拦截
     *
     * @param text
     * @param newCursorPosition
     * @return
     */
    @Override
    public boolean commitText(CharSequence text, int newCursorPosition) {
        // 只能输入汉字或者英文或者数字
        if (!text.toString().matches("[\u4e00-\u9fa5]+") && !text.toString().matches("[a-zA-Z /]+") && !text.toString().matches("[0-9]+]")) {

            return false;
        }
        return super.commitText(text, newCursorPosition);

    }

    @Override
    public boolean sendKeyEvent(KeyEvent event) {
        return super.sendKeyEvent(event);
    }

    @Override
    public boolean setSelection(int start, int end) {
        return super.setSelection(start, end);
    }
}
