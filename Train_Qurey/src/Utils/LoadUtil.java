package Utils;
import java.util.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.train_qurey.R;

public class LoadUtil {


	
	public static SQLiteDatabase createOrOpenDatabase(){
		SQLiteDatabase sld = null;
		try{
			sld = SQLiteDatabase.openDatabase("/data/data/com.example.train_qurey/mydb", null, SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY);
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
	
	public static int getInsertId(String table,String sid){
		int id = 0;
		String sql = "select Max(" + sid + ") from " + table ;
		SQLiteDatabase db = createOrOpenDatabase();
		try{
			Cursor cursor = db.rawQuery(sql, new String[]{});
			if(cursor.moveToNext()){
				id = cursor.getInt(0);
			}
				cursor.close();
				db.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		id++;
		return id;
	} 
	
	public static Vector<Vector<String>> getInfo(String info){
		String sql = "select Sname,Rarrivetime,Rstarttime from station,"+"(select Sid,Rid,Rarrivetime,Rstarttime from relation where Tid = " + "(select Tid from train where Tname = '" +info +"')) a"
+"where  a.Sid =staion.Sid order by Rid";		
	Vector<Vector<String>> V_temp = query(sql);
	return V_temp;
}

	public static Vector<Vector<String>> trainsearch(String train_number) {
		// TODO Auto-generated method stub
		String sql = "select Tname,Tstartstation,Tterminus,Ttype from train where Tname ='"+ train_number + "'";
		Vector<Vector<String>> temp1 = query(sql);
		sql = "select Tstartstation,Rstarttime from  train,relation where relation.Tid = train.Tid and Tname ='"+train_number +"'and relation.Sid = (select Sid from station where Sname = train.Tstartstation)";
		Vector<Vector<String>> temp2 = query(sql);
		sql = "select Tterminus,Rarrivetime from train,relation where relation.Tid = train.Tid and Tname ='"+train_number +"'and relation.Sid = (select Sid from station where Sname = train.Tstartstation)";
		Vector<Vector<String>> temp3 = query(sql);
		temp1 = combine(temp1,temp2,temp3);
		return temp1;
	}

	private static Vector<Vector<String>> combine(Vector<Vector<String>> temp1, Vector<Vector<String>> temp2,
			Vector<Vector<String>> temp3) {
		// TODO Auto-generated method stub
		for(int i=0;i < temp1.size();i++){
			Vector<String> v1 = temp1.get(i);
			if(i < temp2.size()){
				for(int j=0;j < temp2.get(i).size();j++){
					v1.add(temp2.get(i).get(j));
				}
			}else{
				v1.add("");
				v1.add("");
			}
		}
		
		for(int i=0;i < temp1.size();i++){
			Vector<String> v1 = temp1.get(i);
			if(i < temp3.size()){
				for(int j=0;j < temp3.get(i).size();j++){
					v1.add(temp3.get(i).get(j));
				}
			}else{
				v1.add("");
			}
		}
		return temp1;
	}

	public static Vector<Vector<String>> stationsearch(String sczcx_station_name) {
		// TODO Auto-generated method stub
		String sql = "select Tname,Tstartstation,Tterminus,Ttype from train where Tid in (select Tid from relation where Sid in (select Sid from station where Sname = '" + sczcx_station_name +"'))";
		Vector<Vector<String>> temp1 = LoadUtil.query(sql);
		sql = "select '"+sczcx_station_name +"'Rstarttime from relation where Sid = (select Sid from station where Sname = '" +sczcx_station_name + "')";
		Vector<Vector<String>> temp2 = LoadUtil.query(sql);
		sql = "select '"+sczcx_station_name +"'Rarrivetime from relation where Sid = (select Sid from station where Sname = '" +sczcx_station_name + "')";
		Vector<Vector<String>> temp3 = LoadUtil.query(sql);
		temp1 = combine(temp1,temp2,temp3);	
		return temp1;
	}

	public static Vector<Vector<String>> zzsearch(String sstart_station, String sarrive_station) {
		// TODO Auto-generated method stub
		String sql = "select Tname,Tstartstation,Tterminus,Ttype from train where Tid in (select Tid from relation where Sid in (select Sid from station where Sname = '" + sstart_station+"')) and Tid in  (select Tid from relation where Sid in (select Sid from station where Sname = '" + sarrive_station+"'))";
		Vector<Vector<String>> temp = query(sql);
		sql = "select Sname,Rstarttime from station,relation where Sname ='"+sstart_station+"' and station.Sid = relation.Sid and relation.Tid in (select Tid from relation where Sid in (select Sid from station where Sname = '"+sstart_station+"')" +" and Tid in (select Tid from relation where Sid in (select Sid from station where Sname = '"+sarrive_station+"')))";
		Vector<Vector<String>> temp1 = query(sql);
		sql = "select Sname,Rarrivetime from station,relation where Sname ='"+sarrive_station+"' and station.Sid = relation.Sid and relation.Tid in (select Tid from relation where Sid in (select Sid from station where Sname = '"+sstart_station+"')" +"and Tid in (select Tid from relation where Sid in (select Sid from station where Sname = '"+sarrive_station+"')))";
		Vector<Vector<String>> temp2 = query(sql);
		temp = combine(temp, temp1, temp2);
		return temp;
	}

	public static Vector<Vector<String>> zzzsearch(String sstart_station, String stransfer_station,
			String sarrive_station) {
		// TODO Auto-generated method stub
		Vector<Vector<String>> vector = zzsearch(sstart_station, stransfer_station);
		Vector<Vector<String>> vector2 = zzsearch(stransfer_station,sarrive_station);
		for(int i=0; i< vector2.size();i++){
			vector.add(vector2.get(i));
		}
		return vector;
	}
}
