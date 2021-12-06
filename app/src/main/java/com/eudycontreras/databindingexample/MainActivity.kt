package com.eudycontreras.databindingexample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.eudycontreras.databindingexample.viewmodels.DemoViewModel
import com.eudycontreras.databindingexample.viewmodels.DemoViewModelSimple1
import com.eudycontreras.databindingexample.viewmodels.DemoViewModelSimple2

typealias ItemBinding <T> = (T) -> Int

internal class MainActivity : AppCompatActivity() {

    private val demoViewModel: DemoViewModel by viewModels()
    private val demoViewModelSimple1: DemoViewModelSimple1 by viewModels()
    private val demoViewModelSimple2: DemoViewModelSimple2 by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            setVariable(BR.viewModel, demoViewModel)
            setVariable(BR.simpleVM1, demoViewModelSimple1)
            setVariable(BR.simpleVM2, demoViewModelSimple2)
        }
    }
}