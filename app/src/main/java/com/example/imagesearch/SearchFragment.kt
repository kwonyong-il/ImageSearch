package com.example.imagesearch

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imagesearch.RetrofitInstance.apiService
import com.example.imagesearch.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mContext: Context
    private lateinit var adapter: SearchAdapter
    private var itemList: ArrayList<ListItem>  = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = requireContext()
        adapter = SearchAdapter(mContext)
        binding.searchList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.searchList.adapter = adapter
}

    private fun setupListeners() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.searchBtn.setOnClickListener {
            val query = binding.searchEdit.text.toString()
            if (query.isNotEmpty()) {
                adapter.clearItem()
                fetchImageResults(query)
            } else {
                Toast.makeText(mContext, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }

            imm.hideSoftInputFromWindow(binding.searchEdit.windowToken, 0)
        }
    }

    private fun fetchImageResults(query: String) {
        apiService.SearchImage(Constants.AUTH_HEADER, query, "recency", 1, 80)
            ?.enqueue(object : Callback<KakaoSearchData?> {
                override fun onResponse(call: Call<KakaoSearchData?>, response: Response<KakaoSearchData?>) {
                    response.body()?.meta?.let { meta ->
                        if (meta.totalCount != null && meta.totalCount.compareTo(0) > 0) {
                            response.body()!!.documents?.forEach { document ->
                                val title = document?.displaySitename ?: ""
                                val datetime = document?.datetime ?: ""
                                val thumbnail = document?.thumbnailUrl ?: ""
                                itemList.add(ListItem(title,datetime,thumbnail))
                            }
                        }
                    }
                    adapter.searchItem = itemList
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<KakaoSearchData?>, t: Throwable) {
                }
            })
    }
}
