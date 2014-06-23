package com.example.myadapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener{

	private int num=0;
	private String[] names;
	private String[] provs;
	private int[] imageIds;
	MyAdapterClass adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listview = (ListView) this.findViewById(R.id.app_list);
		adapter = new MyAdapterClass(this);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);//设置监听
		
		imageIds = new int[]
				{R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,
				R.drawable.icon_4,R.drawable.icon_5,R.drawable.icon_6,
				R.drawable.icon_7,R.drawable.icon_8};
		names = getResources().getStringArray(R.array.NameArray);
		provs = getResources().getStringArray(R.array.PermissArray);
		
		
		Button buttonAdd = (Button) this.findViewById(R.id.button_add);
		setAddOnClick(buttonAdd);

		Button buttonDel = (Button) this.findViewById(R.id.button_del);
		setDelOnClick(buttonDel);
		
		Button buttonDelAll = (Button) this.findViewById(R.id.button_delAll);
		setDelAllOnClick(buttonDelAll);
	}

	private void setAddOnClick(Button buttonAdd) {
		buttonAdd.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
			    final ListInfo item = setItemInfo();
				num = (num+1)%8;
				adapter.add(item);
			}				
		});
	}
	private ListInfo setItemInfo() {
		final ListInfo item = new ListInfo();
		item.setIconName(getResources().getDrawable(imageIds[num]));
		item.setAppName(names[num]);
		item.setPermission(provs[num]);
		return item;
	}
	private void setDelOnClick(Button buttonDel)
	{
		buttonDel.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
				
				if(adapter.isEmpty())
				{	alertDialog.setTitle("列表为空");
					alertDialog.setNegativeButton("确定",null);
				}
				else
				{
					alertDialog.setTitle("确定删除吗?");
					alertDialog.setPositiveButton("取消",null);
					alertDialog.setNegativeButton("确定",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface alertDialog, int pos) 
						{
							adapter.removeFirst();
						}
					});
				}
				alertDialog.setPositiveButton("取消",null);
				alertDialog.create(); 
			    alertDialog.show();
			}
		});
	}

	private void setDelAllOnClick(Button buttonDelAll) {
		buttonDelAll.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
				alertDialog.setTitle("确定删除所有吗?");
				alertDialog.setPositiveButton("取消",null);
				alertDialog.setNegativeButton("确定",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface alertDialog, int pos) 
					{
						adapter.removeAll();
					}
				});
				alertDialog.create(); 
			    alertDialog.show();
			}			
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, final int position, long id) 
	{
		Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(adapter.getItem(position).getAppName());
		alertDialog.setMessage(adapter.getItem(position).getPermission());
		alertDialog.setIcon(adapter.getItem(position).getIconName());
		alertDialog.setPositiveButton("取消",null);
		alertDialog.setNegativeButton("删除", 
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface alertDialog, int pos) 
			{
				adapter.remove(position);
			}
		});
		alertDialog.create(); 
	    alertDialog.show();
		
	}
}
