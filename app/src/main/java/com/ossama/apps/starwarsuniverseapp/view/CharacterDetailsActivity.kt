package com.ossama.apps.starwarsuniverseapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ossama.apps.starwarsuniverseapp.R
import com.ossama.apps.starwarsuniverseapp.databinding.ActivityCharacterDetailsBinding
import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.RemoteSWCharacter
import com.ossama.apps.starwarsuniverseapp.viewModel.CharacterDetailsViewModel


class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_details)
        binding.viewModel = ViewModelProviders.of(this).get(CharacterDetailsViewModel::class.java)
        binding.lifecycleOwner = this

        val swCharacter = intent?.extras?.getSerializable("EXTRA_CHARACTER") as RemoteSWCharacter

        binding.viewModel!!.setup(swCharacter)

        binding.viewModel?.character?.observe(this, Observer {
            if (it != null) {
                updateData(it)
            }
        })
    }

    private fun updateData(swCharacter: SWCharacter) {
        binding.film.text = swCharacter.films!![0]!!.title
    }
}
