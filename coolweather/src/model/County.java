package model;

public class County {
	private int id;
	private String countyName;
	private String countyCode;
	private int cityId;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getCounty_Name()
	{
		return countyName;
	}
	public void setCounty_Name(String countyName){
		this.countyName = countyName;
	}
	public String getCounty_Code(){
		return countyCode;
	}
	public void setCountyCode(String countyCode){
		this.countyCode = countyCode;
	}
	public int getcityId(){
		return cityId;
	}
	public void setCityId(int cityId){
		this.cityId = cityId;
	}
}
