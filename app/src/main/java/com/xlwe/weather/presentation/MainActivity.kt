package com.xlwe.weather.presentation

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.google.android.material.snackbar.Snackbar
import com.xlwe.weather.R
import com.xlwe.weather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        viewModel.weather.observe(this) { weather ->
            var city = ""

            enterCity.forEachIndexed { index, c ->
                city += if (index == 0)
                    c.uppercaseChar()
                else
                    c.lowercaseChar()
            }

            binding.city.text = city
            binding.temperature.text = weather.temperature
            binding.description.text = weather.description
            binding.wind.text = weather.wind
            binding.progressBar.visibility = View.GONE
        }

        viewModel.error.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        }
    }

    private var enterCity = ""

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val menuItem = menu?.findItem(R.id.search)
        val searchView = menuItem?.actionView as SearchView

        searchView.queryHint = getString(R.string.enterCity)
        searchView.isIconified = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!!.isNotEmpty()) {
                    hideKeyboard(binding.toolbar)
                    searchView.onActionViewCollapsed()
                    viewModel.getWeather(query!!)
                    enterCity = query
                    binding.progressBar.visibility = View.VISIBLE
                    binding.city.text = ""
                    binding.temperature.text = ""
                    binding.description.text = ""
                    binding.wind.text = ""
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun showKeyboard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }
}