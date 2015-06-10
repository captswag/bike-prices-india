package anjithsasindran.bikepricesindia;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by Anjith Sasindran
 * on 07-Jun-15.
 */
public class BikeNamesActivity extends AppCompatActivity {

    public String bike_names = "https://bike-prices.firebaseio.com/bike-details/";
    RecyclerView recyclerView;
    BikeNames bikeNames;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bike_names);
        setTitle(getIntent().getStringExtra("bike_name"));

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bike_names += getIntent().getStringExtra("position")+"/"
                +getIntent().getStringExtra("bike_name");
        Firebase reference = new Firebase(bike_names);

        layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.bikename_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                bikeNames = new BikeNames();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Map<?, ?> bikeKeyPair = (Map<?, ?>) child.getValue();
                    bikeNames.setBikeValues(bikeKeyPair, child.getKey());
                }

                BikeNamesAdapter adapter = new BikeNamesAdapter(bikeNames);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bike_names, menu);

        MenuItem searchItem = menu.findItem(R.id.bike_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getResources().getString(R.string.search_bikes));
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                try {
                    ((BikeNamesAdapter)recyclerView.getAdapter()).setFilter(query);
                } catch(NullPointerException e) {
                    Toast.makeText(getBaseContext(), "Wait for content to load", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home :
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
