package com.gustavohisan.apelieuser.main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gustavohisan.apelieuser.main.HomeStoreListAdapter
import com.gustavohisan.apelieuser.main.R
import com.gustavohisan.apelieuser.main.databinding.FragmentHomeBinding
import com.gustavohisan.apelieuser.main.model.StoreState
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()

    private val adapter: HomeStoreListAdapter = HomeStoreListAdapter()

    private val storeStateObserver = Observer<StoreState> { storeState ->
        when (storeState) {
            is StoreState.Success -> adapter.addItems(storeState.storeList)
            is StoreState.Error -> {
            }
        }
    }

    private var binding: FragmentHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getHomeStoreList()
        viewModel.storeState.observe(this, storeStateObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.homeRecyclerView?.layoutManager = LinearLayoutManager(this.context)
        binding?.homeRecyclerView?.adapter = adapter
    }
}
