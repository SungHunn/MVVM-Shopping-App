package com.example.shoppingapp.presentation.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.presentation.BaseActivity
import com.example.shoppingapp.presentation.BaseFragment
import com.example.shoppingapp.presentation.list.ProductListFragment
import com.example.shoppingapp.presentation.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.koin.android.ext.android.inject

internal class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    override val viewModel by inject<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        bottomNav.setOnNavigationItemSelectedListener(this@MainActivity)
        showFragment(ProductListFragment() , ProductListFragment.TAG)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_products -> {
                showFragment(ProductListFragment() , ProductListFragment.TAG)
                true
            }
            R.id.menu_profile -> {
                showFragment(ProfileFragment() , ProfileFragment.TAG)
                true
            }
            else -> false
        }
    }

    private fun showFragment(fragment : Fragment, tag : String){
        val findFragemnt = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach { fm->
            supportFragmentManager.beginTransaction().hide(fm).commit()
        }
        findFragemnt?.let {
            supportFragmentManager.beginTransaction().show(it).commit()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment , tag)
                .commitAllowingStateLoss()
        }
    }

    override fun observeData() = viewModel.mainStateLiveData.observe(this) {
        when (it) {
            is MainState.RefreshOrderList -> {
                binding.bottomNav.selectedItemId = R.id.menu_profile
                val fragment = supportFragmentManager.findFragmentByTag(ProfileFragment.TAG)
                (fragment as? BaseFragment<*, *>)?.viewModel?.fetchData()
            }
        }
    }

}