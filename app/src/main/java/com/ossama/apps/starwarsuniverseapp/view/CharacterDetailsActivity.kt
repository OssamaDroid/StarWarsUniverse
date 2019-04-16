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

        val swCharacter = intent?.extras?.getSerializable(INTENT_EXTRAS_SW_CHARACTER) as RemoteSWCharacter

        binding.viewModel!!.setupObservers(swCharacter)

        binding.viewModel?.character?.observe(this, Observer {
            if (it != null) {
                updateData(it)
            }
        })
    }

    private fun updateData(swCharacter: SWCharacter) {
        // Name
        binding.textViewName.text = swCharacter.name

        // Birth year
        binding.textViewBirthYear.text = swCharacter.birth_year

        // Height
        binding.textViewHeight.text = swCharacter.height

        // Species Name
        binding.textViewSpeciesName.text = swCharacter.species?.name

        // Species Language
        binding.textViewLanguage.text = swCharacter.species?.language

        // HomeWorld name
        binding.textViewHomeWorldName.text = swCharacter.species?.homeWorld?.name

        // HomeWorld population
        binding.textViewPopulation.text = swCharacter.species?.homeWorld?.population
    }

    companion object {
        const val INTENT_EXTRAS_SW_CHARACTER = "intent.extras.sw.character"
    }
}
