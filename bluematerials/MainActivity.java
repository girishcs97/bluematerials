package com.sial.bluematerials;
//Om Sri Sai Ram
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MaterialFragment.OnListFragmentInteractionListener {

    private MaterialDataSource materialDS = MaterialDataSource.shared(this);
    private MaterialFragment materialFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {

            materialFragment = MaterialFragment.newInstance(1);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, materialFragment)
                    .commitNow();
        }
        fetchMaterials();
    }

    @Override
    public void onListFragmentInteraction(Material item) {
//Load the details screen fragemtn / view model / dialog
    }

    private void fetchMaterials()
    {
        materialDS.fetch(new MaterialDataSource.IResponseListener() {
            @Override
            public void onNetworkResponse() {
                if(null != materialFragment && null != materialFragment.getAdapter())
                    materialFragment.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onNetworkError(String errorMEssage) {
                Toast.makeText(MainActivity.this, errorMEssage, Toast.LENGTH_LONG).show();
            }
        });
    }

}
