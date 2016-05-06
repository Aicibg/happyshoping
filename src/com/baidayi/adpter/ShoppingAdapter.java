package com.baidayi.adpter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidayi.activity.R;
import com.baidayi.domain.Product;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * 购物适配器
 * 
 * @author: wll
 */
public class ShoppingAdapter extends BaseAdapter {
	List<Product> products;
	private Context context;
	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;

	public ShoppingAdapter(Context context, List<Product> products) {
		super();
		this.context = context;
		this.products = products;
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));

		options = new DisplayImageOptions.Builder()
				.displayer(new RoundedBitmapDisplayer(0xff000000, 10))//设置图片圆角，颜色为0xff000000，弧度为10
				.cacheInMemory().cacheOnDisc().build();//cacheInMemory()缓存在内存中，cacheOnDisc()缓存在sd卡中
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
					R.layout.shopping_item, null);
		}
		ImageView imageView = (ImageView) relativeLayout
				.findViewById(R.id.shopping_item_image);

		imageLoader.displayImage(products.get(position).getImageUrl(),
				imageView, options);
		((TextView) relativeLayout.findViewById(R.id.shopping_item_name))
				.setText(products.get(position).getProductName());
		((TextView) relativeLayout.findViewById(R.id.shopping_item_describe))
				.setText(products.get(position).getProductDescribe());
		((TextView) relativeLayout.findViewById(R.id.shopping_item_price))
				.setText(products.get(position).getProductPrice());
		return relativeLayout;
	}

}
