package anjithsasindran.bikepricesindia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

/**
 * Created by Anjith Sasindran
 * on 07-Jun-15.
 */
public class HomeActivityAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    BikeDetails bikeDetails;
    public Context context;

    public HomeActivityAdapter(BikeDetails bikeDetails) {
        this.bikeDetails = bikeDetails;
    }

    @Override
    public int getItemCount() {
        return bikeDetails.getLength();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_bike_companies, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, final int position) {
        holder.textView.setText(bikeDetails.getBikeCompanyByIndex(position));
        Picasso.with(context).load(bikeDetails.getBikeUrlByIndex(position))
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bikeNames = new Intent(context, BikeNamesActivity.class);
                bikeNames.putExtra("bike_name", bikeDetails.getBikeCompanyByIndex(position));
                bikeNames.putExtra("position", position+1+"");
                context.startActivity(bikeNames);
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(HomeViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }
}
