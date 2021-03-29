package Resources;

import java.util.ArrayList;

import Pojo.AddPlace;
import Pojo.DeletePlace;
import Pojo.Location;
import Pojo.UpdatePlace;

public class TestDataBuild implements Payloads
 {
	
	public AddPlace aadPlace() 	{
		ArrayList<String>types=new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		Location  location=new Location();
		AddPlace ad=new AddPlace();
		
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		ad.setLocation(location);
		ad.setAccuracy(50);
		
		ad.setName("Frontline house");
		ad.setPhone_number("(+91) 983 893 3937");
		ad.setAddress("29, side layout, cohen 09");
		ad.setWebsite("www.gmail.com");
		ad.setLanguage("French-IN");
		return ad;
		
		
	}
	
	public UpdatePlace updatePlace(String placeid,String Adress)
	{
		UpdatePlace up=new UpdatePlace();
		
		up.setPlace_id(placeid);
		up.setAddress(Adress);
		up.setKey("qaclick123");
		return up;
		
	}

	public DeletePlace deletePlace(String placeid) {
		
		DeletePlace dp=new DeletePlace();
		
		dp.setPlace_id(placeid);
		return dp;
	}

	
	
	

}
