package com.seeker.liuhe.avos;


public class ParseHtml {
	
//	void parseHtml() {
//		ArrayList<FsBean> dataList = new ArrayList<FsBean>();
////		AssetManager am = this.getAssets();
//		InputStream inputStream;
//		try {
//			inputStream =am.open("html/xh.html");
//			Document doc = Jsoup.parse(inputStream, "UTF-8",
//					"http://img9.91huo.cn/");
//			Elements tbody = doc.getElementsByTag("tbody");
//			Element tBody = tbody.first();
//			Elements tr = tBody.getElementsByTag("tr");
//			for (Element tablerow : tr) {
//				FsBean bean = new FsBean();
//				Elements tmc1 = tablerow.getElementsByClass("tmc1");
//				if (tmc1.first() == null)
//					continue;
//				bean.tmc1 = tmc1.first().text();
//				// Log.e("tmc1", tmc1.first().text());
//				Elements tmc2 = tablerow.getElementsByClass("tmc2");
//				Node node = tmc2.first().childNode(0);
//				bean.tmc2 = node.attr("src");
//				Elements tmc3 = tablerow.getElementsByClass("tmc3");
//				bean.tmc3 = tmc3.first().text();
//				Elements tmc4 = tablerow.getElementsByClass("tmc4");
//				if (tmc4.first() != null) {
//					bean.tmc4 = tmc4.first().text();
//				}
//
//				Elements tmc5 = tablerow.getElementsByClass("tmc5");
//				if (tmc5.first() != null) {
//					bean.tmc5 = tmc5.first().text();
//				}
//				Elements tmc6 = tablerow.getElementsByClass("tmc6");
//				bean.tmc6 = tmc6.first().text();
//				Attributes attr = tablerow.attributes();
//				List<Attribute> attrList = attr.asList();
//				bean.tmc7 = attrList.get(0).getKey();
//				dataList.add(bean);
//			}
//
//			AVOSCloud.initialize(getApplication(), APP_ID, APP_KEY);
//			for (FsBean bean : dataList) {
//				AVObject gameScore = new AVObject("yh_xh");
//				gameScore.put("tmc1", bean.tmc1);
//				gameScore.put("tmc2", bean.tmc2);
//				gameScore.put("tmc3", bean.tmc3);
//				gameScore.put("tmc4", bean.tmc4);
//				gameScore.put("tmc5", bean.tmc5);
//				gameScore.put("tmc6", bean.tmc6);
//				gameScore.put("tmc7", bean.tmc7);
//				gameScore.saveInBackground();
//
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
