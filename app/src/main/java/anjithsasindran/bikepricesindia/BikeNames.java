package anjithsasindran.bikepricesindia;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Anjith Sasindran
 * on 07-Jun-15.
 */
public class BikeNames {

    private ArrayList<String> bikeNames;
    private ArrayList<String> bikeImageUrl;
    private ArrayList<String> bikePrices;

    public BikeNames() {
        this.bikeNames = new ArrayList<>();
        this.bikeImageUrl = new ArrayList<>();
        this.bikePrices = new ArrayList<>();
    }

    public int getLength() {
        return bikeNames.size();
    }

    public void setBikeValues(Map<?, ?> value, String bike_name) {
        bikeImageUrl.add(value.get("image_url").toString());
        bikePrices.add(value.get("price").toString());
        bikeNames.add(bike_name);
    }

    public String getBikeNameByIndex(int position) {
        return bikeNames.get(position);
    }

    public String getBikeUrlByIndex(int position) {
        return bikeImageUrl.get(position);
    }

    public String getBikePricesByIndex(int position) {
        return bikePrices.get(position);
    }
}
