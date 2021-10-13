package com.ggomez.myapplicationsqlite.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ggomez.myapplicationsqlite.R;
import com.ggomez.myapplicationsqlite.models.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {
    ArrayList<Producto> productos;

    public ProductoAdapter(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        holder.cargarProductos(productos.get(position));

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewNombrePro, textViewMarcaPro, textViewPrecioPro, textViewStockPro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewNombrePro = itemView.findViewById(R.id.textViewNombrePro);
            textViewMarcaPro = itemView.findViewById(R.id.textViewMarcaPro);
            textViewPrecioPro = itemView.findViewById(R.id.textViewPrecioPro);
            textViewStockPro = itemView.findViewById(R.id.textViewStockPro);
        }

        public void cargarProductos(Producto p) {
            textViewNombrePro.setText(p.getNombre());
            textViewMarcaPro.setText(p.getMarca());
            textViewPrecioPro.setText("Precio $" + p.getPrecio());
            textViewStockPro.setText("Quedan " + p.getStock() + " unidades");

            if(p.getCategoria().getNombre().equals("Computación")) {
                imageView.setImageResource(R.drawable.computacion);
            }
            else if(p.getCategoria().getNombre().equals("Construcción")) {
                imageView.setImageResource(R.drawable.construccion);
            }
            else if(p.getCategoria().getNombre().equals("Deporte")) {
                imageView.setImageResource(R.drawable.deporte);
            }
            else if(p.getCategoria().getNombre().equals("Hogar")) {
                imageView.setImageResource(R.drawable.hogar);
            }
            else if(p.getCategoria().getNombre().equals("Moda")) {
                imageView.setImageResource(R.drawable.moda);
            }
        }


    }
}
