package test.seven_store.com.sevenstore;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public  class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private List<Product>products;
    private ProductViewHolder.OnProductItemClick onProductItemClick;

    public ProductsAdapter(List<Product> products, ProductViewHolder.OnProductItemClick onProductItemClick) {
        this.products = products;
        // کد زیر رو برای این اضافه کردم که تعداد آیتمهارو برای تست زیاد که موقتا
        this.products.addAll(products);
        //-------------------------------------------------------------
        this.onProductItemClick = onProductItemClick;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item,parent,false);
        return new ProductViewHolder(view,onProductItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bindProduct(products.get(position));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImageView;
        private TextView productTitle;
        private TextView producCurrentPrice;
        private TextView producPreviousPrice;
        private  OnProductItemClick onProductItemClick;

        public ProductViewHolder(View itemView,OnProductItemClick onProductItemClick) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.tv_product_title);
            productImageView = itemView.findViewById(R.id.iv_product_image);
            producCurrentPrice = itemView.findViewById(R.id.tv_product_currentPrice);
            producPreviousPrice = itemView.findViewById(R.id.tv_product_previousPrice);
            this.onProductItemClick = onProductItemClick;
        }
        public  void  bindProduct(final Product product){

            if (product.getStatus()==Product.STATUS_EXSTS){
                producCurrentPrice.setText(product.getCurrentPrice());
            }
            producPreviousPrice.setText(product.getPreviusPrice());
            productTitle.setText(product.getTitle());
            Picasso.get().load(product.getImageUrl()).into(productImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProductItemClick.onProductClick(product);
                }
            });

        }
        public  interface OnProductItemClick{
            void onProductClick(Product product);

        }
    }

}
