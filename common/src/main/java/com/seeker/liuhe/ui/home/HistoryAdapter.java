package com.seeker.liuhe.ui.home;

public class HistoryAdapter{
//}  extends BaseAdapter {
//	private ArrayList<HistoryEn> resultList=new ArrayList<HistoryEn>();
//	private Context mContext;
//	
//	public HistoryAdapter(Context context,ArrayList<HistoryEn> dataList) {
//		resultList=dataList;
//		mContext=context;
//	}
//
//	@Override
//	public int getCount() {
//		return resultList.size();
//	}
//
//	@Override
//	public HistoryEn getItem(int arg0) {
//		return resultList.get(arg0);
//	}
//
//	@Override
//	public long getItemId(int arg0) {
//		return arg0;
//	}
//
//	@Override
//	public View getView(int arg0, View arg1, ViewGroup arg2) {
//		if (arg1 == null) {
//			arg1 = View.inflate(mContext, R.layout.history_act_cell, null);
//		}
//		final HistoryEn historyEn=resultList.get(arg0);
//		arg1.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				Intent intent= new Intent(mContext,HistoryDetailActivity.class);
//				intent.putExtra("history", historyEn);
//				mContext.startActivity(intent);
//			}
//		});
//		
//		TextView tvDate = ViewHolder.get(arg1, R.id.tv_date);
//		tvDate.setText(historyEn.period);
//		HomeFg.initBallView(arg1, historyEn.numbers.split(","), historyEn.sx.split(","), historyEn.wx.split(","));
//		
//		return arg1;
//	}

}
