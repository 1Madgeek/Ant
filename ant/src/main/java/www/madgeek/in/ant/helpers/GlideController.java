package www.madgeek.in.ant.helpers;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Madgeek Pvt Ltd on 12/08/18.
 * Copyright 2016-2017. All Rights Reserved
 * www.madgeek.in
 */

public class GlideController {

    private Activity activity;
    private ImageView imageview;

    public GlideController(Activity activity) {
        this.activity = activity;
    }

    public static GlideController init(Activity activity){
        return new GlideController(activity);
    }

    public GlideController setImageView(ImageView imageView){
        this.imageview = imageView;
        return this;
    }

    //Display images using glide
    public void mDrawable(Drawable drawable) throws NullPointerException{
        Glide.with(this.activity)
                .load(drawable)
                .into(this.imageview);
    }

    public void mObject(Object object){}

}
