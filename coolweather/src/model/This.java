package model;

public class This {
   private int id;
   private String city_Name;
   private String city_Code;
   private int provinceId;
   
   public int getId(){
	   return id;
   }
   public void setId(int id){
	   this.id = id;
   }
   public String getCity_Name(){
	   return city_Name;
   }
   public void setCity_Name(String city_Name){
	   this.city_Name = city_Name;
   }
   public String getCity_Code(){
	   return city_Code;
   }
   public void setCity_Code(String city_Code){
	   this.city_Code = city_Code;
   }
   public int getProvinceId()
   {
	   return provinceId;
   }
   public void setProvinceId(int provinceId){
	   this.provinceId = provinceId;
   }
}
