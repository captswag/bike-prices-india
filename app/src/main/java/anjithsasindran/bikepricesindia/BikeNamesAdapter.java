package anjithsasindran.bikepricesindia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

/**
 * Created by Anjith Sasindran
 * on 07-Jun-15.
 */
public class BikeNamesAdapter extends RecyclerView.Adapter<BikeNamesViewHolder> {

    BikeNames bikeNames;
    Context context;
    int height;

    public BikeNamesAdapter(BikeNames bikeNames, int height) {
        this.bikeNames = bikeNames;
        this.height = height;
    }

    @Override
    public int getItemCount() {
        return bikeNames.getLength();
    }

    @Override
    public BikeNamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_bike_names, parent, false);
        return new BikeNamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BikeNamesViewHolder holder, int position) {

        holder.textViewBikeName.setText(bikeNames.getBikeNameByIndex(position));
        holder.textViewBikePrice.setText(bikeNames.getBikePricesByIndex(position));

        holder.imageView.requestLayout();
        holder.imageView.getLayoutParams().height = height;

        Picasso.with(context).load(bikeNames.getBikeUrlByIndex(position))
                .into(holder.imageView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
