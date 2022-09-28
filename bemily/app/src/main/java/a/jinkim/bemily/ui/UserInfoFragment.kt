package a.jinkim.bemily.ui


import a.jinkim.bemily.R
import a.jinkim.bemily.databinding.FragmentUserinfoBinding
import a.jinkim.bemily.viewmodel.bemilyViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class UserInfoFragment : Fragment(R.layout.fragment_userinfo) {


    // FragmentMapBinding 선언
    private lateinit var binding: FragmentUserinfoBinding


    // 뷰모델 생성
    private val viewModel by activityViewModels<bemilyViewModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserinfoBinding.bind(view)


        binding.apply {

        }
    }


}