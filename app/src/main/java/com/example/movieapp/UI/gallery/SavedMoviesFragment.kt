package com.example.movieapp.UI.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Gallery
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.UI.tab_activity.ui.main.PlaceholderFragment
import com.example.movieapp.databinding.FragmentSavedMoviesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SavedMoviesFragment : Fragment() {
    private val tabTitles = arrayOf(
        "Favourite",
        "Watched"
    )

    private var _binding: FragmentSavedMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var adapter: AdapterTabPager2? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val savedViewMode =
            ViewModelProvider(this).get(SavedViewModel::class.java)

        _binding = FragmentSavedMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = binding.viewPager

        adapter = activity?.let { AdapterTabPager2(it)}

        adapter?.addFragment(FavouriteListFragment(), tabTitles[0])
        adapter?.addFragment(WatchedListFragment(), tabTitles[1])

        viewPager.adapter = adapter

        val tabs: TabLayout = binding.tabs

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = adapter?.getTitle(position)
        }.attach()

    }

    //   private fun getMovies(){}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}