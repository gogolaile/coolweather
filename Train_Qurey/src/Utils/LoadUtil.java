package Utils;
import java.util.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoadUtil {


	
	public static SQLiteDatabase createOrOpenDatabase(){
		SQLiteDatabase sld = null;
		try{
			sld = SQLiteDatabase.openDatabase("/data/data/mydb", null, SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sld;
	}
	
	public static void createTable(String sql){
		SQLiteDatabase sld = createOrOpenDatabase();
		try{
			sld.execSQL(sql);
			sld.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static boolean insert(String sql){
		SQLiteDatabase sld = createOrOpenDatabase();
		try{
			sld.execSQL(sql);
			sld.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static Vector<Vector<String>> query(String sql){
		Vector<Vector<String>> vector = new Vector<Vector<String>>();
		SQLiteDatabase sld = createOrOpenDatabase();
		try{Cursor cursor = sld.rawQuery(sql, new String[]{});
		  while(cursor.moveToNext()){
			  Vector<String> v = new Vector<String>();
			  int col = cursor.getColumnCount();
			  for(int i=0;i < col; i++){
				  v.add(cursor.getString(i));
			  }
			  vector.add(v);
		  }
		  cursor.close();
		  sld.close();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return vector;
	}
}