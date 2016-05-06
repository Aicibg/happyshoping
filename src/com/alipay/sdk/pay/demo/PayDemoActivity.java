package com.alipay.sdk.pay.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.alipay.sdk.app.PayTask;
import com.baidayi.activity.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class PayDemoActivity extends FragmentActivity {

	// 閸熷棙鍩汸ID
	public static final String PARTNER = "20881231232123123";
	// 閸熷棙鍩涢弨鑸殿儥鐠愶箑褰�
	public static final String SELLER = "18086518280";
	// 閸熷棙鍩涚粔渚�鎸滈敍瀹瞜cs8閺嶇厧绱�
	public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAN0yqPkLXlnhM+2H/57aHsYHaHXazr9pFQun907TMvmbR04wHChVsKVgGUF1hC0FN9hfeYT5v2SXg1WJSg2tSgk7F29SpsF0I36oSLCIszxdu7ClO7c22mxEVuCjmYpJdqb6XweAZzv4Is661jXP4PdrCTHRdVTU5zR9xUByiLSVAgMBAAECgYEAhznORRonHylm9oKaygEsqQGkYdBXbnsOS6busLi6xA+iovEUdbAVIrTCG9t854z2HAgaISoRUKyztJoOtJfI1wJaQU+XL+U3JIh4jmNx/k5UzJijfvfpT7Cv3ueMtqyAGBJrkLvXjiS7O5ylaCGuB0Qz711bWGkRrVoosPM3N6ECQQD8hVQUgnHEVHZYtvFqfcoq2g/onPbSqyjdrRu35a7PvgDAZx69Mr/XggGNTgT3jJn7+2XmiGkHM1fd1Ob/3uAdAkEA4D7aE3ZgXG/PQqlm3VbE/+4MvNl8xhjqOkByBOY2ZFfWKhlRziLEPSSAh16xEJ79WgY9iti+guLRAMravGrs2QJBAOmKWYeaWKNNxiIoF7/4VDgrcpkcSf3uRB44UjFSn8kLnWBUPo6WV+x1FQBdjqRviZ4NFGIP+KqrJnFHzNgJhVUCQFzCAukMDV4PLfeQJSmna8PFz2UKva8fvTutTryyEYu+PauaX5laDjyQbc4RIEMU0Q29CRX3BA8WDYg7YPGRdTkCQQCG+pjU2FB17ZLuKRlKEdtXNV6zQFTmFc1TKhlsDTtCkWs/xwkoCfZKstuV3Uc5J4BNJDkQOGm38pDRPcUDUh2/";
	// 閺�顖欑帛鐎规繂鍙曢柦锟�
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQWiDVZ7XYxa4CQsZoB3n7bfxLDkeGKjyQPt2FUtm4TWX9OYrd523iw6UUqnQ+Evfw88JgRnhyXadp+vnPKP7unormYQAfsM/CxzrfMoVdtwSiGtIJB4pfyRXjA+KL8nIa2hdQy5nLfgPVGZN4WidfUY/QpkddCVXnZ4bAUaQjXQIDAQAB";
	private static final int SDK_PAY_FLAG = 1;
    private String productname;
    private String productdescription;
    private String productPrice;
    private TextView tvproductname,tvproductdescription,tvproductPrice;
    
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);
				/**
				 * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
				 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
				 * docType=1) 建议商户依赖异步通知
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息

				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
				} else {
					// 判断resultStatus 为非"9000"则代表可能支付失败
					// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(PayDemoActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pay_main);
		Intent intent=getIntent();
		productdescription=intent.getStringExtra("productdesrcption");
		productname=intent.getStringExtra("productname");
		productPrice=intent.getStringExtra("productprice");
		
		tvproductdescription=(TextView) findViewById(R.id.product_decription);
		tvproductname=(TextView) findViewById(R.id.product_subject);
		tvproductPrice=(TextView) findViewById(R.id.product_price);
		
		tvproductdescription.setText(productdescription);
		tvproductname.setText(productname);
		tvproductPrice.setText(productPrice);
		Log.i("product支付宝", productdescription+" "+productname+" "+productPrice);
	}

	/**
	 * call alipay sdk pay. 鐠嬪啰鏁DK閺�顖欑帛
	 * 
	 */
	public void pay(View v) {
		if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							//
							finish();
						}
					}).show();
			return;
		}
		String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");

		/**
		 * 閻楃懓鍩嗗▔銊﹀壈閿涘矁绻栭柌宀�娈戠粵鎯ф倳闁槒绶棁锟界憰浣规杹閸︺劍婀囬崝锛勵伂閿涘苯鍨忛崟鍨殺缁変線鎸滃▔鍕苟閸︺劋鍞惍浣疯厬閿涳拷
		 */
		String sign = sign(orderInfo);
		try {
			/**
			 * 娴犲懘娓剁�电畟ign 閸嬫瓗RL缂傛牜鐖�
			 */
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/**
		 * 鐎瑰本鏆ｉ惃鍕儊閸氬牊鏁禒妯虹杺閸欏倹鏆熺憴鍕瘱閻ㄥ嫯顓归崡鏇氫繆閹拷
		 */
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 閺嬪嫰锟界嚛ayTask 鐎电钖�
				PayTask alipay = new PayTask(PayDemoActivity.this);
				// 鐠嬪啰鏁ら弨顖欑帛閹恒儱褰涢敍宀冨箯閸欐牗鏁禒妯肩波閺嬶拷
				String result = alipay.pay(payInfo, true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 韫囧懘銆忓鍌涱劄鐠嬪啰鏁�
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	/**
	 * get the sdk version. 閼惧嘲褰嘢DK閻楀牊婀伴崣锟�
	 * 
	 */
	public void getSDKVersion() {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 閸樼喓鏁撻惃鍑�5閿涘牊澧滈張铏圭秹妞ょ數澧楅弨顖欑帛閸掑檳atvie閺�顖欑帛閿涳拷 閵嗘劕顕惔鏃堛�夐棃銏㈢秹妞ゅ灚鏁禒妯诲瘻闁筋喓锟斤拷
	 * 
	 * @param v
	 */
	public void h5Pay(View v) {
		Intent intent = new Intent(this, H5PayDemoActivity.class);
		Bundle extras = new Bundle();
		/**
		 * url閺勵垱绁寸拠鏇犳畱缂冩垹鐝敍灞芥躬app閸愬懘鍎撮幍鎾崇磻妞ょ敻娼伴弰顖氱唨娴滃窚ebview閹垫挸绱戦惃鍕剁礉demo娑擃厾娈憌ebview閺勭枙5PayDemoActivity閿涳拷
		 * demo娑擃厽瀚ら幋鐚絩l鏉╂稖顢戦弨顖欑帛閻ㄥ嫰锟芥槒绶弰顖氭躬H5PayDemoActivity娑撶捈houldOverrideUrlLoading閺傝纭剁�圭偟骞囬敍锟�
		 * 閸熷棙鍩涢崣顖欎簰閺嶈宓侀懛顏勭箒閻ㄥ嫰娓跺Ч鍌涙降鐎圭偟骞�
		 */
		String url = "http://m.meituan.com";
		// url閸欘垯浜掗弰顖欑閸欏嘲绨甸幋鏍拷鍛法閸ャ垻鐡戠粭顑跨瑏閺傚湱娈戠拹顓犲⒖wap缁旀瑧鍋ｉ敍灞芥躬鐠囥儳缍夌粩娆戞畱閺�顖欑帛鏉╁洨鈻兼稉顓ㄧ礉閺�顖欑帛鐎规嫉dk鐎瑰本鍨氶幏锔藉焻閺�顖欑帛
		extras.putString("url", url);
		intent.putExtras(extras);
		startActivity(intent);

	}

	/**
	 * create the order info. 閸掓稑缂撶拋銏犲礋娣団剝浼�
	 * 
	 */
	private String getOrderInfo(String subject, String body, String price) {

		// 缁涘墽瀹抽崥鍫滅稊閼板懓闊╂禒绲�D
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 缁涘墽瀹抽崡鏍ь啀閺�顖欑帛鐎规繆澶勯崣锟�
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 閸熷棙鍩涚純鎴犵彲閸烆垯绔寸拋銏犲礋閸欙拷
		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

		// 閸熷棗鎼ч崥宥囆�
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 閸熷棗鎼х拠锔藉剰
		orderInfo += "&body=" + "\"" + body + "\"";

		// 閸熷棗鎼ч柌鎴︻杺
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 閺堝秴濮熼崳銊ョ磽濮濄儵锟芥氨鐓℃い鐢告桨鐠侯垰绶�
		orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

		// 閺堝秴濮熼幒銉ュ經閸氬秶袨閿涳拷 閸ュ搫鐣鹃崐锟�
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 閺�顖欑帛缁鐎烽敍锟� 閸ュ搫鐣鹃崐锟�
		orderInfo += "&payment_type=\"1\"";

		// 閸欏倹鏆熺紓鏍垳閿涳拷 閸ュ搫鐣鹃崐锟�
		orderInfo += "&_input_charset=\"utf-8\"";

		// 鐠佸墽鐤嗛張顏冪帛濞嗗彞姘﹂弰鎾舵畱鐡掑懏妞傞弮鍫曟？
		// 姒涙顓�30閸掑棝鎸撻敍灞肩閺冿箒绉撮弮璁圭礉鐠囥儳鐟禍銈嗘鐏忓彉绱伴懛顏勫З鐞氼偄鍙ч梻顓滐拷锟�
		// 閸欐牕锟借壈瀵栭崶杈剧窗1m閿濓拷15d閵嗭拷
		// m-閸掑棝鎸撻敍瀹�-鐏忓繑妞傞敍瀹�-婢垛晪绱�1c-瑜版挸銇夐敍鍫熸￥鐠佽桨姘﹂弰鎾茬秿閺冭泛鍨卞鐚寸礉闁棄婀�0閻愮懓鍙ч梻顓ㄧ礆閵嗭拷
		// 鐠囥儱寮弫鐗堟殶閸婇棿绗夐幒銉ュ綀鐏忓繑鏆熼悙鐧哥礉婵★拷1.5h閿涘苯褰叉潪顒佸床娑擄拷90m閵嗭拷
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token娑撹櫣绮℃潻鍥ф彥閻х粯宸块弶鍐箯閸欐牕鍩岄惃鍒焞ipay_open_id,鐢缚绗傚銈呭棘閺佹壆鏁ら幋宄扮殺娴ｈ法鏁ら幒鍫熸綀閻ㄥ嫯澶勯幋鐤箻鐞涘本鏁禒锟�
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 閺�顖欑帛鐎规繂顦╅悶鍡楃暚鐠囬攱鐪伴崥搴礉瑜版挸澧犳い鐢告桨鐠哄疇娴嗛崚鏉挎櫌閹撮攱瀵氱�规岸銆夐棃銏㈡畱鐠侯垰绶為敍灞藉讲缁岋拷
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 鐠嬪啰鏁ら柧鎯邦攽閸椻剝鏁禒姗堢礉闂囷拷闁板秶鐤嗗銈呭棘閺佸府绱濋崣鍌欑瑢缁涙儳鎮曢敍锟� 閸ュ搫鐣鹃崐锟� 閿涘牓娓剁憰浣侯劮缁撅负锟藉﹥妫ょ痪鍧楁懕鐞涘苯宕辫箛顐ｅ祹閺�顖欑帛閵嗗澧犻懗鎴掑▏閻㈩煉绱�
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. 閻㈢喐鍨氶崯鍡樺煕鐠併垹宕熼崣鍑ょ礉鐠囥儱锟界厧婀崯鍡樺煕缁旑垰绨叉穱婵囧瘮閸烆垯绔撮敍鍫濆讲閼奉亜鐣炬稊澶嬬壐瀵繗顫夐懠鍐跨礆
	 * 
	 */
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * sign the order info. 鐎电顓归崡鏇氫繆閹垵绻樼悰宀�顒烽崥锟�
	 * 
	 * @param content
	 *            瀵板懐顒烽崥宥堫吂閸楁洑淇婇幁锟�
	 */
	private String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 閼惧嘲褰囩粵鎯ф倳閺傜懓绱�
	 * 
	 */
	private String getSignType() {
		return "sign_type=\"RSA\"";
	}

}
