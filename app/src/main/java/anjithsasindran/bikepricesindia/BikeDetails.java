package anjithsasindran.bikepricesindia;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Anjith Sasindran
 * on 07-Jun-15.
 */
public class BikeDetails {

    private ArrayList<Integer> position;
    private ArrayList<String> bikeCompany;
    private ArrayList<String> bikeUrl;

    public BikeDetails() {
        bikeCompany = new ArrayList<>();
        bikeUrl = new ArrayList<>();
        position = new ArrayList<>();
    }

    public void setBikeValues(Map<?, ?> bikeValues, int positionOfIndex) {
        bikeCompany.add(bikeValues.get("name").toString());
        bikeUrl.add(bikeValues.get("logo_url").toString());
        position.add(positionOfIndex);
    }

    public void setBikeValues(String bikeCompany, String bikeLogo) {
        this.bikeCompany.add(bikeCompany);
        this.bikeUrl.add(bikeLogo);
    }

    public int getPosition(String bikeName) {
        for (int i = 0 ; i < bikeCompany.size(); i ++) {
            if (bikeCompany.get(i).equals(bikeName)) {
                return i+1;
            }
        }
        return 0;
    }

    public int getLength() {
        return bikeCompany.size();
    }

    public String getBikeCompanyByIndex(int position) {
        return bikeCompany.get(position);
    }
    public String getBikeUrlByIndex(int position) {
        return bikeUrl.get(position);
    }

}
