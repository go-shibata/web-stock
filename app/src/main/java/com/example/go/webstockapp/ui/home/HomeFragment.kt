package com.example.go.webstockapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.go.webstockapp.MainActivity
import com.example.go.webstockapp.databinding.FragmentHomeBinding
import com.example.go.webstockapp.di.ViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory<HomeViewModel>

    private val viewModel: HomeViewModel by activityViewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.run {
            if (this is MainActivity) {
                appComponent.inject(this@HomeFragment)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.linkList.apply {
            adapter = LinkListAdapter(this@HomeFragment, viewModel)
            layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }
}
