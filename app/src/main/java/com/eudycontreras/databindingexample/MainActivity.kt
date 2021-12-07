package com.eudycontreras.databindingexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eudycontreras.databindingexample.databinding.ActivityMainBinding
import com.eudycontreras.databindingexample.viewmodels.*
import com.eudycontreras.databindingexample.viewmodels.DemoViewModel
import com.eudycontreras.databindingexample.viewmodels.DemoViewModelCollection
import com.eudycontreras.databindingexample.viewmodels.DemoViewModelSimple1
import com.eudycontreras.databindingexample.viewmodels.DemoViewModelSimple2

typealias ItemBinding <T> = (T) -> Int

internal class MainActivity : AppCompatActivity() {

    private val demoViewModel: DemoViewModel by viewModels()
    private val demoViewModelSimple1: DemoViewModelSimple1 by viewModels()
    private val demoViewModelSimple2: DemoViewModelSimple2 by viewModels()
    private val collectionViewModel: DemoViewModelCollection by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            viewModel = demoViewModel
            simpleVM1 = demoViewModelSimple1
            simpleVM2 = demoViewModelSimple2
            collectionVM = collectionViewModel
        }
    }
}
