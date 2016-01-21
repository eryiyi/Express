package com.seeker.liuhe.ui;

import com.seeker.base.BaseAct;


public class HistoryActivity extends BaseAct  {
	
//	private XListView mListView;
//	private HistoryAdapter mAdapter;
//	private EmptyViewManager mEmptyViewManager;
//	private ArrayList<HistoryEn> resultList=new ArrayList<HistoryEn>();
//	private String[] yearArray={"2015,2014,2013","2012"};
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.history_act);
//		ActionBarUtil.initTitle(this, "历史记录");
//		ActionBarUtil.initLeft(this, null);
//		initView();
//		initData();
//	}
//
//	private void initView() {
//		// 右边栏
//		ImageView mDrawerImageView = (ImageView) this
//				.findViewById(R.id.title_right);
//		mDrawerImageView.setImageResource(R.drawable.ic_menu_home);
//		mDrawerImageView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//		          Builder builder=new AlertDialog.Builder(mContext);
//		            builder.setIcon(R.drawable.ic_launcher);
//		            builder.setTitle("选择年份");
//		            builder.setSingleChoiceItems(yearArray, 0, new AlertDialog.OnClickListener(){
//		            	
//		                public void onClick(DialogInterface dialog, int which) {
//		                	dialog.dismiss();
//		                	String year=yearArray[which];
//		                	doRequest(year);
//		                }
//		            }).create();
//		            
//			}
//		});
//		mListView = (XListView) this.findViewById(R.id.listview);
//		mListView.setPullEnable(true);
//		mListView.setPullLoadEnable(false);
//		mListView.setXListViewListener(this);
//		mAdapter = new HistoryAdapter(mContext,resultList);
//		mListView.setAdapter(mAdapter);
//
//		mEmptyViewManager = new EmptyViewManager(this, mListView);
//		mEmptyViewManager.setEmptyInterface(new EmptyInterface() {
//
//			@Override
//			public void doRetry() {
//				initData();
//			}
//		});
//		mEmptyViewManager.setType(EmptyStyle.EmptyStyle_NORMAL);
//
//	}
//
//	private void initData() {
//		doRequest("2015");
//	}
//	@Override
//	public void onRefresh() {
//		initData();
//	}
//
//	@Override
//	public void onLoadMore() {
////		mLastPosition = mLastPosition + Constant.PAGE_SIZE;
////		doRequest(mTable, mLastPosition);
//		// loadNextPage(mLastPosition);
//	}
//
//	public void doRequest(String year) {
//		
//		String url = "http://m.1396ck.com/stat/5?client_lang=zh-tw";
//		Param param = new Param();
//		param.put("year", year);
//		url = Param.appendToUrl(param, url);
//		new JsonGetWithCache(url, new Listener<String>() {
//
//
//			@Override
//			public void onResponse(String arg0) {
//				Map<String, JsonElement> map = GsonUtil.fromJson2MapJson(arg0);
//				JsonElement result = map.get("items");
//				resultList = GsonUtil.fromJson2List(result,
//						HistoryEn.class);
//				if (TextUtils.isEmpty(arg0)) {
//					mEmptyViewManager.setType(EmptyStyle.EmptyStyle_RETRY);
//					return;
//				}
//				if (resultList.size() == 0) {
//					mEmptyViewManager.setType(EmptyStyle.EmptyStyle_NODATA);
//				}
//				mEmptyViewManager.setType(EmptyStyle.EmptyStyle_NORMAL);
//				mAdapter = new HistoryAdapter(mContext,resultList);
//				mAdapter.notifyDataSetChanged();
//				mListView.onLoadComplete(true);
//			}
//		}).request();
//	}
//
//	@Override
//	public void onStartPullDown() {
//		mListView.calculateTime();
//	}

}
