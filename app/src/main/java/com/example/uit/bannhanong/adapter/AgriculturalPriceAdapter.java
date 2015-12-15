package com.example.uit.bannhanong.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.uit.bannhanong.DTO.Agricultural;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.utils.CommonUtils;

import java.util.List;


public class AgriculturalPriceAdapter extends ArrayAdapter<Agricultural> {
	Context context;
	int resource;

	TextView mTvName, mTvPrice;
	ImageView mIvStatus;
	List<Agricultural> list;

	public AgriculturalPriceAdapter(Context context, int resource, List<Agricultural> objects) {
		super(context, resource, objects);

		this.context = context;
		this.resource = resource;
		this.list = objects;
	}


	@Override
	public View getView(final int position, View v, ViewGroup viewGroup){
		View view = View.inflate(context, resource, null);

		Agricultural agricultural = list.get(position);

		initView(view);

		mTvName.setText(agricultural.getName());
		mTvPrice.setText(String.valueOf(agricultural.getPriceDomestic()) + "/" + agricultural.getUnit());
		if (agricultural.getStatus().equals("increase"))
		{
			mIvStatus.setImageResource(R.drawable.ic_increase);
		}
		else if (agricultural.getStatus().equals("decrease"))
		{
			mIvStatus.setImageResource(R.drawable.ic_decrease);
		}

		return view;
	}

	public void initView(View view){
		mTvName = CommonUtils.findViewById(view, R.id.tv_agricultural_name);
		mTvPrice = CommonUtils.findViewById(view, R.id.tv_agricultural_price);
		mIvStatus = CommonUtils.findViewById(view, R.id.iv_volatility_price);
	}

}
