package uz.uzgidro.ugenews.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import uz.uzgidro.ugenews.databinding.FragmentNewsBinding
import uz.uzgidro.ugenews.presentation.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<NewsFragmentArgs>()

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(
            this
        )[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        viewModel.parseText(args.news.text!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupNewsInfo()
        observeViewModel()
        setupClickListener()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.toolbar.title = args.news.title
    }

    private fun setupNewsInfo() {
        with(args) {
            Picasso.get().load(news.img).fit().centerCrop().into(binding.newsImage)
            binding.newsTitle.text = news.title
            binding.newsDate.text = news.date
            binding.newsViews.text = news.views.toString()
        }
    }

    private fun observeViewModel() {
        viewModel.textBlocks.observe(viewLifecycleOwner) {
            it.forEach { (index, value) ->
                if (index.startsWith("text")) {
                    createTextField(value)
                } else if (index.startsWith("img")) {
                    createImageField(value)
                }
            }
        }
    }

    private fun setupClickListener() {
        binding.newsDate.setOnClickListener {
            (it as Chip).isChecked = true
        }
        binding.newsViews.setOnClickListener {
            (it as Chip).isChecked = true
        }
    }

    private fun createTextField(text: String) {
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val container = binding.contentLayout

        val textView = TextView(requireContext())
        textView.layoutParams = params
        container.addView(textView)

        textView.text = text
    }

    private fun createImageField(imageSrc: String) {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val container = binding.contentLayout

        val imageView = ImageView(requireContext())
        imageView.layoutParams = params
        imageView.minimumHeight = 750
        container.addView(imageView)

        Picasso.get().load(imageSrc).fit().centerCrop().into(imageView)
    }
}