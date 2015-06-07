package anjithsasindran.bikepricesindia;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Anjith Sasindran
 * on 07-Jun-15.
 */
public class BikeDetails {

    private ArrayList<String> bike_Company;
    private ArrayList<String> bike_Url;

    public BikeDetails() {
        bike_Company = new ArrayList<>();
        bike_Url = new ArrayList<>();
    }

    public void setBikeValues(Map<?, ?> bikeValues) {
        bike_Company.add(bikeValues.get("name").toString());
        bike_Url.add(bikeValues.get("logo_url").toString());
    }

    public int getLength() {
        return bike_Company.size();
    }

    public String getBikeCompanyByIndex(int position) {
        return bike_Company.get(position);
    }
    public String getBikeUrlByIndex(int position) {
        return bike_Url.get(position);
    }

}
