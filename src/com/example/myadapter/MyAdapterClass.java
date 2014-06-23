package com.example.myadapter;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapterClass extends BaseAdapter 
{
	private ArrayList<ListInfo> mApp = new ArrayList<ListInfo>();
	private Activity mActivity;

	private LayoutInflater mInflater;
	MyAdapterClass(Context context)
	{
		mInflater = LayoutInflater.from(context);
		mActivity = (Activity) context;
	}
	@Override
	public int getCount() {
		return mApp.size();
	}

	public ListInfo getItem(int position) {
		return mApp.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convert, ViewGroup parent) {
		if(convert == null)
		{
			convert = mInflater.inflate(R.layout.list_view, null);
		}

		ConnectUI((ListInfo) getItem(position),convert);
		return convert;
	}
	private void ConnectUI(final ListInfo item, View convert) {
		setTextAppImage(item, convert);
		setTextAppName(item, convert);
		setTextAppPermission(item, convert);
	}
	private void setTextAppPermission(final ListInfo item, View convert) 
	{
		TextView textPermission = (TextView) convert.findViewById(R.id.permission);
		textPermission.setText(item.getPermission());
		setClickAddAppPermission(item, textPermission);
	}
	private void setTextAppName(final ListInfo item, View convert) 
	{
		TextView textAppName = (TextView) convert.findViewById(R.id.name);
		textAppName.setText(item.getAppName());
		setClickAddAppName(item, textAppName);
	}
	private void setTextAppImage(final ListInfo item, View convert) 
	{
		ImageView image = (ImageView) convert.findViewById(R.id.icon);
		image.setImageDrawable(item.getIconName());
		setClickAddAppImage(item, image);
	}
	private void setClickAddAppImage(final ListInfo item, ImageView image) 
	{
		image.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				Builder alertDialog = new AlertDialog.Builder(mActivity);
				alertDialog.setTitle(item.getAppName());
				alertDialog.setIcon(item.getIconName());
				dialogSet(alertDialog);
			}	
		});
	}
	
	private void setClickAddAppName(final ListInfo item, TextView text) {
		text.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				Builder alertDialog = new AlertDialog.Builder(mActivity);
				alertDialog.setMessage(item.getAppName());
				dialogSet(alertDialog);
			}
		});
	}
	
	private void setClickAddAppPermission(final ListInfo item, TextView text) {
		text.setOnClickListener(new OnClickListener()
		{
			public void onClick(View view) 
			{
				Builder alertDialog = new AlertDialog.Builder(mActivity);
				alertDialog.setMessage(item.getPermission());
				dialogSet(alertDialog);
			}
		});
	}
	
	private void dialogSet(Builder alertDialog) {
		alertDialog.setPositiveButton("取消", null);
		alertDialog.setNegativeButton("确定", null);
		alertDialog.create(); 
	    alertDialog.show();
	}
	public void add(ListInfo item) 
	{
		mApp.add(item);
		notifyDataSetChanged();
	}
	public void removeAll() 
	{
		while (!mApp.isEmpty())
		{
			mApp.remove(0);
			notifyDataSetChanged();
		}
	}
	
	public void removeFirst() 
	{
		if (!mApp.isEmpty()) 
		{
			mApp.remove(0);
		}
		notifyDataSetChanged();
	}
	
	public void remove(int position) 
	{
		mApp.remove(position);
		notifyDataSetChanged();
		
	}
	
	
}