package anjithsasindran.bikepricesindia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.squareup.picasso.Picasso;

/**
 * Created by Anjith Sasindran
 * on 07-Jun-15.
 */
public class BikeNamesAdapter extends RecyclerView.Adapter<BikeNamesViewHolder> {

    BikeNames bikeNames;
    BikeNames afterQueryBikeNames;
    Context context;

    public BikeNamesAdapter(BikeNames bikeNames) {
        this.bikeNames = bikeNames;
        this.afterQueryBikeNames = bikeNames;
    }

    @Override
    public int getItemCount() {
        return afterQueryBikeNames.getLength();
    }

    @Override
    public BikeNamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_bike_names, parent, false);
        return new BikeNamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BikeNamesViewHolder holder, int position) {

        holder.textViewBikeName.setText(afterQueryBikeNames.getBikeNameByIndex(position));
        holder.textViewBikePrice.setText(
                context.getResources().getString(R.string.Rs)+" "+
                        afterQueryBikeNames.getBikePricesByIndex(position));

        ViewTreeObserver widthListener = holder.imageView.getViewTreeObserver();
        widthListener.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                holder.imageView.getViewTreeObserver().removeOnPreDrawListener(this);

                holder.imageView.requestLayout();
                holder.imageView.getLayoutParams().height = (holder.imageView.getMeasuredWidth() * 3) / 4;
                return true;
            }
        });

        Picasso.with(context).load(afterQueryBikeNames.getBikeUrlByIndex(position))
                .into(holder.imageView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setFilter(String query) {
        afterQueryBikeNames = new BikeNames();
        query = query.toLowerCase();
        for (int i = 0 ; i < bikeNames.getLength() ; i++) {
            if (bikeNames.getBikeNameByIndex(i).toLowerCase().contains(query)) {
                afterQueryBikeNames.setBikeValues(bikeNames.getBikeUrlByIndex(i),
                        bikeNames.getBikeNameByIndex(i), bikeNames.getBikePricesByIndex(i));
            }
        }
        notifyDataSetChanged();
    }


}
