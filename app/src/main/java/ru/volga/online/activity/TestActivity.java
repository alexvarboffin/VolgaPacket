package ru.volga.online.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ru.volga.online.R;
import ru.volga.online.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализация ViewBinding
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        View serverLayout = findViewById(R.id.edgar_loagin_kranin);
        serverLayout.setVisibility(View.GONE);

        // Инициализация полей с использованием binding
//        binding.button.setOnClickListener(v -> {
//            String inputText = binding.editText.getText().toString();
//            Toast.makeText(TestActivity.this, "Input: " + inputText, Toast.LENGTH_SHORT).show();
//        });
    }
}

