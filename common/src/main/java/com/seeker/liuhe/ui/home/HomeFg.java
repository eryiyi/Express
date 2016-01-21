package com.seeker.liuhe.ui.home;

public class HomeFg {
//	extends
//} BaseFg  implements IXListViewListener{
//	private View mView;
//	private String historyData;
//	private View tvMore;
//	private XListView mListView;
//	private HistoryAdapter mAdapter;
//	private EmptyViewManager mEmptyViewManager;
//	private ArrayList<HistoryEn> resultList=new ArrayList<HistoryEn>();
//	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		mContext = getActivity();
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		mView = inflater.inflate(R.layout.home_fg, null);
//		initView(mView);
//		 initData();
//		return mView;
//	}
//
//	public static Fragment newInstance() {
//		HomeFg fragment = new HomeFg();
//		fragment.setArguments(new Bundle());
//		return fragment;
//	}
//	
//	private void initView(View view) {
//		ActionBarUtil.initTitle(view, "首页");
//		MainWebFg.initSideLeft(view);
//		tvMore =view.findViewById(R.id.tv_more);
//		tvMore.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				Intent intent=new Intent(mContext, HistoryActivity.class);
//				intent.putExtra("data", historyData);
//				mContext.startActivity(intent);
//				
//			}
//		});
//		mListView = (XListView) view.findViewById(R.id.listview);
//		mListView.setPullEnable(true);
//		mListView.setPullLoadEnable(false);
//		mListView.setXListViewListener(this);
//		mAdapter = new HistoryAdapter(mContext,resultList);
//		mListView.setAdapter(mAdapter);
//
//		mEmptyViewManager = new EmptyViewManager(view, mListView);
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
//	public void initData() {	
//		doRequest();
//		requestHistory();
//	}
//
//	public void doRequest() {
//		String url ="http://c1data.b0.upaiyun.com/result.js";
//		new JsonGet(url, new Listener<String>() {
//
//			@Override
//			public void onResponse(String arg0) {
//				ResultEn result=GsonUtil.fromJson(arg0, ResultEn.class);
//				TextView tvDate=(TextView) mView.findViewById(R.id.tv_board);
//				tvDate.setText(result.year+"年"+result.day+result.time+" "+result.week+" "+"第"+result.nextid+"期");
//				TextView tvCount=(TextView)mView.findViewById(R.id.tv_count_down);
//				tvCount.setText(new Gson().toJson(result.ma));
//			}
//		}).request();
//	}
//
//	public void requestHistory(){
//		String url="http://m.1396ck.com/stat/5?client_lang=zh-tw&year=2015";
//		new JsonGet(url, new Listener<String>() {
//
//			@Override
//			public void onResponse(String response) {
//				historyData=response;
//				tvMore.setVisibility(View.VISIBLE);
//				Map<String, JsonElement> map = GsonUtil.fromJson2MapJson(response);
//				JsonElement result = map.get("items");
//				ArrayList<HistoryEn> tempList = GsonUtil.fromJson2List(result,HistoryEn.class);
//				if(tempList.size()>10){
//					resultList=new ArrayList<HistoryEn>( tempList.subList(0, 10));	
//				}
//				
//				
//				if (TextUtils.isEmpty(response)) {
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
//			
//		}).request();
//
//	}
//	
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
//	@Override
//	public void onStartPullDown() {
//		mListView.calculateTime();
//	}
//
//	
//	
//	public static void initBallView(View llhistory,String[] number,String[] sx,String[] wx){
//		TextView tv_ball_1=(TextView) llhistory.findViewById(R.id.tv_ball_1);
//		TextView tv_sx_1=(TextView) llhistory.findViewById(R.id.tv_sx_1);
//		TextView tv_ball_2=(TextView) llhistory.findViewById(R.id.tv_ball_2);
//		TextView tv_sx_2=(TextView) llhistory.findViewById(R.id.tv_sx_2);
//		TextView tv_ball_3=(TextView) llhistory.findViewById(R.id.tv_ball_3);
//		TextView tv_sx_3=(TextView) llhistory.findViewById(R.id.tv_sx_3);
//		TextView tv_ball_4=(TextView) llhistory.findViewById(R.id.tv_ball_4);
//		TextView tv_sx_4=(TextView) llhistory.findViewById(R.id.tv_sx_4);
//		TextView tv_ball_5=(TextView) llhistory.findViewById(R.id.tv_ball_5);
//		TextView tv_sx_5=(TextView) llhistory.findViewById(R.id.tv_sx_5);
//		TextView tv_ball_6=(TextView) llhistory.findViewById(R.id.tv_ball_6);
//		TextView tv_sx_6=(TextView) llhistory.findViewById(R.id.tv_sx_6);
//		TextView tv_ball_7=(TextView) llhistory.findViewById(R.id.tv_ball_7);
//		TextView tv_sx_7=(TextView) llhistory.findViewById(R.id.tv_sx_7);
//		tv_ball_1.setText(number[0]);
//		tv_ball_2.setText(number[1]);
//		tv_ball_3.setText(number[2]);
//		tv_ball_4.setText(number[3]);
//		tv_ball_5.setText(number[4]);
//		tv_ball_6.setText(number[5]);
//		tv_ball_7.setText(number[6]);
//		setBallBg(tv_ball_1, number[0]);
//		setBallBg(tv_ball_2, number[1]);
//		setBallBg(tv_ball_3, number[2]);
//		setBallBg(tv_ball_4, number[3]);
//		setBallBg(tv_ball_5, number[4]);
//		setBallBg(tv_ball_6, number[5]);
//		setBallBg(tv_ball_7, number[6]);
//		tv_sx_1.setText(sx[0]+"/"+wx[0]);
//		tv_sx_2.setText(sx[1]+"/"+wx[1]);
//		tv_sx_3.setText(sx[2]+"/"+wx[2]);
//		tv_sx_4.setText(sx[3]+"/"+wx[3]);
//		tv_sx_5.setText(sx[4]+"/"+wx[4]);
//		tv_sx_6.setText(sx[5]+"/"+wx[5]);
//		tv_sx_7.setText(sx[6]+"/"+wx[6]);
//	}
//
//	public  static void setBallBg(TextView view,String number){
//		int num=Integer.parseInt(number);
//		int[] redWave={1,2,7,8,12,13,18,19,23,24,29,30,34,35,40,45,46};
//		int[] blueWave={3,4,9,10,14,15,20,25,26,31,36,37,41,42,47,48};
//		int[]  greenWave={5,6,11,16,17,21,22,27,28,32,33,38,39,43,44,49};
//		if(Arrays.binarySearch(redWave, num)>0){
//			view.setBackgroundResource(R.drawable.ball_red);
//		};
//		if(Arrays.binarySearch(blueWave, num)>0){
//			view.setBackgroundResource(R.drawable.ball_blue);
//		};
//		if(Arrays.binarySearch(greenWave, num)>0){
//			view.setBackgroundResource(R.drawable.ball_green);
//		};
//	}

}
