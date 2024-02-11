package com.example.examplesharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examplesharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            saveUsernameAndEmail(binding.edUsername.text.toString(), binding.etEmail.text.toString())
        }

        binding.btnLoad.setOnClickListener {
            val usernameInfo = loadUsernameAndEmail()
            binding.tvUsername.text = usernameInfo.first
            binding.tvEmail.text = usernameInfo.second
        }
    }

    private fun saveUsernameAndEmail(username: String, email: String) {
        val sharedPrefs = getSharedPreferences("UsernameInfo", MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        editor.apply {
            putString("username", username)
            putString("email", email)
            apply()
        }
    }

    private fun loadUsernameAndEmail(): Pair<String, String> {
        val sharedPrefs = getSharedPreferences("UsernameInfo", MODE_PRIVATE)
        val username = sharedPrefs.getString("username", "empty")!!
        val email = sharedPrefs.getString("email", "empty")!!

        return Pair(username, email)
    }
}