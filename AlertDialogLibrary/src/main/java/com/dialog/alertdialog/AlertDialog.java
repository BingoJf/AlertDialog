package com.dialog.alertdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dialog.R;
import com.dialog.utils.ScreenUtils;

public class AlertDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;

    private RelativeLayout txtTitleRelativeLayout;
    private TextView txtTitle;
    private RelativeLayout txtMsgRelativeLayout;
    private TextView txtMsg;

    private RelativeLayout btnCancelRelativeLayout;
    private Button BtnCancel;
    private RelativeLayout btnConfirmRelativeLayout;
    private Button btnConfirm;
    private View imgLine;

    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showConfirmBtn = false;
    private boolean showCancelBtn = false;

    private int mLayout;

    private boolean ConfirmOnclickNoCancel;       //针对于 点击确认按钮dialog 不消失

    public AlertDialog(Context context) {
        this.context = context;
        display = ScreenUtils.getInstance().DefaultDisplay((Activity) context);

        mLayout = R.layout.alertdialog;
        builder();
    }

    public AlertDialog(Context context, int layout) {
        this.context = context;
        display = ScreenUtils.getInstance().DefaultDisplay((Activity) context);

        mLayout = layout;
        builder();
    }

    private void builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(mLayout, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = view.findViewById(R.id.lLayout_bg);

        txtTitleRelativeLayout = view.findViewById(R.id.txtTitleRelativeLayout);
        if(null!=txtTitleRelativeLayout)
            txtTitleRelativeLayout.setVisibility(View.GONE);
        txtTitle = view.findViewById(R.id.txtTitle);

        txtMsgRelativeLayout = view.findViewById(R.id.txtMsgRelativeLayout);
        if(null!=txtMsgRelativeLayout)
            txtMsgRelativeLayout.setVisibility(View.GONE);
        txtMsg = view.findViewById(R.id.txtMsg);

        btnConfirmRelativeLayout = view.findViewById(R.id.btnConfirmRelativeLayout);
        if(null!=btnConfirmRelativeLayout)
            btnConfirmRelativeLayout.setVisibility(View.GONE);
        btnConfirm = view.findViewById(R.id.btnConfirm);

        btnCancelRelativeLayout = view.findViewById(R.id.btnCancelRelativeLayout);
        if(null!=btnCancelRelativeLayout)
            btnCancelRelativeLayout.setVisibility(View.GONE);
        BtnCancel = view.findViewById(R.id.BtnCancel);

        imgLine = view.findViewById(R.id.imgLine);
        if(null!=imgLine)
            imgLine.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);


        double Scale;
        //横竖屏 设置 Dialog所占比例
        if (display.getWidth() > display.getHeight()) {
            Scale = 0.4;
        } else {
            Scale = 0.8;
        }
        // 调整dialog背景大小
        if(null!=lLayout_bg)
            lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * Scale), LayoutParams.WRAP_CONTENT));

    }

    /***************设置标题头*/
    public AlertDialog setTitle(String msg) {
        if(null!=txtTitle)
            txtTitle.setText(TitleSet(msg));
        return this;
    }

    public AlertDialog setTitle(String msg, int TxtColor) {
        setTitle(msg);
        if(null!=txtTitle)
            txtTitle.setTextColor(context.getResources().getColor(TxtColor));
        return this;
    }

    private String TitleSet(String Msg) {
        showTitle = true;
        if (TextUtils.isEmpty(Msg)) {
            Msg = context.getResources().getString(R.string.AlerDialogTitle);
        }
        return Msg;
    }

    /****************设置内容体*/
    public AlertDialog setShowMsg(String msg) {
        if(null!=txtMsg)
            txtMsg.setText(MsgSet(msg));
        return this;
    }

    public AlertDialog setShowMsg(String msg, int TxtColor) {
        setShowMsg(msg);
        if(null!=txtMsg)
            txtMsg.setTextColor(context.getResources().getColor(TxtColor));
        return this;
    }

    public AlertDialog setShowMsg(String msg, int TxtColor,int showMsgGravity) {
        setShowMsg(msg,TxtColor);

        if(null!=txtMsg)
            txtMsg.setGravity(showMsgGravity);
        return this;
    }

    private String MsgSet(String Msg) {
        showMsg = true;
        if (TextUtils.isEmpty(Msg)) {
            Msg = context.getResources().getString(R.string.AlerDialogContext);
        }
        return Msg;
    }

    // SpannableString
    public AlertDialog setShowMsg(SpannableString msg,int defaultColor) {
        showMsg = true;

        if(null!=txtMsg){
            txtMsg.setTextColor(context.getResources().getColor(defaultColor));
            txtMsg.setText(msg);
        }
        return this;
    }

    public AlertDialog setShowMsg(SpannableString msg,int defaultColor,int showMsgGravity) {
        setShowMsg(msg,defaultColor);
        if(null!=txtMsg)
            txtMsg.setGravity(showMsgGravity);
        return this;
    }

    /**********************************************************************************************/

    public AlertDialog SetConfirmOnclickNoCancel(boolean ConfirmOnclickNoCancel){
        this.ConfirmOnclickNoCancel=ConfirmOnclickNoCancel;
        return this;
    }

    public AlertDialog setCancelable(boolean Cancelable, boolean CanceledOnTouchOutside) {
        dialog.setCancelable(Cancelable);
        dialog.setCanceledOnTouchOutside(CanceledOnTouchOutside);
        return this;
    }

    /********************确认*/
    //兼容以前
    public AlertDialog setRightButton(String text, final OnClickListener listener) {
        setConfirmButton(text,listener);
        return this;
    }

    public AlertDialog setRightButton(String text, int TxtColor, final OnClickListener listener) {
        setConfirmButton(text,TxtColor,listener);
        return this;
    }

    public AlertDialog setConfirmButton(String text, final OnClickListener listener) {
        if(null!=btnConfirm){
            btnConfirm.setText(ConfirmSet(text));
            btnConfirm.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null!=listener)
                        listener.onClick(v);

                    if(!ConfirmOnclickNoCancel){
                        dialog.dismiss();
                    }
                }
            });
        }
        return this;
    }

    public AlertDialog setConfirmButton(String text, int TxtColor, final OnClickListener listener) {
        setConfirmButton(text,listener);

        if(null!=btnConfirm)
            btnConfirm.setTextColor(context.getResources().getColor(TxtColor));
        return this;
    }

    private String ConfirmSet(String Text) {
        showConfirmBtn = true;
        if (TextUtils.isEmpty(Text)) {
            Text = context.getResources().getString(R.string.AlerDialogConfrim);
        }
        return Text;
    }

    /***************取消*/
    //兼容以前
    public AlertDialog setLeftButton(String text, final OnClickListener listener) {
        setCancelButton(text,listener);
        return this;
    }

    public AlertDialog setLeftButton(String text, int TxtColor, final OnClickListener listener) {
        setCancelButton(text,TxtColor,listener);
        return this;
    }

    public AlertDialog setCancelButton(String text, final OnClickListener listener) {
        if(null!=BtnCancel){
            BtnCancel.setText(CancelSet(text));
            BtnCancel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null!=listener)
                        listener.onClick(v);

                    dialog.dismiss();
                }
            });
        }
        return this;
    }

    public AlertDialog setCancelButton(String text, int TxtColor, final OnClickListener listener) {
        setCancelButton(text,listener);

        if(null!=BtnCancel)
            BtnCancel.setTextColor(context.getResources().getColor(TxtColor));
        return this;
    }

    private String CancelSet(String Text) {
        showCancelBtn = true;
        if (TextUtils.isEmpty(Text)) {
            Text = context.getResources().getString(R.string.AlerDialogCacel);
        }
        return Text;
    }

    private void setLayout() {
        if (showTitle) {
            if(null!=txtTitleRelativeLayout)
                txtTitleRelativeLayout.setVisibility(View.VISIBLE);
        }
        if (showMsg) {
            if(null!=txtMsgRelativeLayout)
                txtMsgRelativeLayout.setVisibility(View.VISIBLE);
        }
        if (!showConfirmBtn && !showCancelBtn) {
            if(null!=btnConfirm){
                btnConfirm.setText("确定");
                btnConfirm.setBackgroundResource(R.drawable.alertdialog_single_selector);
                btnConfirm.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                if(null!=btnConfirmRelativeLayout)
                    btnConfirmRelativeLayout.setVisibility(View.VISIBLE);
            }
        }

        if (showConfirmBtn && showCancelBtn) {
            if(null!=btnConfirmRelativeLayout)
                btnConfirmRelativeLayout.setVisibility(View.VISIBLE);

            if(null!=btnCancelRelativeLayout)
                btnCancelRelativeLayout.setVisibility(View.VISIBLE);

            if(null!=imgLine)
                imgLine.setVisibility(View.VISIBLE);
        }

        if (showConfirmBtn && !showCancelBtn) {
            if(null!=btnConfirmRelativeLayout)
                btnConfirmRelativeLayout.setVisibility(View.VISIBLE);
        }

        if (!showConfirmBtn && showCancelBtn) {
            if(null!=btnCancelRelativeLayout)
                btnCancelRelativeLayout.setVisibility(View.VISIBLE);
        }
    }

    public AlertDialog show() {
        try{
            if (null != context) {
                Activity activity = scanForActivity(context);
                if (null == activity || activity.isFinishing()) {
                    return this;
                }

                if (null != dialog && !dialog.isShowing()) {
                    setLayout();

                    dialog.show();
                }
            }
        }catch (Exception exception){

        }
        return this;
    }

    public void cancel() {
        try{
            if (null != context) {
                Activity activity = scanForActivity(context);
                if (null == activity || activity.isFinishing()) {
                    return;
                }

                if (null != dialog && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }catch (Exception exception){

        }
    }

    /***
     * Activity转换  , 装饰类 转换 处理
     * @param cont
     * @return
     */
    private static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }

}

