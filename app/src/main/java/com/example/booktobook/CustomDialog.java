package com.example.booktobook;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class CustomDialog extends Dialog {
    TextView camera,gallery;
    View.OnClickListener cameraListener;
    View.OnClickListener galleryListener;

    public CustomDialog(Context context) {
        super(context);
        camera=findViewById(R.id.textview_camera);
        gallery=findViewById(R.id.gallery);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.camera_dialog);

        camera.setOnClickListener(cameraListener);
        gallery.setOnClickListener(galleryListener);

    }

    CustomDialog(Context context,View.OnClickListener cameraListener,View.OnClickListener galleryListener)
    {
        super(context);
        this.cameraListener=cameraListener;
        this.galleryListener=galleryListener;

    }



}
