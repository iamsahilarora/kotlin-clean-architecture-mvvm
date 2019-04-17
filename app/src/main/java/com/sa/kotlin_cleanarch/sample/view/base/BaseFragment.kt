package com.sa.kotlin_cleanarch.sample.view.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {

    private lateinit var binding: ViewDataBinding
    private lateinit var activity: Activity
    private lateinit var fragmentListener: OnFragmentListener

    abstract fun getLayoutId(): Int

    abstract fun onViewsInitialized(binding: ViewDataBinding, view: View)

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Override
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewsInitialized(binding, view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        if (context is Activity)
            activity = context

        if (context is OnFragmentListener)
            fragmentListener = context

        super.onAttach(context)
    }
}