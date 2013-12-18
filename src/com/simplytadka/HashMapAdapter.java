package com.simplytadka;

import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class HashMapAdapter extends BaseAdapter
{
	HashMap<String, String> items;
	private String[] mValues;
	private Context context;

	public HashMapAdapter(Context context, HashMap<String, String> items)
	{
		this.context=context;
		this.items=items;
		this.mValues = items.values().toArray(new String[items.size()]);

	}

	@Override
	public int getCount()
	{
		return items.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mValues[position];
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		String dispName = (String) getItem(position);

		View view = GetInflater(context).inflate(android.R.layout.simple_list_item_1, null);

		TextView textView = (TextView) view.findViewById(android.R.id.text1);

		textView.setText(dispName);

		return view;
	}

	private static LayoutInflater GetInflater(Context context)
	{

		return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

}
