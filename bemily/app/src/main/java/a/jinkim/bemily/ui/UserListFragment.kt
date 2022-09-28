package a.jinkim.bemily.ui


import a.jinkim.bemily.R
import a.jinkim.bemily.adapter.userListAdapter
import a.jinkim.bemily.databinding.FragmentUserlistBinding
import a.jinkim.bemily.viewmodel.bemilyViewModel
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserlistBinding.bind(view)

        userListAdapter = userListAdapter()



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

        viewModel.trackList.observe(viewLifecycleOwner) {
            it?.let {
                userListAdapter.submitData(lifecycle, it)
            }

        }


        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(context, it.errors.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchUser(query: String) {
            viewModel.getUserList(query)
    }

}