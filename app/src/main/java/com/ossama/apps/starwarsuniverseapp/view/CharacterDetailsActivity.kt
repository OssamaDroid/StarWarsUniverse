package com.ossama.apps.starwarsuniverseapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ossama.apps.starwarsuniverseapp.R
import com.ossama.apps.starwarsuniverseapp.databinding.ActivityCharacterDetailsBinding
import com.ossama.apps.starwarsuniverseapp.model.entity.Film
import com.ossama.apps.starwarsuniverseapp.model.entity.SWCharacter
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.RemoteSWCharacter
import com.ossama.apps.starwarsuniverseapp.viewModel.CharacterDetailsViewModel


class CharacterDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailsBinding

    private var viewModel: CharacterDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_details)
        viewModel = ViewModelProviders.of(this).get(CharacterDetailsViewModel::class.java)

        val swCharacter = intent?.extras?.getSerializable(INTENT_EXTRAS_SW_CHARACTER) as RemoteSWCharacter

        viewModel?.setupObservers(swCharacter)

        viewModel?.character?.observe(this, Observer {
            if (it != null) {
                updateData(it)
            }
        })
    }

    private fun updateData(swCharacter: SWCharacter) {
        // Star Wars Character's data
        binding.swCharacter = swCharacter

        // Films
        swCharacter.films?.apply {
            showFilms(this)
        }
    }

    private fun showFilms(films: List<Film>) {
        val adapter = FilmsAdapter(films)
        binding.rvFilms.adapter = adapter
        binding.rvFilms.setHasFixedSize(true)
    }

    companion object {
        const val INTENT_EXTRAS_SW_CHARACTER = "intent.extras.sw.character"
    }
}
