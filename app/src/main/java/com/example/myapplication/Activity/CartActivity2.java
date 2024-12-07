package com.example.myapplication.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.CartAdapter;
import com.example.myapplication.Helper.ChangeNumberItemsListener;
import com.example.myapplication.Helper.ManagmentCart;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCart2Binding;

public class CartActivity2 extends BaseActivity {
    private ActivityCart2Binding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCart2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart = new ManagmentCart(this);
        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if (managmentCart.getListCart().isEmpty()) {
            binding.emptytxt.setVisibility(View.VISIBLE);
            binding.scrollviewcart.setVisibility(View.GONE);
            binding.totalfee.setVisibility(View.GONE);
            binding.taxtxt.setVisibility(View.GONE);
            binding.deliverytxt.setVisibility(View.GONE);
            binding.totaltxt.setVisibility(View.GONE);
        } else {
            binding.emptytxt.setVisibility(View.GONE);
            binding.scrollviewcart.setVisibility(View.VISIBLE);
            binding.totalfee.setVisibility(View.VISIBLE);
            binding.taxtxt.setVisibility(View.VISIBLE);
            binding.deliverytxt.setVisibility(View.VISIBLE);
            binding.totaltxt.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.cartview.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managmentCart.getListCart(), this, () -> {
            calculateCart();
            initList();  // Refresh the UI after recalculating the cart
        });
        binding.cartview.setAdapter(adapter);
    }

    private void calculateCart() {
        double percentTax = 0.25;
        double delivery = 45;
        tax = Math.round(managmentCart.getTotalFee() * percentTax * 100.0) / 100;
        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100;

        binding.totalfee.setText("₹" + itemTotal);
        binding.taxtxt.setText("₹" + tax);
        binding.deliverytxt.setText("₹" + delivery);
        binding.totaltxt.setText("₹" + total);

        // Ensure the list is updated after each calculation
        initList(); // Refresh the UI after calculating the cart
    }

    private void setVariable() {
        binding.backbtn.setOnClickListener(view -> finish());
    }
}
