package Resources;

public enum ApiResources {
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceApi("/maps/api/place/get/json"),
	deletePlaceAPi("/maps/api/place/delete/json"),
    updatePlaceAPi("/maps/api/place/update/json"),
	userAPI("/api/users/2");
	private String resource;
	
	
	ApiResources(String resource)
	{
		this.resource=resource;
	}
   public String getResource()
   {
	   return resource;
   }
}
