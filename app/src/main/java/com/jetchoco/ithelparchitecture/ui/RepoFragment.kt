package com.jetchoco.ithelparchitecture.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetchoco.ithelparchitecture.databinding.FragmentRepoBinding
import com.jetchoco.ithelparchitecture.vm.GithubViewModelFactory
import com.jetchoco.ithelparchitecture.vm.RepoViewModel

class RepoFragment : Fragment() {

    companion object {
        const val TAG = "RepoFragment"
        fun newInstance(): RepoFragment {
            return RepoFragment()
        }
    }

    private var factory: GithubViewModelFactory = GithubViewModelFactory()
    private lateinit var viewModel: RepoViewModel
    private lateinit var binding: FragmentRepoBinding
    private var repoAdapter = RepoAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepoBinding.inflate(inflater, container, false)
        binding.edtQuery.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                doSearch()
                true
            } else {
                false
            }
        }
        binding.btnSearch.setOnClickListener { doSearch() }
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = repoAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(RepoViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.repos().observe(this, Observer {
            repoAdapter.swapItems(it)
        })
    }

    private fun doSearch() {
        val query = binding.edtQuery.text.toString()
        if (TextUtils.isEmpty(query)) {
            repoAdapter.swapItems(ArrayList())
            return
        }
        viewModel.doSearch(query)
        dismissKeyboard()
    }

    private fun dismissKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}