package Resources;

public enum TestData1 {
	
	searchtext("shirt"),
	quanityitem("3"),
	password("123456");
	
	private String data;
	TestData1(String data)
	{
		this.data=data;
	}
   public String getResource()
   {
	   return data;
   }
}
	

