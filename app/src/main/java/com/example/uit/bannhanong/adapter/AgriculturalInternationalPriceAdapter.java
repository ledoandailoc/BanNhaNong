package com.example.uit.bannhanong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uit.bannhanong.DTO.Agricultural;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.utils.CommonUtils;

import java.util.List;


public class AgriculturalInternationalPriceAdapter extends ArrayAdapter<Agricultural> {
	Context context;
	int resource;

	TextView mTvName, mTvTodayPrice, mTvYesterdayPrice, mTvStatus;
	ImageView mIvStatus;
	List<Agricultural> list;

	public AgriculturalInternationalPriceAdapter(Context context, int resource, List<Agricultural> objects) {
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
		mTvName.setText(agricultural.name);
		mTvTodayPrice.setText(String.valueOf(agricultural.priceTodayInternational) + "/" + agricultural.unit);
		mTvYesterdayPrice.setText(String.valueOf(agricultural.priceYesterdayInternational) + "/" + agricultural.unit);
		mTvStatus.setText(agricultural.status);
		if (agricultural.priceTodayInternational - agricultural.priceYesterdayInternational > 0) {
			mIvStatus.setImageResource(R.drawable.ic_increase);
			mIvStatus.setVisibility(View.VISIBLE);
			mTvTodayPrice.setTextColor(getContext().getResources().getColor(R.color.tab_market_price_increase_color));
			mTvStatus.setTextColor(getContext().getResources().getColor(R.color.tab_market_price_increase_color));
		}
		else if (agricultural.priceTodayInternational - agricultural.priceYesterdayInternational < 0) {
			mIvStatus.setVisibility(View.VISIBLE);
			mIvStatus.setImageResource(R.drawable.ic_decrease);
			mTvTodayPrice.setTextColor(context.getResources().getColor(R.color.md_red_400));
			mTvStatus.setTextColor(getContext().getResources().getColor(R.color.md_red_400));
		}
		else {
			mIvStatus.setVisibility(View.GONE);
		}

		return view;
	}

	public void initView(View view){
		mTvName = CommonUtils.findViewById(view, R.id.tv_agricultural_name);
		mTvTodayPrice = CommonUtils.findViewById(view, R.id.tv_agricultural_price);
		mTvYesterdayPrice = CommonUtils.findViewById(view, R.id.tv_yesterday_agricultural_price);
		mIvStatus = CommonUtils.findViewById(view, R.id.iv_volatility_price);
		mTvStatus = CommonUtils.findViewById(view, R.id.tv_status);

	}

}
