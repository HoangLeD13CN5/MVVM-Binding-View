package com.example.samplemvvm.view

import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.samplemvvm.R
import com.example.samplemvvm.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
       return R.layout.activity_main
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        //  update the toolbar as the user navigates between the destinations
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.main_nav_fragment))
    }

    // delegate the system up button call with the NavController
    override fun onSupportNavigateUp() = findNavController(R.id.main_nav_fragment).navigateUp()
}
