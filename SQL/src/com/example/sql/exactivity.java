package com.example.sql;

import android.os.Bundle;

public class exactivity extends MainActivity{
     
	private static exactivity temp ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		temp = this;
	}
	
	public static exactivity getactivity(){
		return temp;
	}
	

}
