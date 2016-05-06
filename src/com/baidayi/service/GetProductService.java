package com.baidayi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.baidayi.domain.Product;
import com.baidayi.utils.ListUtil;

/**
 * 获取产品服务网络数据
 * 
 * @author: wll
 */
public class GetProductService {

	public List<Product> getProduct(int page, int menu, String search) {
		List<Product> products = new ArrayList<Product>();
		Document document = null;
		try {
			switch (menu) {
			case 0:// 酒水
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430160/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 1:// 营养保健
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430168/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 2:// 厨房调料
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430152/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 3:// 衣物清洁护理
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430174/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 4:// 家庭清洁护理
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430181/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 5:// 一次性用品
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430197/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 6:// 奶粉
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430269/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 7:// 童车童床
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430303/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 8:// 面部护肤
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430328/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 9:// 生活电器
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430395/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 10:// 飞利浦电器类
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430395/b50107-p"
										+ page + ".html").timeout(3000).get();
				break;
			// 搜索
			case 11:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/product_list.jsp?keyword="
										+ search + "&cid=c_10000&page=" + page)
						.timeout(3000).get();
				break;
			case 12:// 食品饮料酒水
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-600021/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 13:// 安徽特产
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-590007/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 14:// 休闲零食
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430085/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 15:// 饼干糕点
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430096/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 16://
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-590007/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 17:// 乳品饮料
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430120/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 18:// 冲饮谷物
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430131/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 19:// 方便速食
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430139/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 20:// 粮油/干货
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430144/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 21:// 厨房调料
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430152/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 22:// 酒水
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430160/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 23:// 茶叶
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430164/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 24:// 营养保健
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430168/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 25:// 厨房清洁、纸
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430174/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 26:// 家庭清洁护理
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430181/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 27:// 纸制品
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430189/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 28:// 一次性用品
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430197/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 29:// 清洁用具
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430207/p" + page
										+ ".html").timeout(3000).get();
				break;
			// 日用家居、锅具餐具
			case 30:// 家纺日用品
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430221/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 31:// 收纳洗晒
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430226/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 32:// 生活日用
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430233/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 33:// 成人用品
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430240/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 34:// 厨具锅具
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430242/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 35:// 餐具水具
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430256/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 36:// 宠物生活
				document = Jsoup
						.connect("http://www.bdysc.com/list-430265/p" + page

						+ ".html").timeout(3000).get();
				break;
			// 母婴用品、玩具
			case 37:// 奶粉
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430269/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 38:// 尿裤湿巾
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430276/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 39:// 婴儿辅食
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430284/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 40:// 宝宝用品
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430288/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 41:// 洗护清洁
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430296/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 42:// 童车童床
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430303/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 43:// 妈妈用品
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430314/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 44:// 玩具
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430318/p" + page
										+ ".html").timeout(3000).get();
				break;
			// 美容化妆、个人护理
			case 45:// 面部护肤
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430328/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 46:// 缤纷彩妆
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430340/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 47:// 男士护肤
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430350/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 48:// 洗发护发
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430355/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 49:// 洗浴用品
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430361/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 50:// 口腔护理
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430367/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 51:// 女性护理
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430371/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 52:// 身体护理
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430374/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 53:// 美容工具
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430377/p" + page
										+ ".html").timeout(3000).get();
				break;

			case 54:// 香水SPA
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430381/p" + page
										+ ".html").timeout(3000).get();
				break;

			// 生活电器、办公文娱
			case 55:// 厨房电器
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430386/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 56:// 生活电器
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430395/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 57:// 个人健康
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430401/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 58:// 办公文仪
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430405/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 59:// 保健器材
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430413/p" + page
										+ ".html").timeout(3000).get();
				break;

			// 生活服务、礼品专区
			case 60:// 送爸妈
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430434/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 61:// 送女士
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430437/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 62:// 送男士
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430440/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 63:// 节日礼品
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430443/p" + page
										+ ".html").timeout(3000).get();
				break;
			}

			String elementkey = document.getElementsByClass(
					"result_key_notfound").text();
			ListUtil.elementkey = elementkey;

			if (document != null && elementkey.equals("")) {
				Elements elements = document.getElementById("prodcutListUl")
						.children();
				for (Element element : elements) {
					Product product = new Product();
					product.setUrl("http://www.bdysc.com"
							+ element.child(0).select("a").attr("href"));
					product.setImageUrl(element.select("img").first()
							.absUrl("src"));
					product.setProductName(element.select("img").first()
							.attr("alt"));
					product.setProductDescribe("欢乐购商城自营店");
					product.setProductPrice(element.child(2).text()
							.replace(element.child(2).child(0).text(), "")
							.trim());
					products.add(product);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return products;
	}
}
