package a.jinkim.bemily.ui


import a.jinkim.bemily.R
import a.jinkim.bemily.databinding.FragmentUserinfoBinding
import a.jinkim.bemily.retofit.dto.response.userItem
import a.jinkim.bemily.util.constants.Companion.USERINFO
import a.jinkim.bemily.viewmodel.bemilyViewModel
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserInfoFragment : Fragment(R.layout.fragment_userinfo) {


    // FragmentMapBinding 선언
    private lateinit var binding: FragmentUserinfoBinding


    // 뷰모델 생성
    private val viewModel by activityViewModels<bemilyViewModel>()


    //컨트롤러
    lateinit var navController: NavController


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserinfoBinding.bind(view)

        navController = Navigation.findNavController(view)


        val userItem = arguments?.get(USERINFO) as userItem
        viewModel.getUserInfo(userItem.login)

        viewModel.userInfo.observe(viewLifecycleOwner) {
            it?.let {
                binding.apply {

                    Glide.with(avatarImageView)
                        .load(it.avatar_url)
                        .transform(
                            CenterCrop(),
                            RoundedCorners(10)
                        )
                        .thumbnail(0.1f)
                        .error(R.color.defaultImageColor) //에러
                        .placeholder(R.color.defaultImageColor) //미리보기
                        .fallback(R.color.defaultImageColor) // load할 url이 null인 경우 등 비어있을 때 보여줄 이미지를 설정
                        .into(avatarImageView)



                    userNameTextViwe.text = it.login
                    githumUrlTextViwe.text = it.html_url ?: "없음"
                    emailTextView.text = it.email ?: "없음"
                }

            }
        }


    }


}