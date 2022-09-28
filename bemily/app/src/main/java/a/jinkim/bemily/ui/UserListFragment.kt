package a.jinkim.bemily.ui


import a.jinkim.bemily.R
import a.jinkim.bemily.adapter.userListAdapter
import a.jinkim.bemily.databinding.FragmentUserlistBinding
import a.jinkim.bemily.util.constants.Companion.USERINFO
import a.jinkim.bemily.util.datahelper
import a.jinkim.bemily.viewmodel.bemilyViewModel
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_userlist) {


    // FragmentMapBinding 선언
    private lateinit var binding: FragmentUserlistBinding

    // 뷰모델 생성
    private val viewModel by activityViewModels<bemilyViewModel>()

    //어뎁터
    private lateinit var userListAdapter: userListAdapter

    //컨트롤러
    lateinit var navController: NavController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserlistBinding.bind(view)

        navController = Navigation.findNavController(view)

        userListAdapter = userListAdapter(itemClickedListener = {
            val bundle = bundleOf(USERINFO to it)
            navController.navigate(R.id.action_userlist_to_userinfo,bundle)
        })



        binding.apply {

            githubUserListRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                adapter = userListAdapter
                addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            }

            searchButton.setOnClickListener {
                searchUser(keywordEditText.text.toString())
            }
            keywordEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(p0: TextView, p1: Int, p2: KeyEvent?): Boolean {
                    if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                        searchUser(p0.text.toString())
                    }
                    return false
                }
            })
        }

        viewModel.userList.observe(viewLifecycleOwner) {
            it?.let {
                userListAdapter.submitData(lifecycle, it)
            }
        }

        datahelper.total_cnt.observe(viewLifecycleOwner){
            it?.let{
                binding.numberSearchResultsTextView.text = "전체 검색수(${it})"
            }
        }


    }

    private fun searchUser(query: String) {
            viewModel.getUserList(query)
    }

}