/*
 * Copyright 2021 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sergiobelda.materialmotion.music

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.sergiobelda.materialmotion.R
import com.example.sergiobelda.materialmotion.databinding.MusicActivityBinding

class MusicActivity : AppCompatActivity() {
    private lateinit var binding: MusicActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MusicActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.bottomNavigationView.inflateMenu(R.menu.navigation_menu)
        val navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navController
        )
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.artistsFragment,
                R.id.albumsFragment,
                R.id.playlistsFragment
            )
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.albumFragment -> {
                    binding.appbarLayout.setExpanded(false, false)
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    binding.appbarLayout.setExpanded(true, true)
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                true
            }
        }
    }
}
