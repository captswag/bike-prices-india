package anjithsasindran.bikepricesindia;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anjith Sasindran
 * on 07-Jun-15.
 */
public class BikeNamesViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView imageView;
    TextView textViewBikeName;
    TextView textViewBikePrice;

    public BikeNamesViewHolder(View view) {
        super(view);
        cardView = (CardView) view.findViewById(R.id.bikenames_cardview);
        imageView = (ImageView) cardView.findViewById(R.id.bikenames_imageview);
        textViewBikeName = (TextView) cardView.findViewById(R.id.bikenames_name);
        textViewBikePrice = (TextView) cardView.findViewById(R.id.bikenames_price);
    }

}
