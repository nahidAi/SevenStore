package test.seven_store.com.sevenstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import ss.com.infinitescrollprovider.InfiniteScrollProvider;
import ss.com.infinitescrollprovider.OnLoadMoreListener;

public class ProductListActivity extends AppCompatActivity implements ProductsAdapter.ProductViewHolder.OnProductItemClick {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Toolbar toolbar = findViewById(R.id.productListToolbar);
        setSupportActionBar(toolbar);



        setupViews();
        ApiService apiService = new ApiService(this);
        apiService.getProducts(new ApiService.OnResultCallback<List<Product>>() {
            @Override
            public void onReceived(List<Product> products) {
                ProductsAdapter productsAdapter = new ProductsAdapter(products, ProductListActivity.this);
                recyclerView.setAdapter(productsAdapter);

            }

            @Override
            public void onError() {
                Toast.makeText(ProductListActivity.this, "خطا در انجام عملیات", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupViews() {
        recyclerView = findViewById(R.id.rel_details_product);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        InfiniteScrollProvider infiniteScrollProvider = new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView, new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Toast.makeText(ProductListActivity.this,"رسیدیم به ته لیست",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onProductClick(Product product) {
        Toast.makeText(this, product.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
