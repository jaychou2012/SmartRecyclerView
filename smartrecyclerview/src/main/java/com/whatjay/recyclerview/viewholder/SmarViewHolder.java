package com.whatjay.recyclerview.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by office on 2017/3/1.
 */
public class SmarViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> viewSparseArray;
    private View view;
    private Context context;

    public SmarViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        view = itemView;
        viewSparseArray = new SparseArray<View>();
    }

    public static SmarViewHolder getViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        SmarViewHolder smarViewHolder = new SmarViewHolder(context, itemView);
        return smarViewHolder;
    }

    public static SmarViewHolder getViewHolder(Context context, ViewGroup parent, View itemView) {
        SmarViewHolder smarViewHolder = new SmarViewHolder(context, itemView);
        return smarViewHolder;
    }

    public View getItemView() {
        return view;
    }

    public <T extends View> T getView(int viewId) {
        View v = viewSparseArray.get(viewId);
        if (v == null) {
            v = view.findViewById(viewId);
            viewSparseArray.put(viewId, v);
        }
        return (T) v;
    }

    public SmarViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public SmarViewHolder setTextColor(int viewId, int textColor) {
        TextView tv = getView(viewId);
        tv.setTextColor(textColor);
        return this;
    }

    public SmarViewHolder setTextSize(int viewId, float textSize) {
        TextView tv = getView(viewId);
        tv.setTextSize(textSize);
        return this;
    }

    public SmarViewHolder setTextSize(int viewId, float textSize, int unit) {
        TextView tv = getView(viewId);
        tv.setTextSize(unit, textSize);
        return this;
    }

    public SmarViewHolder setImageResource(int viewId, int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public SmarViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public SmarViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public SmarViewHolder setBackgroundResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    public SmarViewHolder setBackgroundColor(int viewId, int color) {
        ImageView view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public SmarViewHolder setBackgroundColor(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setBackgroundDrawable(drawable);
        return this;
    }

    public SmarViewHolder setVisible(int viewId) {
        View view = getView(viewId);
        view.setVisibility(View.VISIBLE);
        return this;
    }

    public SmarViewHolder setInVisible(int viewId) {
        View view = getView(viewId);
        view.setVisibility(View.INVISIBLE);
        return this;
    }

    public SmarViewHolder setGone(int viewId) {
        View view = getView(viewId);
        view.setVisibility(View.GONE);
        return this;
    }

    public SmarViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public SmarViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public SmarViewHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    public SmarViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public SmarViewHolder setProgressMax(int viewId, int maxProgress) {
        ProgressBar view = getView(viewId);
        view.setMax(maxProgress);
        return this;
    }

    public SmarViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public SmarViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public SmarViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public SmarViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public SmarViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public SmarViewHolder setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
        return this;
    }

    public SmarViewHolder setAdapter(int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    public SmarViewHolder setEditTextHint(int viewId, String hint) {
        EditText view = getView(viewId);
        view.setHint(hint);
        return this;
    }

    public SmarViewHolder setEditTextSelection(int viewId, int selection) {
        EditText view = getView(viewId);
        view.setSelection(selection);
        return this;
    }

    public SmarViewHolder setSingleLine(int viewId, boolean singleline) {
        TextView view = getView(viewId);
        view.setSingleLine(singleline);
        return this;
    }

    public SmarViewHolder setEditTextSingleLine(int viewId, boolean singleline) {
        EditText view = getView(viewId);
        view.setSingleLine(singleline);
        return this;
    }

    public SmarViewHolder setDrawableLeft(int viewId, Drawable drawable) {
        TextView view = getView(viewId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(drawable, null, null, null);
        return this;
    }

    public SmarViewHolder setDrawableLeft(int viewId, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        TextView view = getView(viewId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(drawable, null, null, null);
        return this;
    }

    public SmarViewHolder setDrawableRight(int viewId, Drawable drawable) {
        TextView view = getView(viewId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(null, null, drawable, null);
        return this;
    }

    public SmarViewHolder setDrawableRight(int viewId, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        TextView view = getView(viewId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(null, null, drawable, null);
        return this;
    }

    public SmarViewHolder setDrawableTop(int viewId, Drawable drawable) {
        TextView view = getView(viewId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(null, drawable, null, null);
        return this;
    }

    public SmarViewHolder setDrawableTop(int viewId, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        TextView view = getView(viewId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(null, drawable, null, null);
        return this;
    }

    public SmarViewHolder setDrawableBottom(int viewId, Drawable drawable) {
        TextView view = getView(viewId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(null, null, null, drawable);
        return this;
    }

    public SmarViewHolder setDrawableBottom(int viewId, int resId) {
        Drawable drawable = context.getResources().getDrawable(resId);
        TextView view = getView(viewId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        view.setCompoundDrawables(null, null, null, drawable);
        return this;
    }

    public SmarViewHolder setSeekbarProgress(int viewId, int progress) {
        SeekBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public SmarViewHolder setSeekbarMax(int viewId, int max) {
        SeekBar view = getView(viewId);
        view.setMax(max);
        return this;
    }
}
