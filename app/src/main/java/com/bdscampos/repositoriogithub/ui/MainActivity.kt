package com.bdscampos.repositoriogithub.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.bdscampos.repositoriogithub.R
import com.bdscampos.repositoriogithub.core.createDialog
import com.bdscampos.repositoriogithub.core.createProgressDialog
import com.bdscampos.repositoriogithub.core.hideSoftKeyboard
import com.bdscampos.repositoriogithub.databinding.ActivityMainBinding
import com.bdscampos.repositoriogithub.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { RepoListAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        binding.rvRepoList.adapter = adapter


        viewModel.repos.observe(this) {
            when (it) {
                is MainViewModel.State.Error -> {
                    createDialog{
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    adapter.submitList(it.list)
                    dialog.dismiss()
                    binding.root.hideSoftKeyboard()
                }
                MainViewModel.State.loading -> {
                    dialog.show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it) }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    companion object {
        private const val TAG = "TAG"
    }
}