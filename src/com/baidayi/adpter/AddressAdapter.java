package com.baidayi.adpter;

import java.util.List;

import com.baidayi.activity.R;
import com.baidayi.domain.Address;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * µÿ÷∑   ≈‰∆˜
 * 
 * @author: wll
 */
public class AddressAdapter extends BaseAdapter {
	List<Address> products;
	private Context context;

	private TextView address_item_name;

	public AddressAdapter(Context context, List<Address> list) {
		super();
		this.context = context;
		this.products = list;

	}

	public void setAddress(List<Address> addresses) {
		this.products = addresses;
	}

	public List<Address> getProducts() {
		return products;
	}

	public void setProducts(List<Address> products) {
		this.products.addAll(products);
	}

	@Override
	public int getCount() {
		return products.size();
	}

	@Override
	public Object getItem(int position) {
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout relativeLayout;
		if (convertView != null) {
			relativeLayout = (RelativeLayout) convertView;
		} else {
			relativeLayout = (RelativeLayout) View.inflate(context,
					R.layout.address_item, null);
		}

		address_item_name = (TextView) relativeLayout
				.findViewById(R.id.address_item_name);
		address_item_name.setText(products.get(position).getName());

		((TextView) relativeLayout.findViewById(R.id.address_item_address))
				.setText(products.get(position).getAddress());
		((TextView) relativeLayout.findViewById(R.id.address_item_phone))
				.setText(products.get(position).getJiedao());

		return relativeLayout;
	}
}
