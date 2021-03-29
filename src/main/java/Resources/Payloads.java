package Resources;

import Pojo.AddPlace;
import Pojo.DeletePlace;
import Pojo.UpdatePlace;

public interface Payloads {
	AddPlace aadPlace();
    UpdatePlace updatePlace(String placeid,String Adress);
    DeletePlace deletePlace(String placeid);
  
}
