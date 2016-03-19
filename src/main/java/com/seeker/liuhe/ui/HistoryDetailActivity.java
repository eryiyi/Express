package com.seeker.liuhe.ui;

import com.eln.lib.base.BaseActivity;


public class HistoryDetailActivity extends BaseActivity {
	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.history_detail_act);
//		ActionBarUtil.initTitle(this, "详情");
//		ActionBarUtil.initLeft(this, null);
//		Intent intent =getIntent();
//		HistoryEn history=(HistoryEn) intent.getSerializableExtra("history");
//		initView();
//		initData(history.period,history.period);
//	}
//
//	private void initView() {
//	
//	}
//	
//	private void initData(String year,String period) {
//		String url = "http://m.1396ck.com/stat/9?client_lang=zh-tw";
//		Param param = new Param();
//		param.put("year", year);
//		param.put("period", period);
//		url = Param.appendToUrl(param, url);
//		new JsonGetWithCache(url, new Listener<String>() {
//
//
//			@Override
//			public void onResponse(String arg0) {
//				HistoryDetailEn detail=GsonUtil.fromJson(arg0, HistoryDetailEn.class);
//				
//				TextView tvDate = (TextView) mContext.findViewById(R.id.tv_date);
//				tvDate.setText(detail.betDate+" 第"+detail.betPeriod+"期");
//				LinearLayout view =(LinearLayout) mContext.findViewById(R.id.ll_history_detail);
//				HomeFg.initBallView(view, detail.numbers.split(","), detail.sx.split(","), detail.wx.split(","));
//				TextView tvSx=(TextView) mContext.findViewById(R.id.tv_sx);
//				String recommend="";
//				for (int i = 0; i < detail.betGame.size(); i++) {
//					recommend=recommend+detail.betGame.get(i)+" ";
//				}
//				tvSx.setText(Html.fromHtml(recommend));
//			}
//		}).request();
//	}
//	
}
