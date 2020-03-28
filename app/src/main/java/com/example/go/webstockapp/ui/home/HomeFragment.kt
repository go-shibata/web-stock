package com.example.go.webstockapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.go.webstockapp.R
import com.example.go.webstockapp.database.entity.Link
import com.example.go.webstockapp.databinding.DialogAddLinkBinding
import com.example.go.webstockapp.databinding.FragmentHomeBinding
import com.example.go.webstockapp.di.ViewModelFactory
import com.example.go.webstockapp.ui.MyDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory<HomeViewModel>

    private val viewModel: HomeViewModel by activityViewModels { factory }
    private val onClickLinkListener = object : LinkListAdapter.OnClickLinkListener {
        override fun onClickLink(link: Link) {
            val builder = AlertDialog.Builder(requireContext())
                .setTitle(link.title)
                .setItems(R.array.click_link_list) { dialog, which ->
                    when (which) {
                        0 -> viewModel.openLink(link, requireActivity())
                        1 -> viewModel.deleteLink(link)
                    }
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.cancel, null)
            MyDialogFragment()
                .setBuilder(builder)
                .show(parentFragmentManager, null)
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.linkList.apply {
            adapter = LinkListAdapter(this@HomeFragment, viewModel).apply {
                setOnClickLinkListener(onClickLinkListener)
            }
            layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

    fun onClickAddButton() {
        val binding = DialogAddLinkBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(R.string.insert_link_dialog_title)
            .setView(binding.root)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                viewModel.insertLink(binding.url.text.toString())
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel, null)
        MyDialogFragment()
            .setBuilder(builder)
            .show(parentFragmentManager, null)
    }
}
