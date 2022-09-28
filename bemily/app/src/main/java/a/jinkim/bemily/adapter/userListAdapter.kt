package a.jinkim.bemily.adapter

import a.jinkim.bemily.R
import a.jinkim.bemily.databinding.ItemUserBinding
import a.jinkim.bemily.retofit.dto.response.userItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


class userListAdapter(private val itemClickedListener:(userItem) -> Unit) : PagingDataAdapter<userItem, userListAdapter.UserViewHolder>(differ) {
    companion object {
        private val differ = object : DiffUtil.ItemCallback<userItem>() {
            override fun areItemsTheSame(oldItem: userItem, newItem: userItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: userItem, newItem: userItem): Boolean {
                return oldItem.login == newItem.login &&
                        oldItem.html_url == newItem.html_url
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return  UserViewHolder(
                ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    override fun onBindViewHolder(holderTrack: UserViewHolder, position: Int) {
        getItem(position)?.let { trackItem ->
            holderTrack.bind(trackItem)
        }
    }

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: userItem) {

            Glide.with(binding.avatarImageView)
                .load(user.avatar_url)
                .error(R.color.defaultImageColor) //에러
                .placeholder(R.color.defaultImageColor) //미리보기
                .fallback(R.color.defaultImageColor) // load할 url이 null인 경우 등 비어있을 때 보여줄 이미지를 설정
                .into(binding.avatarImageView)

            binding.userNameTextViwe.text = user.login
            binding.githumUrlTextViwe.text = user.html_url
            binding.root.setOnClickListener{
                itemClickedListener(user)
            }

        }
    }
}