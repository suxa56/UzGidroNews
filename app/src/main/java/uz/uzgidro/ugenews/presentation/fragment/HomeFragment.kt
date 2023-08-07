package uz.uzgidro.ugenews.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.uzgidro.ugenews.databinding.FragmentHomeBinding
import uz.uzgidro.ugenews.presentation.viewmodel.HomeViewModel
import uz.uzgidro.ugenews.presentation.recycler.NewsAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NewsAdapter

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.getNews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        val homeRV = binding.homeRV
        adapter = NewsAdapter()
        homeRV.adapter = adapter

        adapter.onClickListener = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToNewsFragment(
                    it
                )
            )
        }
    }

    private fun observeViewModel() {
        viewModel.news.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isNetworkError.observe(viewLifecycleOwner) {
            if (it) {
                binding.noInternetIcon.visibility = View.VISIBLE
            } else {
                binding.noInternetIcon.visibility = View.GONE
            }
        }
    }
}