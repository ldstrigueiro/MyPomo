package com.example.mypomo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.mypomo.R
import com.example.mypomo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
    }

    private fun initListeners() {
        viewModel.loadingUi.observe(this, Observer {
            if(it){
                //TODO setLoading On
            }else{
                //TODO setLoading Off
            }
        })
    }

    override fun onBackPressed() {

    }
}
