package com.seeker.liuhe.ui.entity;

import java.util.ArrayList;

import com.eln.lib.common.entity.BaseEn;

public class HistoryDetailEn extends BaseEn{
	public String betDate;
	public String betPeriod;
	public ArrayList<BetGame> betGame;
	public String numbers;
	public String sx;
	public String wx;
	public String teMaOddOrEven;
	public String teMaBigOrSmall;
	public String totalOddOrEven;
	public String total;
	public String totalBigOrSmall;
}


class BetGame extends BaseEn{
	public String bet;
	public String result;
}