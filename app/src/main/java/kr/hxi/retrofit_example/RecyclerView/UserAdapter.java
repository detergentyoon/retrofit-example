package kr.hxi.retrofit_example.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hxi.retrofit_example.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<UserModel> userList;

    public UserAdapter(ArrayList<UserModel> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserModel user = userList.get(position);
        holder.onBind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView avatarUrl, login, id, htmlUrl, name, location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            avatarUrl = itemView.findViewById(R.id.item_avatar_url);
            login = itemView.findViewById(R.id.item_login);
            id = itemView.findViewById(R.id.item_id);
            name = itemView.findViewById(R.id.item_name);
            htmlUrl = itemView.findViewById(R.id.item_html_url);
            location = itemView.findViewById(R.id.item_location);
        }

        public void onBind(UserModel user) {
//            avatarUrl.setText(userModel.getAvatarUrl());
            login.setText(user.getLogin());
            id.setText(String.valueOf(user.getId()));
            name.setText(user.getName());
            htmlUrl.setText(user.getHtmlUrl());
            location.setText(user.getLocation());
        }
    }
}
