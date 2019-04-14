package com.ossama.apps.starwarsuniverseapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.ossama.apps.starwarsuniverseapp.R
import com.ossama.apps.starwarsuniverseapp.databinding.ActivityMainBinding
import com.ossama.apps.starwarsuniverseapp.viewModel.SearchCharacterViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = ViewModelProviders.of(this).get(SearchCharacterViewModel::class.java)
        binding.lifecycleOwner = this

        val adapter = SWCharacterAdapter()
        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.setHasFixedSize(true)
        binding.rvCharacters.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.viewModel?.characters?.observe(this, Observer {
            if (it != null) {
                adapter.updateList(it)
            }
        })
    }
}
