package com.seeker.liuhe.ui.home;

public class HomeActivity{ 
//extends BaseAct {
//
//	private Context mContext;
//	private SlideMenu slideMenu;
//	private boolean isExit;
//	private FragmentManager FgManager;
//	private ArrayList<FragmentData> fragmentList;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		mContext = this;
//		FgManager = getSupportFragmentManager();
//		setContentView(R.layout.home_act);
//		slideMenu=(SlideMenu) findViewById(R.id.slideMenu);
//		initData();
//		initView();
//		initFragment();
//		SystemEvent.addListener(SystemEventID.INS_TOGGLE_SIDEBAR, this);
//
//	}
//
//	public static void launch(Context context) {
//		Intent intent = new Intent(context, HomeActivity.class);
//		context.startActivity(intent);
//	}
//
//	private void initView() {
//		ListView sideList = (ListView) findViewById(R.id.list_side);
//		sideList.setAdapter(new SideListAdapter());
//		sideList.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				slideMenu.close(true);
//				showFg(arg2);
//			}
//		});
//
//	}
//
//	class SideListAdapter extends BaseAdapter {
//
//		@Override
//		public int getCount() {
//			return fragmentList.size();
//		}
//
//		@Override
//		public FragmentData getItem(int arg0) {
//			return fragmentList.get(arg0);
//		}
//
//		@Override
//		public long getItemId(int arg0) {
//			return arg0;
//		}
//
//		@Override
//		public View getView(int arg0, View convertView, ViewGroup arg2) {
//			if (convertView == null) {
//				convertView = View.inflate(mContext, R.layout.menu_left_cell,
//						null);
//			}
//			TextView tvButton =ViewHolder.get(convertView, R.id.tv_button); 
//			ImageView ivImage=ViewHolder.get(convertView, R.id.iv_image);
//			
//			FragmentData data=getItem(arg0);
//			tvButton.setText(data.name);
//			ivImage.setImageResource(data.drawableId);
//			return convertView;
//		}
//
//	}
//
//	private void initFragment() {
//		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//		ft.add(R.id.menu_content, HomeFg.newInstance(), "首页");
//		ft.commit();
//	}
//
//	public void showFg(int pos) {
//		FragmentData data=fragmentList.get(pos);
//		String tag=data.name;
//		FragmentTransaction ft = FgManager.beginTransaction();
//		Fragment fragment = FgManager.findFragmentByTag(tag);
//		if(fragment!=null){
//			List<Fragment> fgList=FgManager.getFragments();
//			for (Fragment fragment2 : fgList) {
//				ft.hide(fragment2);
//			}
//			
//			ft.show(fragment);
//		}else{
//			fragment=data.fragment;
//			ft.add(R.id.menu_content, fragment, tag);
//		}
//		ft.commit();
//	}
//
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// 提示退出
//		if (null != slideMenu && slideMenu.isActivated()) {
//			slideMenu.close(true);
//			return true;
//		} else {
//			exitNotice();
//			return true;
//		}
//
//	}
//
//	private void exitNotice() {
//		if (!isExit) {
//			Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
//			isExit = true;
//		} else {
//			this.finish();
//		}
//
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				isExit = false;
//			}
//		}, 3500);
//	}
//	
//	@Override
//	public void onEvent(Message msg) {
//		switch (msg.what) {
//		case SystemEventID.INS_TOGGLE_SIDEBAR:
//			slideMenu.toggleSlide();
//			break;
//
//		default:
//			break;
//		}
//	}
//	
//	private void initData(){
//		fragmentList=new ArrayList<FragmentData>();
//		FragmentData home=new FragmentData( "首页",R.drawable.icon_home_green,HomeFg.newInstance());
//		fragmentList.add(home);
////		FragmentData hero=new FragmentData( "图库",R.drawable.icon_people_red,HeroAllFg.newInstance());
////		fragmentList.add(hero);
////		FragmentData wsq=new FragmentData( "微社区",R.drawable.icon_video_yellow,WsqFg.newInstance());
////		fragmentList.add(wsq);
////		FragmentData tieba=new FragmentData( "贴吧",R.drawable.icon_pic_black,TiebaFg.newInstance());
////		fragmentList.add(tieba);
////		FragmentData video=new FragmentData( "精选",R.drawable.icon_cyan_news,NewsFg.newInstance());
////		fragmentList.add(video);
//	}
//	private class FragmentData{
//	       public final Fragment fragment;
//	       public final int drawableId;
//	       public final String name;
//
//	       public FragmentData(String name, int drawableId,Fragment fragment) {
//	           this.name = name;
//	           this.drawableId = drawableId;
//	           this.fragment=fragment;
//	       }
//
//	}
	 
}
