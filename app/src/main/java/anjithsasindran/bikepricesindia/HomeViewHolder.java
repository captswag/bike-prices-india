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
public class HomeViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView imageView;
    TextView textView;

    public HomeViewHolder(View view) {
        super(view);
        cardView = (CardView)view.findViewById(R.id.bike_companies_cardview);
        imageView = (ImageView) cardView.findViewById(R.id.bike_companies_image);
        textView = (TextView) cardView.findViewById(R.id.bike_companies_text);
    }

}
